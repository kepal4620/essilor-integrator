package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFile;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFileResponse;

public class UploadCustomFileResultBuilder extends ResultBuilder {

	private UploadCustomFile wsRequest;
	private UploadCustomFileResponse wsResponse;

	private UploadCustomFileResultBuilder(AdapterRequest request,
			UploadCustomFile wsRequest, UploadCustomFileResponse wsResponse) {
		this.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
	}

	public static  ResultBuilder getInstance(AdapterRequest request,
			UploadCustomFile wsRequest, UploadCustomFileResponse wsResponse) {
		return new UploadCustomFileResultBuilder(request, wsRequest, wsResponse);
	}

	@Override
	protected final void build(Result result) {
		result.setSessionId(wsResponse.getUploadCustomFileResult().getGuid());
		result.setUniqueId(wsResponse.getUploadCustomFileResult().getUniqueID());
		if (wsResponse.getUploadCustomFileResult().getUrl() != null) {
			result.setUrl(wsResponse.getUploadCustomFileResult().getUrl());
		}
		result.setXmlInput(wsRequest.getData());
		if (wsResponse.getUploadCustomFileResult().getError() != null) {
			result.setProcessed(Result.UNPROCESSED);
			result.setErrorText(wsResponse.getUploadCustomFileResult().getError());
		} else {
			result.setProcessed(Result.PROCESSED);
			result.setXmlOutput(wsResponse.getUploadCustomFileResult().getXml());
		}
	}

}
