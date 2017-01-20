package essilor.integrator.adapter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import essilor.integrator.adapter.domain.optosys.OrderItem;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderItem item = new OrderItem();
		item.setId(rs.getLong(1));
		item.setZakazka(rs.getLong(2));
		item.setSkupina(rs.getLong(3));
		item.setCi_reg(rs.getLong(4));
		item.setPd(rs.getBigDecimal(5));
		item.setKod(rs.getString(6));
		item.setSph(rs.getString(7));
		item.setCyl(rs.getString(8));
		item.setOs(rs.getString(9));
		item.setAdice(rs.getBigDecimal(10));
		item.setUprava1_perc(rs.getString(11));
		item.setUprava2_perc(rs.getString(12));
		item.setUprava3_perc(rs.getString(13));
		item.setUprava4_perc(rs.getString(14));
		item.setUprava1_kod(rs.getString(15));
		item.setUprava2_kod(rs.getString(16));
		item.setUprava3_kod(rs.getString(17));
		item.setUprava4_kod(rs.getString(18));
		item.setVyska(rs.getBigDecimal(19));
		item.setPrumer(rs.getBigDecimal(20));
		item.setOko(rs.getString(21));
		item.setPrisma1(rs.getBigDecimal(22));
		item.setBaza1(rs.getInt(23));
		item.setPrisma2(rs.getBigDecimal(24));
		item.setBaza2(rs.getInt(25));
		item.setUprava1_typ(rs.getString(26));
		item.setUprava2_typ(rs.getString(27));
		item.setUprava3_typ(rs.getString(28));
		item.setUprava4_typ(rs.getString(29));
		item.setDiameter_h(rs.getString(30));
		item.setDiameter_v(rs.getString(31));
		item.setExt_ci_obj(rs.getString(32));
		item.setNazev(rs.getString(33));
		item.setUprava1_nazev(rs.getString(34));
		item.setUprava2_nazev(rs.getString(35));
		item.setUprava3_nazev(rs.getString(36));
		item.setUprava4_nazev(rs.getString(37));
		item.setSend(rs.getLong(38));
		item.setUser_name(rs.getString(39));
		item.setCi_obj(rs.getLong(40));
		item.setTyp(rs.getString(41));
		item.setStav(rs.getString(42));
		item.setOnline(rs.getString(43));
		item.setVyrobce(rs.getString(44));
		item.setVyr_nazev(rs.getString(45));
		item.setPocet(rs.getInt(46));
		item.setZakr(rs.getBigDecimal(47));
		item.setDco(rs.getInt(48));
		item.setRh(rs.getBigDecimal(49));
		item.setRv(rs.getBigDecimal(50));
		item.setR(rs.getBigDecimal(51));
		item.setCi_pri(rs.getLong(52));
		item.setSklad_pri(rs.getInt(53));
		item.setSpln_pocet(rs.getInt(54));
		item.setUrl(rs.getString(55));
		item.setPdf_name(rs.getString(56));
		item.setVyber(rs.getInt(57));
        /*		
		item.setDt_send(rs.getTimestamp(58));
		item.setDt_receive(rs.getTimestamp(59));
		item.setDt_pdf(rs.getTimestamp(60));
        */
		item.setVyrSklad(rs.getString(61));
		return item;
	}

}
