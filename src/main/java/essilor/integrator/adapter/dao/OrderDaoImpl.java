package essilor.integrator.adapter.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import essilor.integrator.adapter.domain.optosys.Order;
import essilor.integrator.adapter.domain.optosys.OrderItem;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;
import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.AdapterRequest.MethodName;
import essilor.integrator.adapter.Result;

public class OrderDaoImpl implements OrderDao {

	public static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return fmt;
		}
	};

	private static final String GET_ORDER_ITEM_SQL = "select id, zakazka, skupina, ci_reg, pd, kod, sph, cyl, os, adice, uprava1_perc, uprava2_perc, uprava3_perc,"
			+ "uprava4_perc, uprava1_kod, uprava2_kod, uprava3_kod, uprava4_kod, vyska, prumer, oko, prisma1, baza1, prisma2, baza2,"
			+ " uprava1_typ, uprava2_typ, uprava3_typ, uprava4_typ, diameter_h, diameter_v, ext_ci_obj,"
			+ "nazev, uprava1_nazev, uprava2_nazev, uprava3_nazev, uprava4_nazev, send, user_name, ci_obj, typ, stav, online, vyrobce, vyr_nazev, "
			+ "pocet, zakr, dco, rh, rv, r, ci_pri, sklad_pri, spln_pocet, url, pdf_name, vyber, dt_send, dt_receive, dt_pdf, vyr_sklad from se_obj_polozky "
			+ " where id=?";

	private static final String LOG_UPLOAD_ORDER_RESULT_SQL = "insert into se_obj_kom_log"
			+ " (write_time,user_name,vyrobca,method_name,time_stamp,url,session_id,unique_id,processed,"
			+ "direction,zakazka,skupina,ci_obj,err_code,err_text,xml_input,xml_output,pdf_output,db_datasource,db_name,db_host,order_id,supplier_id,action_type) "
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String GET_LENS_NAME_SQL = "select nazev from c_typy_skel where vyrobce=? and kod=?";

	private static final String GET_COATING_NAME_SQL = "select nazev from c_upravy_skel where vyrobce=? and kod=?";

	private static final String GET_ORDER_KEYS_SQL = "select id_obj_p, id_obj_l from se_obj_rel_zak where zakazka=? and skupina=?";

	private static final String UPDATE_ORDER_ITEM_SQL = "update se_obj_polozky set zakazka=?, skupina=?, ci_reg=?, pd=?, kod=?, sph=?, cyl=?, os=?, adice=?, uprava1_perc=?, uprava2_perc=?, uprava3_perc=?,"
			+ "uprava4_perc=?, uprava1_kod=?, uprava2_kod=?, uprava3_kod=?, uprava4_kod=?, vyska=?, prumer=?, oko=?, prisma1=?, baza1=?, prisma2=?, baza2=?,"
			+ " uprava1_typ=?, uprava2_typ=?, uprava3_typ=?, uprava4_typ=?, diameter_h=?, diameter_v=?, ext_ci_obj=?,"
			+ "nazev=?, uprava1_nazev=?, uprava2_nazev=?, uprava3_nazev=?, uprava4_nazev=?, send=?, user_name=?, ci_obj=?, typ=?, stav=?, online=?, vyrobce=?, vyr_nazev=?, "
			+ "pocet=?, zakr=?, dco=?, rh=?, rv=?, r=?, ci_pri=?, sklad_pri=?, spln_pocet=?, url=?, pdf_name=?, vyber=?, write_time=?, dt_receive=? where id=?";

	private static final String INSERT_ORDER_ITEM_SQL = "insert into se_obj_polozky (zakazka, skupina, ci_reg, pd, kod, sph, cyl, os, adice, uprava1_perc, uprava2_perc, uprava3_perc,"
			+ "uprava4_perc, uprava1_kod, uprava2_kod, uprava3_kod, uprava4_kod, vyska, prumer, oko, prisma1, baza1, prisma2, baza2,"
			+ " uprava1_typ, uprava2_typ, uprava3_typ, uprava4_typ, diameter_h, diameter_v, ext_ci_obj,"
			+ "nazev, uprava1_nazev, uprava2_nazev, uprava3_nazev, uprava4_nazev, send, user_name, ci_obj, typ, stav, online, vyrobce, vyr_nazev, "
			+ "pocet, zakr, dco, rh, rv, r, ci_pri, sklad_pri, spln_pocet, url, pdf_name, vyber, write_time, dt_receive) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_LEFT_ITEM_ID_SQL = "update se_obj_rel_zak set write_time=?,id_obj_l=? where zakazka=? and skupina=?";

	private static final String UPDATE_RIGHT_ITEM_ID_SQL = "update se_obj_rel_zak set write_time=?,id_obj_r=? where zakazka=? and skupina=?";

	private static final String GET_ITEM_ID_SQL = "select id from se_obj_polozky where zakazka=? and skupina=? and oko=?";

	private static final String UPDATE_ORDER_AFTER_UPLOAD_SQL = "update se_obj_polozky set write_time=?,online='A',url=?,dt_send=? where id=?";

	private static final String UPDATE_ORDER_AFTER_UPLOAD_ORDER_BY_ACTION_SQL = "update se_obj_polozky set write_time=?,online='A',url=?,stav=?,dt_send=?,ext_ci_obj=? where id=?";

	private static final String UPDATE_ORDER_AFTER_GET_AS_PDF = "update se_obj_polozky set write_time=?,pdf_name=?,dt_pdf=? where id=?";

	private JdbcTemplate jdbcTemplate;

	public OrderDaoImpl(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public Order getOrder(String zakazka, String skupina, String objednavka) {

		Map<String, Object> m = jdbcTemplate.queryForMap(GET_ORDER_KEYS_SQL,
				new Object[] { Long.valueOf(zakazka), Long.valueOf(skupina) });

		OrderItem right = null, left = null;
		if (((Long) m.get("ID_OBJ_P")) != 0) {
			right = this.jdbcTemplate.queryForObject(GET_ORDER_ITEM_SQL,
					new Object[] { m.get("ID_OBJ_P") },
					new OrderItemRowMapper());
		}

		if (((Long) m.get("ID_OBJ_L")) != 0) {
			left = this.jdbcTemplate.queryForObject(GET_ORDER_ITEM_SQL,
					new Object[] { m.get("ID_OBJ_L") },
					new OrderItemRowMapper());
		}

		Order order = new Order();
		if (objednavka != null && !objednavka.isEmpty()) {
			order.setObjednavka(Long.valueOf(objednavka));
		}
		order.setSkupina(Long.valueOf(skupina));
		order.setZakazka(Long.valueOf(zakazka));
		order.setRightItem(right);
		order.setLeftItem(left);
		return order;
	}

	@Override
	public void logResult(final AdapterRequest request, final Result result) {
		this.jdbcTemplate.update(LOG_UPLOAD_ORDER_RESULT_SQL,
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setTimestamp(1, new Timestamp(ServiceCallTimestampHolder.getAsLong()));
						ps.setString(2, result.getUsername()); // USERNAME
						ps.setString(3, result.getManufacturer()); // VYROBCA
						ps.setString(4, result.getMethodName()); // METHOD_NAME
						ps.setString(
								5,
								dateFormat.get().format(
										new Date(ServiceCallTimestampHolder.getAsLong()))); // TIME_STAMP
						ps.setString(6, result.getUrl()); // URL
						ps.setString(7, result.getSessionId()); // SESSION_ID
						ps.setString(8, result.getUniqueId()); // UNIQUE_ID
						ps.setInt(9, result.getProcessed()); // PROCESSED
						ps.setString(10, result.getDirection()); // DIRECTION
						ps.setLong(11, Long.valueOf(result.getOrderNumber())); // ZAKAZKA
						ps.setLong(12, Long.valueOf(result.getOrderGroup())); // SKUPINA
						ps.setLong(13,
								Long.valueOf(result.getPurchaseOrderNumber())); // OBJEDNAVKA
						ps.setString(14, result.getErrorCode()); // ERROR_CODE
						ps.setString(15, result.getErrorText()); // ERROR_TEXT
						ps.setString(16, result.getXmlInput()); // XML_INPUT
						// XML_OUTPUT
						if (request.getMethodName().equals(
								MethodName.GetOrderAsPDFByPoNum)) {
							ps.setBytes(17, null);
						} else {
							ps.setString(17, result.getXmlOutput());
						}
						//PDF_OUTPUT
						if (request.getMethodName().equals(
								MethodName.GetOrderAsPDFByPoNum) && request.isPersistPdfInLog()) {
							ps.setBytes(18, result.getPdf());
						} else {
							ps.setString(18, null);
						}
						ps.setString(19, request.getDataSourceName()); // DB_DATASOURCE
						ps.setString(20, ""); // TODO DB_NAME
						ps.setString(21, ""); // TODO DB_HOST
						ps.setString(22, result.getOrderId()); // ORDER_ID
						ps.setString(23, request.getSloId()); // SUPPLIER_ID
						ps.setString(24, (request.getActionType() != null) ? request.getActionType().name() : null); // ACTION_TYPE
					}
				});
	}

	@Override
	public void updateOrderAfterGetOrder(Order order) {
		if (order.getLeftItem() != null && order.getLeftItem().getId() == 0) {
			insertOrderItem(order, order.getLeftItem());
		} else if (order.getLeftItem() != null) {
			updateOrderItem(order.getLeftItem());
		}
		if (order.getRightItem() != null && order.getRightItem().getId() == 0) {
			insertOrderItem(order, order.getRightItem());
		} else if (order.getRightItem() != null) {
			updateOrderItem(order.getRightItem());
		}
	}

	protected void updateOrderItem(OrderItem orderItem) {
		jdbcTemplate.update(UPDATE_ORDER_ITEM_SQL, new OrderItemUpdateSetter(
				orderItem));
	}

	protected void insertOrderItem(Order order, OrderItem orderItem) {
		jdbcTemplate.update(INSERT_ORDER_ITEM_SQL, new OrderItemInsertSetter(
				orderItem));
		long id = jdbcTemplate.queryForLong(GET_ITEM_ID_SQL,
				new Object[] { orderItem.getZakazka(), orderItem.getSkupina(),
						orderItem.getOko() });
		if ("P".equals(orderItem.getOko())) {
			jdbcTemplate.update(UPDATE_RIGHT_ITEM_ID_SQL,
					new Object[] { new Date(ServiceCallTimestampHolder.getAsLong()), id,
							order.getZakazka(), order.getSkupina() });
		} else {
			jdbcTemplate.update(UPDATE_LEFT_ITEM_ID_SQL,
					new Object[] { new Date(ServiceCallTimestampHolder.getAsLong()), id,
							order.getZakazka(), order.getSkupina() });
		}
	}

	@Override
	public String getLensName(String manufacturerCode, String code) {
		List<String> l = jdbcTemplate.queryForList(GET_LENS_NAME_SQL,
				new Object[] { manufacturerCode, code }, String.class);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return code;
		}
	}

	@Override
	public String getCoatingName(String manufacturerCode, String code) {
		List<String> l = jdbcTemplate.queryForList(GET_COATING_NAME_SQL,
				new Object[] { manufacturerCode, code }, String.class);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return code;
		}
	}

	@Override
	public void updateOrderAfterUpload(String zakazka, String skupina,
			String url) {
		Map<String, Object> m = jdbcTemplate.queryForMap(GET_ORDER_KEYS_SQL,
				new Object[] { Long.valueOf(zakazka), Long.valueOf(skupina) });

		long timestamp = ServiceCallTimestampHolder.getAsLong();
		for (Object o : m.values()) {
			if (o != null) {
				jdbcTemplate.update(UPDATE_ORDER_AFTER_UPLOAD_SQL,
						new Object[] { new Date(timestamp), url,
								new Timestamp(timestamp), o });
			}
		}
	}

	@Override
	public void updateOrderAfterGetAsPDF(String zakazka, String skupina,
			String pathToFile) {
		Map<String, Object> m = jdbcTemplate.queryForMap(GET_ORDER_KEYS_SQL,
				new Object[] { Long.valueOf(zakazka), Long.valueOf(skupina) });

		long timestamp = ServiceCallTimestampHolder.getAsLong();
		for (Object o : m.values()) {
			if (o != null) {
				jdbcTemplate.update(UPDATE_ORDER_AFTER_GET_AS_PDF,
						new Object[] { new Date(System.currentTimeMillis()),
								pathToFile, new Timestamp(timestamp), o });
			}
		}
	}

	@Override
	public void updateOrderAfterUploadOrderByAction(String zakazka,
			String skupina, String url, String stav, String orderId) {
		
		Map<String, Object> m = jdbcTemplate.queryForMap(GET_ORDER_KEYS_SQL,
				new Object[] { Long.valueOf(zakazka), Long.valueOf(skupina) });

		long timestamp = ServiceCallTimestampHolder.getAsLong();
		for (Object o : m.values()) {
			if (o != null) {
				jdbcTemplate.update(UPDATE_ORDER_AFTER_UPLOAD_ORDER_BY_ACTION_SQL,
						new Object[] { new Date(timestamp), 
						              url,
						              stav,
						              new Timestamp(timestamp), orderId, o});
			}
		}
	}

}
