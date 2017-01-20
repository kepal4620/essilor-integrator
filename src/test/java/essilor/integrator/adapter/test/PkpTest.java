package essilor.integrator.adapter.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class PkpTest {

    private KeyStore keystore;
    //private String password="eet";
    private String password="Brelis6859eet";

    @Test
    public void testPkp() throws Exception {

        //String alias = "cz683555118";
        String alias = "1";

        String plaintext="CZ72080043|181|00/2535/CN58|0/2482/IE25|2016‐12‐07T22:01:00+01:00|87988.00";


        Signature signature = Signature.getInstance("SHA256withRSA");
//        final Certificate cert = keystore.getCertificate(alias);
//        final PublicKey publicKey = cert.getPublicKey();
        Enumeration<String> e = keystore.aliases();
        while(e.hasMoreElements()) {
            System.out.println("alias: " + e.nextElement());
        }

        PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, password.toCharArray());
        signature.initSign(privateKey);
        signature.update(plaintext.getBytes("UTF-8"));
        byte[] rsa_text = signature.sign();
        String base64 = Base64.encodeBase64String(rsa_text);
        System.out.println("signature class name: " + signature.getClass().getCanonicalName());
    }

    @Before
    public void before() throws Exception {
        keystore = KeyStore.getInstance("pkcs12");
        keystore.load(FileUtils.openInputStream(new File("d:/temp/c/cert/2643013551.p12")), password.toCharArray());
    }

    public static void main(String[] args) throws Exception {
        PkpTest p = new PkpTest();
        p.before();
        p.testPkp();
    }
}
