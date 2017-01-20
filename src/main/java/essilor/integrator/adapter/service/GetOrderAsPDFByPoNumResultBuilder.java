package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNumResponse;

public class GetOrderAsPDFByPoNumResultBuilder extends ResultBuilder {

	private GetOrderAsPDFByPoNum wsRequest;
	private GetOrderAsPDFByPoNumResponse wsResponse;
	private String pathToFile;
	private byte[] pdf;

	private GetOrderAsPDFByPoNumResultBuilder(AdapterRequest request,
			GetOrderAsPDFByPoNum wsRequest,
			GetOrderAsPDFByPoNumResponse wsResponse, String pathToFile,
			byte[] pdf) {
		this.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
		this.pathToFile = pathToFile;
		this.pdf = pdf;

	}

	public static ResultBuilder getInstance(AdapterRequest request,
			GetOrderAsPDFByPoNum wsRequest,
			GetOrderAsPDFByPoNumResponse wsResponse, String pathToFile,
			byte[] pdf) {
		return new GetOrderAsPDFByPoNumResultBuilder(request, wsRequest,
				wsResponse, pathToFile, pdf);
	}

	@Override
	protected void build(Result result) {
		if (pdf != null) {
			result.setProcessed(Result.PROCESSED);
			result.setPdf(pdf);
			result.setUrl(pathToFile);

		} else {
			result.setProcessed(Result.UNPROCESSED);
			result.setErrorText(wsResponse.getGetOrderAsPDFByPoNumResult()
					.getError());
		}
	}

}
