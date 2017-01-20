package essilor.integrator.adapter.service.eet;

import cz.mfcr.fs.eet.schema.v3.Odpoved;
import cz.mfcr.fs.eet.schema.v3.Trzba;
import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.service.ResultBuilder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.swing.border.EtchedBorder;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@Component
public class EetResultBuilder  {

    @Autowired
    private Jaxb2Marshaller eetServiceMarshallerFormattedOutput;

    @Value("${adapter.username}")
    private String username;

    public class Builder extends ResultBuilder {
        private Trzba trzba;
        private Odpoved odpoved;

        public Builder() {}

        public Builder withAdapterRequest(AdapterRequest adapterRequest) {
            if (adapterRequest == null || adapterRequest.getEetData() == null) {
                throw new IllegalStateException("wrong adapterRequest");
            }
            super.request = adapterRequest;
            return this;
        }

        public Builder withTrzba(Trzba trzba) {
            this.trzba = trzba;
            return this;
        }

        public Builder withOdpoved(Odpoved odpoved) {
            this.odpoved = odpoved;
            return this;
        }

        @Override
        protected Result newResult() {
            return new EetResult();
        }

        @Override
        protected void build(Result result) {
            if (!(result instanceof EetResult)) {
                throw new IllegalStateException("wrong result instance");
            }
            if (request == null || request.getEetData() == null) {
                throw new IllegalStateException("adapterRequest or eetData is null");
            }
            if (trzba == null) {
                throw new IllegalStateException("trzba is null");
            }
            if (odpoved == null) {
                throw new IllegalStateException("odpoved is null");
            }

            EetResult eetResult = (EetResult) result;

            eetResult.setFirstSend(request.getEetData().isFirst() ? "A" : "N");
            eetResult.setVerification(request.getEetData().getMode());
            eetResult.setOrderNumber(request.getEetData().getPoradoveCisloDokladu());
            eetResult.setOrderGroup(request.getEetData().getSkupinaDokladu());
            eetResult.setPurchaseOrderNumber(request.getEetData().getSkupinaZakazok());
            eetResult.setFik(odpoved.getPotvrzeni() == null ? "" : odpoved.getPotvrzeni().getFik());
            eetResult.setPkp(Base64.encodeBase64String(trzba.getKontrolniKody().getPkp().getValue()));
            eetResult.setBkp(trzba.getKontrolniKody().getBkp().getValue());
            eetResult.setXmlInput(marshal(trzba));
            eetResult.setXmlOutput(marshal(odpoved));
            eetResult.setUuid(trzba.getHlavicka().getUuidZpravy());
            eetResult.setUsername(username);
            eetResult.setDirection("S");
            eetResult.setErrorCode(odpoved.getChyba() == null ? "" : String.valueOf(odpoved.getChyba().getKod()));
            eetResult.setErrorText(odpoved.getChyba() == null ? "" :odpoved.getChyba().getContent());
            eetResult.setDataSourceName(request.getEetData().getDataSourceName());
        }

        private String marshal(Object o) {
            StringWriter sw = new StringWriter();
            eetServiceMarshallerFormattedOutput.marshal(o, new StreamResult(sw));
            return sw.toString();
        }
    }
}
