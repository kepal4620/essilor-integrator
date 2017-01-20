package essilor.integrator.adapter.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

public class BkpTest {

    private KeyStore keystore;
    private String password="eet";

    @Test
    public void testBkp() throws Exception {

        //String alias = "cz683555118";
        String alias = "1";

        String plaintext = "CZ72080043|181|00/2535/CN58|0/2482/IE25|2016‐12‐07T22:01:00+01:00|87988.00";


        Signature signature = Signature.getInstance("SHA256withRSA");
        final Certificate cert = keystore.getCertificate(alias);
        final PublicKey publicKey = cert.getPublicKey();
        PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, password.toCharArray());
        signature.initSign(privateKey);
        signature.update(plaintext.getBytes("UTF-8"));
        byte[] rsa_text = signature.sign();

        String sha = DigestUtils.sha1Hex(rsa_text);
        String shaDash = sha.replaceAll(
                "(\\w{8})(\\w{8})(\\w{8})(\\w{8})(\\w{8})",
                "$1-$2-$3-$4-$5");

        System.out.println("sha1: " + sha);
        System.out.println("shaDash: " + shaDash);
    }


    @Before
    public void before() throws Exception {
        keystore = KeyStore.getInstance("pkcs12");
        keystore.load(FileUtils.openInputStream(new File("src/main/resources/cert/EET_CA1_Playground-CZ00000019.p12")), password.toCharArray());
        //keystore.load(FileUtils.openInputStream(new File("src/main/resources/cert/01000005.p12")), password.toCharArray());
    }

}
