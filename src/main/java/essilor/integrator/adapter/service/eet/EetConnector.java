package essilor.integrator.adapter.service.eet;

import cz.mfcr.fs.eet.schema.v3.Odpoved;
import cz.mfcr.fs.eet.schema.v3.Trzba;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

@Component
public class EetConnector {
    private static final Logger logger = Logger.getLogger(EetConnector.class);

    @Autowired
    private WebServiceTemplate eetWSTemplate;

    public Odpoved sendTrzba(Trzba trzba) {
        if (trzba == null) {
            throw new IllegalArgumentException("trzba is null");
        }
        JAXBElement<Odpoved> root =  (JAXBElement<Odpoved>) eetWSTemplate.marshalSendAndReceive(trzba,
                new SoapActionCallback("http://fs.mfcr.cz/eet/OdeslaniTrzby"));
        return root.getValue();
    }
}
