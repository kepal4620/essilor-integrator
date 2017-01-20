package essilor.integrator.adapter.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import essilor.integrator.adapter.AdapterRequest.Manufacturer;
import essilor.integrator.adapter.dao.OrderDao;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.domain.b2boptic.Coating;
import essilor.integrator.adapter.domain.b2boptic.Sides;
import essilor.integrator.adapter.domain.b2boptic.Pair.Lens;
import essilor.integrator.adapter.domain.optosys.Order;
import essilor.integrator.adapter.domain.optosys.OrderItem;

public class OrderBuilder {

	private static final String FORMAT_1 = "format_1";
	private static final String FORMAT_2 = "format_2";

	private static ThreadLocal<Map<String, DecimalFormat>> formatters = new ThreadLocal<Map<String, DecimalFormat>>() {
		@Override
		protected Map<String, DecimalFormat> initialValue() {
			Map<String, DecimalFormat> map = new HashMap<String, DecimalFormat>();

			DecimalFormat format = new DecimalFormat();
			DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
			symbols.setDecimalSeparator(',');
			symbols.setMinusSign('-');
			format.setDecimalFormatSymbols(symbols);
			format.setMaximumFractionDigits(2);
			format.setMinimumFractionDigits(2);
			format.setPositivePrefix("+");
			map.put(FORMAT_1, format);

			format = new DecimalFormat();
			format.setMaximumFractionDigits(0);
			format.setMinimumFractionDigits(0);
			map.put(FORMAT_2, format);

			return map;
		}
	};

	@Autowired
	private OrderDao dao;

	public Order build(B2BOptic b2bOptic, Manufacturer manufacturer) {
		String referenceNumber = b2bOptic.getItems().getItem().get(0)
				.getReferenceNo();
		String[] parts = referenceNumber.split("-");
		Order order = dao.getOrder(parts[0], parts[1], "");
		setOrder(order, b2bOptic, manufacturer);
		return order;
	}

	protected void setOrder(Order order, B2BOptic b2bOptic,
			Manufacturer manufacturer) {

		List<Lens> lensList = b2bOptic.getItems().getItem().get(0).getPair()
				.getLens();
		for (Lens lens : lensList) {
			OrderItem orderItem;
			if (Sides.RIGHT.equals(lens.getSide())) {
				orderItem = order.getRightItem();
				if (orderItem == null) {
					orderItem = new OrderItem(order.getLeftItem());
					order.setRightItem(orderItem);
					orderItem.setOko("P");
				}
			} else if (Sides.LEFT.equals(lens.getSide())) {
				orderItem = order.getLeftItem();
				if (orderItem == null) {
					orderItem = new OrderItem(order.getRightItem());
					order.setLeftItem(orderItem);
					orderItem.setOko("L");
				}
			} else {
				throw new RuntimeException(
						"Lens side not specified in order(orderNumber-orderGroup): "
								+ order.getZakazka() + "-" + order.getSkupina());
			}
			setOrderItem(orderItem, lens, manufacturer);
		}

		// se_obj_polozky.EXT_CI_OBJ
		if (order.getLeftItem() != null) {
			order.getLeftItem().setExt_ci_obj(
					b2bOptic.getHeader().getCustomersOrderId().trim());
		}
		if (order.getRightItem() != null) {
			order.getRightItem().setExt_ci_obj(
					b2bOptic.getHeader().getCustomersOrderId().trim());
		}

	}

