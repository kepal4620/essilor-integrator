package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.AdapterRequest.MethodName;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.service.eet.EetReplyBuilder;

public abstract class AdapterReplyBuilder {

	protected Result result;
	protected AdapterRequest request;
	
	public abstract String build();
	
	public static AdapterReplyBuilder getBuilder(AdapterRequest request, Result result) {
		AdapterReplyBuilder builder = null;
		if (MethodName.UploadCustomFile.equals(request.getMethodName())) {
			builder = new UploadCustomFileReplyBuilder();
		} else if (MethodName.GetOrderByPoNum.equals(request.getMethodName())) {
			builder =  new GetOrderByPoNumReplyBuilder();
		} else if (MethodName.GetOrderByPoNum_2.equals(request.getMethodName())) {
			builder =  new GetOrderByPoNum_2ReplyBuilder();
		} else if (MethodName.GetOrderAsPDFByPoNum.equals(request
				.getMethodName())) {
			builder =  new GetOrderAsPDFByPoNumReplyBuilder();
		} else if (MethodName.UploadOrderByAction.equals(request.getMethodName())) {
			builder = new UploadOrderByActionReplyBuilder();
		} else if (MethodName.ValidateOrderFromPMS.equals(request.getMethodName())) {
			builder = new ValidateOrderFromPMSReplyBuilder();
		}  else if (MethodName.GetSuppliers.equals(request.getMethodName())) {
			builder = new GetSuppliersReplyBuilder();
		} else if (MethodName.OdeslaniTrzby.equals(request.getMethodName())) {
			builder = new EetReplyBuilder();
		}
		
		builder.request = request;
		builder.result = result;	
		return builder;
	}
}
