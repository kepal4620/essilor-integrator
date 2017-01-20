package essilor.integrator.adapter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import essilor.integrator.adapter.utils.PropertiesUtil;

import java.text.ParseException;

public class AdapterRequest {
	private static final Logger log = Logger.getLogger(AdapterRequest.class);

	public enum MethodName {
		UploadCustomFile, GetOrderByPoNum, GetOrderByPoNum_2, GetOrderAsPDFByPoNum, UploadOrderByAction,
		ValidateOrderFromPMS, GetSuppliers, Eet
	};

	public enum Manufacturer {

		Essilor("E"), EssilorSmile("ES");

		private Manufacturer(String code) {
			this.code = code;
		}

		private String code;

		public String getCode() {
			return code;
		}
	}

	public enum Direction {
		SEND("S"), RECEIVE("R");

		private String code;

		private Direction(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	public enum ActionType {
		Send, SavePending
	};

	private String orderNumber;
	private String orderGroup;
	private String purchaseOrderNumber;
	private MethodName methodName;
	private Manufacturer manufacturer;
	private String dataSourceName;
	private String sloId;
	private ActionType actionType;
	private Direction direction;
	private boolean persistPdfInLog;

	private EetData eetData;

	protected AdapterRequest() {
	}

	public EetData getEetData() {
		return eetData;
	}

	public void setEetData(EetData eetData) {
		this.eetData = eetData;
	}

	public static AdapterRequest getEmptyRequest() {
		return new AdapterRequest();
	}

	public static AdapterRequest getRequest(ApplicationContext ctx, String s) throws ParseException {
		AdapterRequest req = new AdapterRequest();
		PropertiesUtil props = ctx.getBean("properties", PropertiesUtil.class);
		String persistPdf = props.getProperty("adapter.log.persist.pdf");
		if("true".equalsIgnoreCase(persistPdf)) {
			req.setPersistPdfInLog(true);
		}
		req.methodName = determineMethod(s);
		switch(req.methodName) {
			case Eet:
				setValuesForEET(s, req);
				break;
			default:
				setDefaultValues(s, req);
		}
		return req;
	}

	private static MethodName determineMethod(String s) {
		if ("010".equals(s.substring(0, 3))) {
			return MethodName.UploadCustomFile;
		} else if ("011".equals(s.substring(0, 3))) {
			return MethodName.UploadOrderByAction;
		} else if ("020".equals(s.substring(0, 3))) {
			return MethodName.GetOrderByPoNum;
		} else if ("021".equals(s.substring(0, 3))) {
			return MethodName.GetOrderAsPDFByPoNum;
		} else if ("022".equals(s.substring(0, 3))) {
			return MethodName.GetOrderByPoNum_2;
		} else if ("030".equals(s.substring(0, 3))) {
			return MethodName.ValidateOrderFromPMS;
		} else if ("040".equals(s.substring(0, 3))) {
			return MethodName.GetSuppliers;
		} else if ("110".equals(s.substring(0, 3))) {
			return MethodName.Eet;
		} else {
			throw new IllegalStateException("Cannot determine adapter method");
		}
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public MethodName getMethodName() {
		return methodName;
	}

	public void setMethodName(MethodName methodName) {
		this.methodName = methodName;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setSloId(String sloId) {
		this.sloId = sloId;
	}
	
	public String getSloId() {
		return this.sloId;
	}
	
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	public ActionType getActionType() {
		return this.actionType;
	}
	
	
	public boolean isPersistPdfInLog() {
		return persistPdfInLog;
	}

	public void setPersistPdfInLog(boolean persistPdfInLog) {
		this.persistPdfInLog = persistPdfInLog;
	}



	private static void setValuesForEET(String s, AdapterRequest request) throws ParseException {
		request.setEetData(EetDataBuilder.newInstance().withRequest(s).build());
	}

	private static void setDefaultValues(String s, AdapterRequest req) {
		req.orderNumber = String.valueOf(Long.valueOf(s.substring(3, 18).trim()));
		req.orderGroup = String.valueOf(Long.valueOf(s.substring(18, 21).trim()));
		req.purchaseOrderNumber = String.valueOf(Long.valueOf(s.substring(21, 36).trim()));
		if ("E".equals(s.substring(36, 38).trim())) {
			req.manufacturer = Manufacturer.Essilor;
		} else if ("ES".equals(s.substring(36, 38).trim())) {
			req.manufacturer = Manufacturer.EssilorSmile;
		} else {
			throw new RuntimeException("Unknown manufacturer code: "
					+ s.substring(38, 40).trim());
		}
		req.dataSourceName = s.substring(38, 68);
		req.sloId = s.substring(68, 78).trim();
		if ("Send".equals(s.substring(78, 93).trim())) {
			req.actionType = ActionType.Send;
		} else if ("SavePending".equals(s.substring(78, 93).trim())) {
			req.actionType = ActionType.SavePending;
		} else {
			throw new RuntimeException("Unknown action type: "
					+ s.substring(78, 93).trim());
		}
		req.direction = Direction.SEND;
		log.debug(req);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AdapterRequest: ").append(this.methodName).append(", ")
		.append(this.orderNumber).append(", ").append(this.orderGroup).
		append(", ").append(this.purchaseOrderNumber).append(", ").append(this.manufacturer)
		.append(", ").append(this.dataSourceName);
		return sb.toString();
	}
}
