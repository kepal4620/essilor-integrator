package essilor.integrator.adapter.service.eet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.*;
import java.security.cert.Certificate;

@Component
public class EetSignService {

    private static final String SIGN_ALGORITHM = "SHA256withRSA";

    @Value("${adapter.eet.keystoreType}")
    private String keystoreType;

    @Value("${adapter.eet.keystorePath}")
    private String keystorePath;

    @Value("${adapter.eet.keystorePassword}")
    private String keystorePassword;

    @Value("${adapter.eet.keyAlias}")
    private String keyAlias;

    private KeyStore keystore;

    @PostConstruct
    public void postConstruct() {
        try {
            keystore = KeyStore.getInstance(keystoreType);
            keystore.load(FileUtils.openInputStream(new File(keystorePath)), keystorePassword.toCharArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getPkp(String textToSign) {
        if (textToSign == null) {
            throw new IllegalArgumentException("textToSign is null");
        }
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initSign((PrivateKey)keystore.getKey(keyAlias, keystorePassword.trim().toCharArray()));
            signature.update(textToSign.getBytes("UTF-8"));
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getBkp(byte[]  rsa_text) {
        if (rsa_text == null) {
            throw new IllegalArgumentException("rsa_text is null");
        }
        String sha = DigestUtils.sha1Hex(rsa_text);
        String shaDash = sha.replaceAll(
                "(\\w{8})(\\w{8})(\\w{8})(\\w{8})(\\w{8})",
                "$1-$2-$3-$4-$5");
        return shaDash;
    }
}
