package essilor.integrator.adapter;


public class Result {

	public static final int PROCESSED = 1;
	public static final int UNPROCESSED = 0;

	private String orderNumber;
	private String orderGroup;
	private String purchaseOrderNumber;
	private String url = "";
	private String sessionId;
	private String uniqueId;
	private String username;
	private String manufacturer;
	private String direction;
	private String errorCode;
	private String errorText;
	private String xmlInput = "";
	private String xmlOutput = "";
	private byte[] pdf;
	private String dataSourceName;
	private String methodName;
	private int processed;
	private String returnCode;
	private String orderId;

	public Result() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getXmlInput() {
		return xmlInput;
	}

	public void setXmlInput(String xmlInput) {
		this.xmlInput = xmlInput;
	}

	public String getXmlOutput() {
		return xmlOutput;
	}

	public void setXmlOutput(String xmlOutput) {
		this.xmlOutput = xmlOutput;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Result[\n")
		.append("MethodName=").append(methodName).append("\n")
		.append("OrderNumber=").append(orderNumber).append("\n")
		.append("OrderGroup=").append(orderGroup).append("\n")
		.append("PurchaseOrderNumber=").append(purchaseOrderNumber).append("\n")
		.append("Url=").append(url).append("\n")
		.append("SessionId=").append(sessionId).append("\n")
		.append("UniqueId=").append(uniqueId).append("\n")
		.append("UserName=").append(username).append("\n")
		.append("Manufacturer=").append(manufacturer).append("\n")
		.append("Direction=").append(direction).append("\n")
		.append("ErrorCode=").append(errorCode).append("\n")
		.append("OrderGroup=").append(orderGroup).append("\n")
		.append("ErrorText=").append(errorText).append("\n")
		.append("DataSource=").append(dataSourceName).append("\n")
		.append("Processed=").append(processed).append("\n")
		.append("ReturnCode=").append(returnCode).append("\n")
		.append("OrderId=").append(orderId).append("\n")
		.append("]");
		return sb.toString();
	}

	
}
