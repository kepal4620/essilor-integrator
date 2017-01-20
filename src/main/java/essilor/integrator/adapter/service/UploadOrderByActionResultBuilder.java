package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByAction;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByActionResponse;

public class UploadOrderByActionResultBuilder extends ResultBuilder {

	private UploadOrderByAction wsRequest;
	private UploadOrderByActionResponse wsResponse;

	private UploadOrderByActionResultBuilder(AdapterRequest request,
			UploadOrderByAction wsRequest, UploadOrderByActionResponse wsResponse) {
		super.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
	}

	
	public static  ResultBuilder getInstance(AdapterRequest request,
			UploadOrderByAction wsRequest, UploadOrderByActionResponse wsResponse) {
		return new UploadOrderByActionResultBuilder(request, wsRequest, wsResponse);
	}


	@Override
	protected void build(Result result) {
		if (wsResponse.getUploadOrderByActionResult().getURL() != null) {
			result.setUrl(wsResponse.getUploadOrderByActionResult().getURL());
		}
		result.setXmlInput(wsRequest.getData());
		result.setOrderId(wsResponse.getUploadOrderByActionResult().getOrderId());
		if ("OK".equals(wsResponse.getUploadOrderByActionResult().getStatus()) ||
			"KO".equals(wsResponse.getUploadOrderByActionResult().getStatus())) {
			result.setProcessed(Result.PROCESSED);
			result.setReturnCode(wsResponse.getUploadOrderByActionResult().getStatus());
		} else {
			result.setReturnCode("ER");
		}
		if (wsResponse.getUploadOrderByActionResult().getErrorMessages() != null) {
			result.setErrorText(wsResponse.getUploadOrderByActionResult().getErrorMessages());
		} 
	}

}
