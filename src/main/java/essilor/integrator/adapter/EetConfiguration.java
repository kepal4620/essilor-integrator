package essilor.integrator.adapter;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import essilor.integrator.adapter.service.eet.EetConfigurationService;
import org.apache.ws.security.components.crypto.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EetConfiguration {

    @Autowired
    private EetConfigurationService configurationService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SaajSoapMessageFactory messageFactory;

    @Autowired
    private Jaxb2Marshaller eetServiceMarshaller;

    @Value("${adapter.eet.keystoreType}")
    private String keystoreType;

    @Value("${adapter.uri.eet}")
    private String eetUri;

    @Bean
    Map<String, String>  myMap() {
        System.out.println("id pokl: " + configurationService.getIdPokl());
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        return myMap;
    }

    @Bean
    Map<String, WebServiceTemplate> wsMap() throws Exception {
        Map<String, EetConfigInfo> eetConfig = configurationService.getEetConfiguration();
        Map<String, WebServiceTemplate> wsMap = new HashMap<>();
        for (EetConfigInfo eetConfigInfo : eetConfig.values()) {
            WebServiceTemplate wsTemplate = new WebServiceTemplate(messageFactory);
            wsTemplate.setMarshaller(eetServiceMarshaller);
            wsTemplate.setUnmarshaller(eetServiceMarshaller);
            wsTemplate.setDefaultUri(eetUri);
            wsTemplate.setInterceptors(new ClientInterceptor[] {
                    getWsSecurityInterceptor(eetConfigInfo)
            });
            wsMap.put(eetConfigInfo.getId_provoz(), wsTemplate);
        }
        return wsMap;
    }

    private Wss4jSecurityInterceptor getWsSecurityInterceptor(EetConfigInfo eetConfigInfo) throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("Signature");
        interceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        interceptor.setSecurementUsername(eetConfigInfo.getKeyAlias());
        interceptor.setSecurementPassword(eetConfigInfo.getKeystorePwd());
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        CryptoFactoryBean crypto = new CryptoFactoryBean();
        crypto.setKeyStorePassword(eetConfigInfo.getKeystorePwd());
        crypto.setKeyStoreType(keystoreType);
        crypto.setKeyStoreLocation(applicationContext.getResource(eetConfigInfo.getKeystorePath()));
        interceptor.setSecurementSignatureCrypto((Crypto) crypto.getObject());
        return interceptor;
    }
}

