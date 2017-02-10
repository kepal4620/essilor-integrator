package essilor.integrator.adapter;

import java.math.BigDecimal;
import java.util.Date;

public class EetData {

    private String mode;
    private boolean first;

    private BigDecimal celkTrzba;
    private BigDecimal zaklDan1;
    private BigDecimal dan1;
    private BigDecimal zaklDan2;
    private BigDecimal dan2;
    private Date datTrzby;
    private String dataSourceName;
    private String poradoveCisloDokladu;
    private String skupinaDokladu;
    private String skupinaZakazok;
    private String poradoveCislo;
    private String kod;
    private String password;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public BigDecimal getCelkTrzba() {
        return celkTrzba;
    }

    public void setCelkTrzba(BigDecimal celkTrzba) {
        this.celkTrzba = celkTrzba;
    }

    public BigDecimal getZaklDan1() {
        return zaklDan1;
    }

    public void setZaklDan1(BigDecimal zaklDan1) {
        this.zaklDan1 = zaklDan1;
    }

    public BigDecimal getDan1() {
        return dan1;
    }

    public void setDan1(BigDecimal dan1) {
        this.dan1 = dan1;
    }

    public BigDecimal getZaklDan2() {
        return zaklDan2;
    }

    public void setZaklDan2(BigDecimal zaklDan2) {
        this.zaklDan2 = zaklDan2;
    }

    public BigDecimal getDan2() {
        return dan2;
    }

    public void setDan2(BigDecimal dan2) {
        this.dan2 = dan2;
    }

    public Date getDatTrzby() {
        return datTrzby;
    }

    public void setDatTrzby(Date datTrzby) {
        this.datTrzby = datTrzby;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getPoradoveCisloDokladu() {
        return poradoveCisloDokladu;
    }

    public void setPoradoveCisloDokladu(String poradoveCisloDokladu) {
        this.poradoveCisloDokladu = poradoveCisloDokladu;
    }

    public String getSkupinaDokladu() {
        return skupinaDokladu;
    }

    public void setSkupinaDokladu(String skupinaDokladu) {
        this.skupinaDokladu = skupinaDokladu;
    }

    public String getSkupinaZakazok() {
        return skupinaZakazok;
    }

    public void setSkupinaZakazok(String skupinaZakazok) {
        this.skupinaZakazok = skupinaZakazok;
    }

    public String getMode() {

        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPoradoveCislo() {
        if (poradoveCisloDokladu == null) {
            throw new IllegalStateException("poradoveCisloDokladu is 0");
        }
        if (skupinaZakazok == null) {
            throw new IllegalStateException("skupinaZakazok is 0");
        }
        if (skupinaDokladu == null) {
            throw new IllegalStateException("skupinaDokladu is null");
        }

        return new StringBuilder()
                .append(String.valueOf(poradoveCisloDokladu))
                .append("-")
                .append(skupinaDokladu)
                .append("-")
                .append(skupinaZakazok).toString();
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
