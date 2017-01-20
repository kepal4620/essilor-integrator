package essilor.integrator.adapter.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.PreparedStatementSetter;

import essilor.integrator.adapter.domain.optosys.OrderItem;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;

public class OrderItemInsertSetter implements PreparedStatementSetter {

	private OrderItem item;
	
	public OrderItemInsertSetter(OrderItem item) {
		this.item = item;
	}
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setLong(1, item.getZakazka());
		ps.setLong(2, item.getSkupina());
		ps.setLong(3, item.getCi_reg());
		ps.setBigDecimal(4, item.getPd());
		ps.setString(5, item.getKod());
		ps.setString(6, item.getSph());
		ps.setString(7, item.getCyl());
		ps.setString(8, item.getOs());
		ps.setBigDecimal(9, item.getAdice());
		ps.setString(10, item.getUprava1_perc());
		ps.setString(11, item.getUprava2_perc());
		ps.setString(12, item.getUprava3_perc());
		ps.setString(13, item.getUprava4_perc());
		ps.setString(14, item.getUprava1_kod());
		ps.setString(15, item.getUprava2_kod());
		ps.setString(16, item.getUprava3_kod());
		ps.setString(17, item.getUprava4_kod());
		ps.setBigDecimal(18, item.getVyska());
		ps.setBigDecimal(19, item.getPrumer());
		ps.setString(20, item.getOko());
		ps.setBigDecimal(21, item.getPrisma1());
		ps.setInt(22, item.getBaza1());
		ps.setBigDecimal(23, item.getPrisma2());
		ps.setInt(24, item.getBaza2());
		ps.setString(25, item.getUprava1_typ());
		ps.setString(26, item.getUprava2_typ());
		ps.setString(27, item.getUprava3_typ());
		ps.setString(28, item.getUprava4_typ());
		ps.setString(29, item.getDiameter_h());
		ps.setString(30, item.getDiameter_v());
		ps.setString(31, item.getExt_ci_obj());
		ps.setString(32, item.getNazev());
		ps.setString(33, item.getUprava1_nazev());
		ps.setString(34, item.getUprava2_nazev());
		ps.setString(35, item.getUprava3_nazev());
		ps.setString(36, item.getUprava4_nazev());

		ps.setLong(37, item.getSend());
		ps.setString(38, item.getUser_name());
		ps.setLong(39, item.getCi_obj());
		ps.setString(40, item.getTyp());
		ps.setString(41, item.getStav());
		ps.setString(42, item.getOnline());
		ps.setString(43, item.getVyrobce());
		ps.setString(44, item.getVyr_nazev());
		ps.setInt(45, item.getPocet());
		ps.setBigDecimal(46, item.getZakr());
		ps.setInt(47, item.getDco());
		ps.setBigDecimal(48, item.getRh());
		ps.setBigDecimal(49, item.getRv());
		ps.setBigDecimal(50, item.getR());
		ps.setLong(51, item.getCi_pri());
		ps.setInt(52, item.getSklad_pri());
		ps.setInt(53, item.getSpln_pocet());
		ps.setString(54, item.getUrl());
		ps.setString(55, item.getPdf_name());
		ps.setInt(56, item.getVyber());
		long timestamp = ServiceCallTimestampHolder.getAsLong();
		ps.setString(
				57,
				OrderDaoImpl.dateFormat.get().format(
						new Date(timestamp)));
		ps.setTimestamp(58, new Timestamp(timestamp));
	}

}
