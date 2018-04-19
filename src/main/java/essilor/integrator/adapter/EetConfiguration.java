package essilor.integrator.adapter;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import essilor.integrator.adapter.service.eet.EetConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EetConfiguration {

    private static final Logger logger = Logger.getLogger(EetConfiguration.class);

    @Autowired
    private EetConfigurationService configurationService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SaajSoapMessageFactory eetMessageFactory;

    @Autowired
    private Jaxb2Marshaller eetServiceMarshaller;

    @Bean
    Map<String, EetConfigInfo> eetConfigMap() {
        return configurationService.getEetConfiguration();
    }

    @Bean
    Map<String, WebServiceTemplate> wsTemplateMap() throws Exception {
        String eetUri = configurationService.getEetUri();
        logger.info("eet ur: " + eetUri);
        String keystoreType = configurationService.getEetKeystoreType();
        logger.info("eet keystore type: " + keystoreType);
        Map<String, EetConfigInfo> eetConfig = configurationService.getEetConfiguration();
        Map<String, WebServiceTemplate> wsMap = new HashMap<>();
        for (EetConfigInfo eetConfigInfo : eetConfig.values()) {
            WebServiceTemplate wsTemplate = new WebServiceTemplate(eetMessageFactory);
            wsTemplate.setMarshaller(eetServiceMarshaller);
            wsTemplate.setUnmarshaller(eetServiceMarshaller);
            wsTemplate.setDefaultUri(eetUri);
            wsTemplate.setInterceptors(new ClientInterceptor[] {
                    getWsSecurityInterceptor(eetConfigInfo, keystoreType)
            });
            wsMap.put(eetConfigInfo.getKod(), wsTemplate);
        }
        return wsMap;
    }

    private Wss4jSecurityInterceptor getWsSecurityInterceptor(EetConfigInfo eetConfigInfo, String keystoreType) throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("Signature");
        interceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        interceptor.setSecurementUsername(eetConfigInfo.getKeyAlias());
        interceptor.setSecurementPassword(eetConfigInfo.getKeystorePwd());
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(applicationContext.getResource(eetConfigInfo.getKeystorePath()));
        cryptoFactoryBean.setKeyStorePassword(eetConfigInfo.getKeystorePwd());
        cryptoFactoryBean.setKeyStoreType(keystoreType);
        cryptoFactoryBean.afterPropertiesSet();
        interceptor.setSecurementSignatureCrypto(cryptoFactoryBean.getObject());
        return interceptor;
    }
}

