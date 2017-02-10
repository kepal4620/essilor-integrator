package essilor.integrator.adapter.service.eet;

import cz.mfcr.fs.eet.schema.v3.Odpoved;
import cz.mfcr.fs.eet.schema.v3.Trzba;
import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import java.util.Map;

@Component
public class EetConnector {
    private static final Logger logger = Logger.getLogger(EetConnector.class);

    private Map<String, WebServiceTemplate> wsTemplateMap;

    @Resource
    private void setWsTemplateMap(Map<String, WebServiceTemplate> wsTemplateMap) {
        this.wsTemplateMap = wsTemplateMap;
    }


    public Odpoved sendTrzba(Trzba trzba, String kod) {
        if (trzba == null) {
            throw new IllegalArgumentException("trzba is null");
        }
        if (kod == null) {
            throw new IllegalArgumentException("kod is null");
        }
        WebServiceTemplate wsTemplate = wsTemplateMap.get(kod);
        if (wsTemplate == null) {
            throw new IllegalStateException("wsTemplate is null for kod: " + kod);
        }

        JAXBElement<Odpoved> root =  (JAXBElement<Odpoved>) wsTemplate.marshalSendAndReceive(trzba,
                new SoapActionCallback("http://fs.mfcr.cz/eet/OdeslaniTrzby"));
        return root.getValue();
    }
}
