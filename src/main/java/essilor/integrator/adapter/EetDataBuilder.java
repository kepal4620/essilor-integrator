package essilor.integrator.adapter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EetDataBuilder {

    public static final String DATE_TIME_MASK = "yyyy-MM-dd HH:mm:ss";
    private String request;

    private EetDataBuilder() {
    }

    public static EetDataBuilder newInstance() {
        return new EetDataBuilder();
    }

    public EetDataBuilder withRequest(String request) {
        this.request = request;
        return this;
    }

    public EetData build() throws ParseException {
        if (request == null) {
            throw new IllegalStateException("request is null");
        }
        EetData eetData = new EetData();

        eetData.setMode(request.substring(3,4));

        if ("A".equals(request.substring(4,5))) {
            eetData.setFirst(true);
        } else if ("N".equals(request.substring(4,5))) {
           eetData.setFirst(false);
        } else {
            throw new IllegalStateException("wrong first sending character: " + request.substring(4,5));
        }

        eetData.setCelkTrzba(new BigDecimal(request.substring(5,17).trim()));
        eetData.setZaklDan1(new BigDecimal(request.substring(17,29).trim()));
        eetData.setDan1(new BigDecimal(request.substring(29,41).trim()));
        eetData.setZaklDan2(new BigDecimal(request.substring(41,53).trim()));
        eetData.setDan2(new BigDecimal(request.substring(53,65).trim()));
        eetData.setDatTrzby(new SimpleDateFormat(DATE_TIME_MASK).parse(request.substring(65,84).trim()));
        eetData.setPoradoveCisloDokladu(request.substring(84,94).trim());
        eetData.setSkupinaDokladu(request.substring(94,97).trim());
        eetData.setSkupinaZakazok(request.substring(97,99).trim());
        eetData.setDataSourceName(request.substring(99,129).trim());
        eetData.setKod(request.substring(129,131).trim());
        return eetData;
    }
}
