package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

@Component
public class EetSignService {

    private static final String SIGN_ALGORITHM = "SHA256withRSA";

    private Map<String, EetConfigInfo> eetConfigMap;
    private Map<String, KeyStore> keyStoreMap;

    @Value("${adapter.eet.keystoreType}")
    private String keystoreType;

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private void setEetConfigMap(Map<String, EetConfigInfo> eetConfigMap) {
        this.eetConfigMap = eetConfigMap;
    }


    @PostConstruct
    public void postConstruct() {
        try {
            keyStoreMap = new HashMap<>();
            for (EetConfigInfo eetConfigInfo : eetConfigMap.values()) {
                KeyStore keystore = KeyStore.getInstance(keystoreType);
                keystore.load(applicationContext.getResource(eetConfigInfo.getKeystorePath()).getInputStream(),
                        eetConfigInfo.getKeystorePwd().toCharArray());
                keyStoreMap.put(eetConfigInfo.getKod(), keystore);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getPkp(String textToSign, String kod) {
        if (textToSign == null) {
            throw new IllegalArgumentException("textToSign is null");
        }
        if (kod == null) {
            throw new IllegalArgumentException("kod is null");
        }

        KeyStore keystore = keyStoreMap.get(kod);
        if (keystore == null) {
            throw new IllegalStateException("keystore is null");
        }
        EetConfigInfo eetConfigInfo = eetConfigMap.get(kod);
        if (eetConfigInfo == null) {
            throw new IllegalStateException("eetConfigInfo is null");
        }
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initSign((PrivateKey)keystore.getKey(eetConfigInfo.getKeyAlias(),
                    eetConfigInfo.getKeystorePwd().trim().toCharArray()));
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
