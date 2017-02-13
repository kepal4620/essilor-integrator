package essilor.integrator.adapter.tools;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

@Component
public class Encryptor {
	
  static Logger logger = Logger.getLogger(Encryptor.class.getName());	
  static{
	logger.setAdditivity(false);
  }

  @Value("${adapter.eet.privateKeyPath}")
  private String privateKeyPath;

  @Value("${adapter.eet.publicKeyPath}")
  private String publicKeyPath;

  @Autowired
  private ApplicationContext applicationContext;

  private Key privateKey;
  private PublicKey publicKey;
  //private Certificate cert;
  private Cipher cipher;

  public PublicKey getPublicKey() {
    return publicKey;
  }

  public Key getPrivateKey(String password) {
    if (password.equals("plugh"))
      return privateKey;
    else
      return null;
  }

  public Encryptor() {}

  @PostConstruct
  public void init() {
    try {
      Security.addProvider(new BouncyCastleProvider());
      
      InputStream priv = applicationContext.getResource(privateKeyPath).getInputStream();
      InputStream publ = applicationContext.getResource(publicKeyPath).getInputStream();
      ObjectInputStream ois = new ObjectInputStream(priv);
      privateKey = (Key) ois.readObject();
      ois = new ObjectInputStream(publ);
      publicKey = (PublicKey) ois.readObject();
      cipher = Cipher.getInstance("RSA", "BC");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public String encrypt(String in) throws Exception {
    String result = null;
    try {
      result = encode64(encrypt(in.getBytes()));
    }
    catch (Exception e) { // too many to list
      if (in.length()>25)
      	logger.warn("Warning: Password may already be encrypted!");
    }
    return result;
  }

  public String decrypt(String in) throws Exception {
    String result = null;
    try {
      result = new String(decrypt(decode64(in.getBytes())));
    }
    catch (Exception e) { // too many to list
      if (in.length()<25)
      	logger.warn("Warning: Password may not be encrypted!");
      throw new RuntimeException(e);
    }
    return result;
  }

  private byte[] encrypt(byte[] in) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(in);
  }

  private byte[] decrypt(byte[] in) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(in);
  }

  public final String encode64(byte[] d) {
    if (d == null)
      return null;
    byte data[] = new byte[d.length + 2];
    System.arraycopy(d, 0, data, 0, d.length);
    byte dest[] = new byte[(data.length / 3) * 4];

    // 3-byte to 4-byte conversion
    for (int sidx = 0, didx = 0; sidx < d.length; sidx += 3, didx += 4) {
      dest[didx] = (byte) ((data[sidx] >>> 2) & 077);
      dest[didx + 1] = (byte) ((data[sidx + 1] >>> 4) & 017 | (data[sidx] << 4) & 077);
      dest[didx + 2] = (byte) ((data[sidx + 2] >>> 6) & 003 | (data[sidx + 1] << 2) & 077);
      dest[didx + 3] = (byte) (data[sidx + 2] & 077);
    }

    // 0-63 to ascii printable conversion
    for (int idx = 0; idx < dest.length; idx++) {
      if (dest[idx] < 26)
        dest[idx] = (byte) (dest[idx] + 'A');
      else if (dest[idx] < 52)
        dest[idx] = (byte) (dest[idx] + 'a' - 26);
      else if (dest[idx] < 62)
        dest[idx] = (byte) (dest[idx] + '0' - 52);
      else if (dest[idx] < 63)
        dest[idx] = (byte) '+';
      else
        dest[idx] = (byte) '/';
    }

    // add padding
    for (int idx = dest.length - 1; idx > (d.length * 4) / 3; idx--) {
      dest[idx] = (byte) '=';
    }
    return new String(dest);
  }

  public final byte[] decode64(byte[] data) {
    int tail = data.length;
    while (data[tail - 1] == '=')
      tail--;
    byte dest[] = new byte[tail - data.length / 4];

    // ascii printable to 0-63 conversion
    for (int idx = 0; idx < data.length; idx++) {
      if (data[idx] == '=')
        data[idx] = 0;
      else if (data[idx] == '/')
        data[idx] = 63;
      else if (data[idx] == '+')
        data[idx] = 62;
      else if (data[idx] >= '0' && data[idx] <= '9')
        data[idx] = (byte) (data[idx] - ('0' - 52));
      else if (data[idx] >= 'a' && data[idx] <= 'z')
        data[idx] = (byte) (data[idx] - ('a' - 26));
      else if (data[idx] >= 'A' && data[idx] <= 'Z')
        data[idx] = (byte) (data[idx] - 'A');
    }

    // 4-byte to 3-byte conversion
    int sidx, didx;
    for (sidx = 0, didx = 0; didx < dest.length - 2; sidx += 4, didx += 3) {
      dest[didx] = (byte) (((data[sidx] << 2) & 255) | ((data[sidx + 1] >>> 4) & 3));
      dest[didx + 1] = (byte) (((data[sidx + 1] << 4) & 255) | ((data[sidx + 2] >>> 2) & 017));
      dest[didx + 2] = (byte) (((data[sidx + 2] << 6) & 255) | (data[sidx + 3] & 077));
    }
    if (didx < dest.length) {
      dest[didx] = (byte) (((data[sidx] << 2) & 255) | ((data[sidx + 1] >>> 4) & 3));
    }
    if (++didx < dest.length) {
      dest[didx] = (byte) (((data[sidx + 1] << 4) & 255) | ((data[sidx + 2] >>> 2) & 017));
    }
    return dest;
  }
}