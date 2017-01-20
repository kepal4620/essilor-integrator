package essilor.integrator.adapter.domain.optosys;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderItem {

	private long id;
	
	private String orderNumber;
	private String orderItemNumber;
	
	private long zakazka;
	private long skupina;
	private long ci_reg;
	private BigDecimal pd;
	private String kod;
	private String sph;
	private String cyl;
	private String os;
	private BigDecimal adice;
	private String uprava1_perc;
	private String uprava2_perc;
	private String uprava3_perc;
	private String uprava4_perc;
	private String uprava1_kod;
	private String uprava2_kod;
	private String uprava3_kod;
	private String uprava4_kod;
	private String uprava1_typ;
	private String uprava2_typ;
	private String uprava3_typ;
	private String uprava4_typ;
	private String uprava1_nazev;
	private String uprava2_nazev;
	private String uprava3_nazev;
	private String uprava4_nazev;
	private BigDecimal vyska;
	private BigDecimal prumer;
	private String oko;
	private BigDecimal prisma1;
	private int baza1;
	private BigDecimal prisma2;
	private int baza2;
	private String diameter_h;
	private String diameter_v;
	private String ext_ci_obj;
	private String nazev;
	
	private long send;
	private String user_name;
	private long ci_obj;
	private String typ;
	private String stav;
	private String online;
	private String vyrobce;
	private String vyr_nazev;
	private int pocet;
	private BigDecimal zakr;
	private int dco;
	private BigDecimal rh;
    private BigDecimal rv;
	private BigDecimal r;
	private long ci_pri;
	private int sklad_pri;
	private int spln_pocet;
	private String url;
	private String pdf_name;
	private int vyber;
	private Timestamp dt_send;
	private Timestamp dt_receive;
	private Timestamp dt_pdf;
	private String vyrSklad;
	

	public OrderItem() {
	}

	// copy constructor
	public OrderItem(OrderItem item) {
		this.orderNumber = item.orderNumber;
		this.orderItemNumber =  item.orderItemNumber;
		
		this.zakazka = item.zakazka;
		this.skupina = item.skupina;
		this.ci_reg = item.ci_reg;
		this.pd = item.pd;
		this.kod = item.kod;
		this.sph = item.sph;
		this.cyl = item.cyl;
		this.os = item.os;
		this.adice = item.adice;
		this.uprava1_perc = item.uprava1_perc;
		this.uprava2_perc = item.uprava2_perc;
		this.uprava3_perc = item.uprava3_perc;
		this.uprava4_perc = item.uprava4_perc;
		this.uprava1_kod = item.uprava1_kod;
		this.uprava2_kod = item.uprava2_kod;
		this.uprava3_kod = item.uprava3_kod;
		this.uprava4_kod = item.uprava4_kod;
		this.uprava1_typ = item.uprava1_typ;
		this.uprava2_typ = item.uprava2_typ;
		this.uprava3_typ = item.uprava3_typ;
		this.uprava4_typ = item.uprava4_typ;
		this.uprava1_nazev = item.uprava1_nazev;
		this.uprava2_nazev = item.uprava2_nazev;
		this.uprava3_nazev = item.uprava3_nazev;
		this.uprava4_nazev = item.uprava4_nazev;
		this.vyska = item.vyska;
		this.prumer = item.prumer;
		this.oko = item.oko;
		this.prisma1 = item.prisma1;
		this.baza1 = item.baza1;
		this.prisma2 = item.prisma2;
		this.baza2 = item.baza2;
		this.diameter_h = item.diameter_h;
		this.diameter_v = item.diameter_v;
		this.ext_ci_obj = item.ext_ci_obj;
		this.nazev = item.nazev;
		
		this.send = item.send;
		this.user_name = item.user_name;
		this.ci_obj = item.ci_obj;
		this.typ = item.typ;
		this.stav = item.stav;
		this.online = item.online;
		this.vyrobce = item.vyrobce;
		this.vyr_nazev = item.vyr_nazev;
		this.pocet = item.pocet;
		this.zakr = item.zakr;
		this.dco = item.dco;
		this.rh = item.rh;
		this.rv = item.rv;
		this.r = item.r;
		this.ci_pri = item.ci_pri;
		this.sklad_pri = item.sklad_pri;
		this.spln_pocet = item.spln_pocet;
		this.url = item.url;
		this.pdf_name = item.pdf_name;
		this.vyber = item.vyber;
		this.dt_pdf = item.dt_pdf;
		this.dt_receive = item.dt_receive;
		this.dt_send = item.dt_send;
		this.vyrSklad = item.vyrSklad;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getZakazka() {
		return zakazka;
	}

	public void setZakazka(long zakazka) {
		this.zakazka = zakazka;
	}

	public long getSkupina() {
		return skupina;
	}

	public void setSkupina(long skupina) {
		this.skupina = skupina;
	}

	public String getOrderItemNumber() {
		return orderItemNumber;
	}

	public void setOrderItemNumber(String orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getCi_reg() {
		return ci_reg;
	}

	public void setCi_reg(long ci_reg) {
		this.ci_reg = ci_reg;
	}

	public BigDecimal getPd() {
		return pd;
	}

	public void setPd(BigDecimal pd) {
		this.pd = pd;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getSph() {
		return sph;
	}

	public void setSph(String sph) {
		this.sph = sph;
	}

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public BigDecimal getAdice() {
		return adice;
	}

	public void setAdice(BigDecimal adice) {
		this.adice = adice;
	}

	public String getUprava1_perc() {
		return uprava1_perc;
	}

	public void setUprava1_perc(String uprava1_perc) {
		this.uprava1_perc = uprava1_perc;
	}

	public String getUprava2_perc() {
		return uprava2_perc;
	}

	public void setUprava2_perc(String uprava2_perc) {
		this.uprava2_perc = uprava2_perc;
	}

	public String getUprava3_perc() {
		return uprava3_perc;
	}

	public void setUprava3_perc(String uprava3_perc) {
		this.uprava3_perc = uprava3_perc;
	}

	public String getUprava4_perc() {
		return uprava4_perc;
	}

	public void setUprava4_perc(String uprava4_perc) {
		this.uprava4_perc = uprava4_perc;
	}

	public String getUprava1_kod() {
		return uprava1_kod;
	}

	public void setUprava1_kod(String uprava1_kod) {
		this.uprava1_kod = uprava1_kod;
	}

	public String getUprava2_kod() {
		return uprava2_kod;
	}

	public void setUprava2_kod(String uprava2_kod) {
		this.uprava2_kod = uprava2_kod;
	}

	public String getUprava3_kod() {
		return uprava3_kod;
	}

	public void setUprava3_kod(String uprava3_kod) {
		this.uprava3_kod = uprava3_kod;
	}

	public String getUprava4_kod() {
		return uprava4_kod;
	}

	public void setUprava4_kod(String uprava4_kod) {
		this.uprava4_kod = uprava4_kod;
	}

	public String getUprava1_typ() {
		return uprava1_typ;
	}

	public void setUprava1_typ(String uprava1_nazev) {
		this.uprava1_typ = uprava1_nazev;
	}

	public String getUprava2_typ() {
		return uprava2_typ;
	}

	public void setUprava2_typ(String uprava2_nazev) {
		this.uprava2_typ = uprava2_nazev;
	}

	public String getUprava3_typ() {
		return uprava3_typ;
	}

	public void setUprava3_typ(String uprava3_nazev) {
		this.uprava3_typ = uprava3_nazev;
	}

	public String getUprava4_typ() {
		return uprava4_typ;
	}

	public void setUprava4_typ(String uprava4_nazev) {
		this.uprava4_typ = uprava4_nazev;
	}

	public BigDecimal getVyska() {
		return vyska;
	}

	public void setVyska(BigDecimal vyska) {
		this.vyska = vyska;
	}

	public BigDecimal getPrumer() {
		return prumer;
	}

	public void setPrumer(BigDecimal prumer) {
		this.prumer = prumer;
	}

	public String getOko() {
		return oko;
	}

	public void setOko(String oko) {
		this.oko = oko;
	}

	public BigDecimal getPrisma2() {
		return prisma2;
	}

	public void setPrisma2(BigDecimal prisma2) {
		this.prisma2 = prisma2;
	}

	public int getBaza2() {
		return baza2;
	}

	public void setBaza2(int baza2) {
		this.baza2 = baza2;
	}

	public String getDiameter_h() {
		return diameter_h;
	}

	public void setDiameter_h(String diameter_h) {
		this.diameter_h = diameter_h;
	}

	public String getDiameter_v() {
		return diameter_v;
	}

	public void setDiameter_v(String diamater_v) {
		this.diameter_v = diamater_v;
	}

	public String getExt_ci_obj() {
		return ext_ci_obj;
	}

	public void setExt_ci_obj(String ext_ci_obj) {
		this.ext_ci_obj = ext_ci_obj;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public BigDecimal getPrisma1() {
		return prisma1;
	}

	public void setPrisma1(BigDecimal prisma1) {
		this.prisma1 = prisma1;
	}

	public int getBaza1() {
		return baza1;
	}

	public void setBaza1(int baza1) {
		this.baza1 = baza1;
	}

	public String getUprava1_nazev() {
		return uprava1_nazev;
	}

	public void setUprava1_nazev(String uprava1_nazev) {
		this.uprava1_nazev = uprava1_nazev;
	}

	public String getUprava2_nazev() {
		return uprava2_nazev;
	}

	public void setUprava2_nazev(String uprava2_nazev) {
		this.uprava2_nazev = uprava2_nazev;
	}

	public String getUprava3_nazev() {
		return uprava3_nazev;
	}

	public void setUprava3_nazev(String uprava3_nazev) {
		this.uprava3_nazev = uprava3_nazev;
	}

	public String getUprava4_nazev() {
		return uprava4_nazev;
	}

	public void setUprava4_nazev(String uprava4_nazev) {
		this.uprava4_nazev = uprava4_nazev;
	}

	public long getSend() {
		return send;
	}

	public void setSend(long send) {
		this.send = send;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public long getCi_obj() {
		return ci_obj;
	}

	public void setCi_obj(long ci_obj) {
		this.ci_obj = ci_obj;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getStav() {
		return stav;
	}

	public void setStav(String stav) {
		this.stav = stav;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getVyrobce() {
		return vyrobce;
	}

	public void setVyrobce(String vyrobce) {
		this.vyrobce = vyrobce;
	}

	public String getVyr_nazev() {
		return vyr_nazev;
	}

	public void setVyr_nazev(String vyr_nazev) {
		this.vyr_nazev = vyr_nazev;
	}

	public int getPocet() {
		return pocet;
	}

	public void setPocet(int pocet) {
		this.pocet = pocet;
	}

	public BigDecimal getZakr() {
		return zakr;
	}

	public void setZakr(BigDecimal zakr) {
		this.zakr = zakr;
	}

	public int getDco() {
		return dco;
	}

	public void setDco(int dco) {
		this.dco = dco;
	}

	public BigDecimal getRh() {
		return rh;
	}

	public void setRh(BigDecimal rh) {
		this.rh = rh;
	}

	public BigDecimal getRv() {
		return rv;
	}

	public void setRv(BigDecimal rv) {
		this.rv = rv;
	}

	public BigDecimal getR() {
		return r;
	}

	public void setR(BigDecimal r) {
		this.r = r;
	}

	public long getCi_pri() {
		return ci_pri;
	}

	public void setCi_pri(long ci_pri) {
		this.ci_pri = ci_pri;
	}

	public int getSklad_pri() {
		return sklad_pri;
	}

	public void setSklad_pri(int sklad_pri) {
		this.sklad_pri = sklad_pri;
	}

	public int getSpln_pocet() {
		return spln_pocet;
	}

	public void setSpln_pocet(int spln_pocet) {
		this.spln_pocet = spln_pocet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPdf_name() {
		return pdf_name;
	}

	public void setPdf_name(String pdf_name) {
		this.pdf_name = pdf_name;
	}

	public int getVyber() {
		return vyber;
	}

	public void setVyber(int vyber) {
		this.vyber = vyber;
	}

	public Timestamp getDt_send() {
		return dt_send;
	}

	public void setDt_send(Timestamp dt_send) {
		this.dt_send = dt_send;
	}

	public Timestamp getDt_receive() {
		return dt_receive;
	}

	public void setDt_receive(Timestamp dt_receive) {
		this.dt_receive = dt_receive;
	}

	public Timestamp getDt_pdf() {
		return dt_pdf;
	}

	public void setDt_pdf(Timestamp dt_pdf) {
		this.dt_pdf = dt_pdf;
	}

	public String getVyrSklad() {
		return vyrSklad;
	}

	public void setVyrSklad(String vyrSklad) {
		this.vyrSklad = vyrSklad;
	}
	
	
}
