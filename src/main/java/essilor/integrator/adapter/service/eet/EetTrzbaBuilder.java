package essilor.integrator.adapter.service.eet;

import cz.mfcr.fs.eet.schema.v3.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Component
public class EetTrzbaBuilder {

    private static final Logger logger = Logger.getLogger(EetTrzbaBuilder.class);

    private static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        }
    };

    private static ThreadLocal<DecimalFormat> numberFormatter = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("");
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(2);
            df.setMinimumIntegerDigits(1);
            df.setGroupingSize(0);
            df.setDecimalFormatSymbols(otherSymbols);
            return df;
        }
    };

    @Autowired
    private EetSignService eetSignService;

    public class Builder {


        private Date dateOdesl;
        private boolean overeni;
        private boolean prvniZaslani;

        private String dicPopl;
        private String dicPover;
        private int idProvoz;
        private String idPokl;
        private Date datTrzby;
        private String poradCis;
        private BigDecimal celkTrzba;
        private BigDecimal dan1;
        private BigDecimal zaklDan1;
        private BigDecimal dan2;
        private BigDecimal zaklDan2;
        private int rezim;
        private String kod;


        public Builder() {
        }


        public Builder withDateOdesl(long millis) {
            this.dateOdesl = new Date(millis);
            return this;
        }

        public Builder withOvereni(String mode) {
            if (mode == null) {
                throw new IllegalArgumentException("mode is null");
            }
            if ("A".equals(mode)) {
                this.overeni = true;
            } else if ("N".equals(mode)) {
                this.overeni = false;
            } else {
                throw new IllegalStateException("not possible to setup overeni property");
            }
            return this;
        }

        public Builder withPrvniZaslani(boolean prvniZaslani) {
            this.prvniZaslani = prvniZaslani;
            return this;
        }

        public Builder withDicPopl(String dicPopl) {
            this.dicPopl = dicPopl;
            return this;
        }

        public Builder withDicPover(String dicPover) {
            this.dicPover = dicPover;
            return this;
        }

        public Builder withIdProvoz(int idProvoz) {
            this.idProvoz = idProvoz;
            return this;
        }

        public Builder withIdPokl(String idPokl) {
            this.idPokl = idPokl;
            return this;
        }

        public Builder withDatTrzby(Date datTrzby) {
            this.datTrzby = datTrzby;
            return this;
        }

        public Builder withPoradCis(String poradCis) {
            this.poradCis = poradCis;
            return this;
        }

        public Builder withCelkTrzba(BigDecimal celkTrzba) {
            this.celkTrzba = celkTrzba;
            return this;
        }

        public Builder withDan1(BigDecimal dan1) {
            this.dan1 = dan1;
            return this;
        }

        public Builder withZaklDan1(BigDecimal zaklDan1) {
            this.zaklDan1 = zaklDan1;
            return this;
        }

        public Builder withDan2(BigDecimal dan2) {
            this.dan2 = dan2;
            return this;
        }

        public Builder withZaklDan2(BigDecimal zaklDan2) {
            this.zaklDan2 = zaklDan2;
            return this;
        }

        public Builder withRezim(int rezim) {
            this.rezim = rezim;
            return this;
        }

        public Builder withKod(String kod) {
            this.kod = kod;
            return this;
        }

        public Trzba build() {
            TrzbaHlavickaType trzbaHlavicka = new TrzbaHlavickaType();
            trzbaHlavicka.setUuidZpravy(UUID.randomUUID().toString());
            trzbaHlavicka.setDatOdesl(dateOdesl);
            trzbaHlavicka.setOvereni(overeni);
            trzbaHlavicka.setPrvniZaslani(prvniZaslani);

            TrzbaDataType trzbaData = new TrzbaDataType();
            trzbaData.setDicPopl(this.dicPopl);
            trzbaData.setIdProvoz(this.idProvoz);
            trzbaData.setIdPokl(this.idPokl);
            trzbaData.setPoradCis(this.poradCis);
            trzbaData.setDatTrzby(this.datTrzby);
            trzbaData.setCelkTrzba(this.celkTrzba);
            trzbaData.setDan1(this.dan1);
            trzbaData.setZaklDan1(this.zaklDan1);
            trzbaData.setDan2(this.dan2);
            trzbaData.setZaklDan2(this.zaklDan2);
            trzbaData.setRezim(this.rezim);
            if (dicPover != null && dicPover.trim().length() > 1) {
                trzbaData.setDicPoverujiciho(dicPover.trim());
            }

            Trzba trzba = new Trzba();
            trzba.setHlavicka(trzbaHlavicka);
            trzba.setData(trzbaData);
            trzba.setKontrolniKody(buildTrzbaKontrolniKody());
            return trzba;
        }

        private TrzbaKontrolniKodyType buildTrzbaKontrolniKody() {
            TrzbaKontrolniKodyType trzbaKontrolniKody = new TrzbaKontrolniKodyType();
            byte[] rsa_text = eetSignService.getPkp(getTextToSign(), kod);

            PkpElementType pkpElementType = new PkpElementType();
            pkpElementType.setValue(rsa_text);
            pkpElementType.setCipher(PkpCipherType.RSA_2048);
            pkpElementType.setDigest(PkpDigestType.SHA_256);
            pkpElementType.setEncoding(PkpEncodingType.BASE_64);
            trzbaKontrolniKody.setPkp(pkpElementType);

            BkpElementType bkpElementType = new BkpElementType();
            bkpElementType.setValue(eetSignService.getBkp(rsa_text));
            bkpElementType.setDigest(BkpDigestType.SHA_1);
            bkpElementType.setEncoding(BkpEncodingType.BASE_16);
            trzbaKontrolniKody.setBkp(bkpElementType);

            return trzbaKontrolniKody;
        }

        private String getTextToSign() {
            String s =  new StringBuilder()
                .append(this.dicPopl).append("|")
                .append(this.idProvoz).append("|")
                .append(this.idPokl).append("|")
                .append(this.poradCis).append("|")
                .append(getDatTrzbyAsString()).append("|")
                .append(getCelkTrzabAsString())
                .toString();
            logger.debug("text to sign: " + s);
            return s;
        }

        private String getDatTrzbyAsString() {
            return dateFormatter.get().format(this.datTrzby.getTime());
        }

        private String getCelkTrzabAsString() {
            return numberFormatter.get().format(this.celkTrzba);
        }
    }
}