	protected void setOrderItem(OrderItem orderItem, Lens lens,
			Manufacturer manufacturer) {
		// commercial code
		if (!lens.getCommercialCode().equals(orderItem.getKod())) {
			orderItem.setNazev(dao.getLensName(manufacturer.getCode(),
					lens.getCommercialCode()));
			orderItem.setKod(lens.getCommercialCode());
		}
		// sphere
		orderItem.setSph(formatters.get().get(FORMAT_1)
				.format(lens.getRxData().getSphere()));

		// cylinder
		if (lens.getRxData().getCylinder() != null) {
			orderItem.setCyl(formatters.get().get(FORMAT_1)
					.format(lens.getRxData().getCylinder().getPower()));
			orderItem.setOs(formatters.get().get(FORMAT_2)
					.format(lens.getRxData().getCylinder().getAxis()));

		} else {
			orderItem.setCyl("");
			orderItem.setOs("");
		}

		// addition
		if (lens.getRxData().getAddition() != null) {
			orderItem.setAdice(new BigDecimal(lens.getRxData().getAddition()));
		} else {
			orderItem.setAdice(BigDecimal.ZERO);
		}

		// prism
		if (lens.getRxData().getPrism().size() > 0
				&& lens.getRxData().getPrism().get(0) != null) {
			orderItem.setPrisma1(new BigDecimal(lens.getRxData().getPrism()
					.get(0).getPower()));
			orderItem.setBaza1(lens.getRxData().getPrism().get(0).getBase()
					.intValue());
		} else {
			orderItem.setPrisma1(BigDecimal.ZERO);
			orderItem.setBaza1(0);
		}

		if (lens.getRxData().getPrism().size() > 1
				&& lens.getRxData().getPrism().get(1) != null) {
			orderItem.setPrisma2(new BigDecimal(lens.getRxData().getPrism()
					.get(1).getPower()));
			orderItem.setBaza2(lens.getRxData().getPrism().get(1).getBase().intValue());
		} else {
			orderItem.setPrisma2(BigDecimal.ZERO);
			orderItem.setBaza2(0);
		}

		// coating
		orderItem.setUprava1_kod("");
		orderItem.setUprava2_kod("");
		orderItem.setUprava3_kod("");
		orderItem.setUprava4_kod("");
		orderItem.setUprava1_typ("");
		orderItem.setUprava2_typ("");
		orderItem.setUprava3_typ("");
		orderItem.setUprava4_typ("");
		orderItem.setUprava1_nazev("");
		orderItem.setUprava2_nazev("");
		orderItem.setUprava3_nazev("");
		orderItem.setUprava4_nazev("");
		orderItem.setUprava1_perc("");
		orderItem.setUprava2_perc("");
		orderItem.setUprava3_perc("");
		orderItem.setUprava4_perc("");

		for (int i = 0; i < lens.getCoating().size(); i++) {
			Coating c = lens.getCoating().get(i);
			setCoating(i, orderItem, c, manufacturer);
		}

		// centration
		if (lens.getCentration() != null &&
				lens.getCentration().getMonocularCentrationDistance() != null) {
			orderItem.setPd(BigDecimal.valueOf(lens.getCentration().getMonocularCentrationDistance().getValue()));
		} else {
			orderItem.setPd(BigDecimal.ZERO);
		}
		// geometry
		if (lens.getGeometry() != null
				&& lens.getGeometry().getDiameter() != null
				&& lens.getGeometry().getDiameter().getPhysical() != null) {
			orderItem.setDiameter_h(formatters.get().get(FORMAT_2)
					.format(lens.getGeometry().getDiameter().getPhysical()));
		} else {
			orderItem.setDiameter_h("");
		}
		if (lens.getGeometry() != null
				&& lens.getGeometry().getDiameter() != null
				&& lens.getGeometry().getDiameter().getOptical() != null) {
			orderItem.setDiameter_v(formatters.get().get(FORMAT_2)
					.format(lens.getGeometry().getDiameter().getOptical()));
		} else {
			orderItem.setDiameter_v("");
		}

		// stav
		orderItem.setStav("O");
	}

	private void setCoating(int index, OrderItem orderItem, Coating c,
			Manufacturer manufacturer) {
		String coatingName = "";
		String coatingCode = "";
		if (c.getCommercialCode() != null) {
			coatingName = dao.getCoatingName(manufacturer.getCode(),
					c.getCommercialCode());
			coatingCode = c.getCommercialCode();
		}
		String coatingType = "";
		if (c.getCoatingType() != null) {
			coatingType = c.getCoatingType().name();
		}
		String coatingPerc = "";
		if (c.getMaxIntensity() != null) {
			coatingPerc = String.valueOf(c.getMaxIntensity());
		} else if (c.getMinIntensity() != null) {
			coatingPerc = String.valueOf(c.getMinIntensity());
		}

		if (index == 0) {
			orderItem.setUprava1_kod(coatingCode);
			orderItem.setUprava1_nazev(coatingName);
			orderItem.setUprava1_typ(coatingType);
			orderItem.setUprava1_perc(String.valueOf(coatingPerc));
		} else if (index == 1) {
			orderItem.setUprava2_kod(coatingCode);
			orderItem.setUprava2_nazev(coatingName);
			orderItem.setUprava2_typ(coatingType);
			orderItem.setUprava2_perc(String.valueOf(coatingPerc));
		} else if (index == 2) {
			orderItem.setUprava3_kod(coatingCode);
			orderItem.setUprava3_nazev(coatingName);
			orderItem.setUprava3_typ(coatingType);
			orderItem.setUprava3_perc(String.valueOf(coatingPerc));
		} else if (index == 3) {
			orderItem.setUprava4_kod(coatingCode);
			orderItem.setUprava4_nazev(coatingName);
			orderItem.setUprava4_typ(coatingType);
			orderItem.setUprava4_perc(String.valueOf(coatingPerc));
		}
	}

}
