package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.Result;

public class EetResult extends Result {

    private String verification;
    private String firstSend;
    private String fik;
    private String uuid;
    private String pkp;
    private String bkp;

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getFirstSend() {
        return firstSend;
    }

    public void setFirstSend(String firstSend) {
        this.firstSend = firstSend;
    }

    public String getFik() {
        return fik;
    }

    public void setFik(String fik) {
        this.fik = fik;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPkp() {
        return pkp;
    }

    public void setPkp(String pkp) {
        this.pkp = pkp;
    }

    public String getBkp() {
        return bkp;
    }

    public void setBkp(String bkp) {
        this.bkp = bkp;
    }
}
