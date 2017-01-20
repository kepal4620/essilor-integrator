package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNumResponse;

public class GetOrderByPoNumResultBuilder extends ResultBuilder {

	private GetOrderByPoNum wsRequest;
	private GetOrderByPoNumResponse wsResponse;
	private B2BOptic b2bOptic;

	private GetOrderByPoNumResultBuilder(AdapterRequest request,
			GetOrderByPoNum wsRequest, GetOrderByPoNumResponse wsResponse,
			B2BOptic b2bOptic) {
		this.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
		this.b2bOptic = b2bOptic;
	}

	public static ResultBuilder getInstance(AdapterRequest request,
			GetOrderByPoNum wsRequest, GetOrderByPoNumResponse wsResponse,
			B2BOptic b2bOptic) {
		return new GetOrderByPoNumResultBuilder(request, wsRequest, wsResponse,
				b2bOptic);
	}

	@Override
	protected void build(Result result) {
		if (b2bOptic != null) {
			result.setProcessed(Result.PROCESSED);
			result.setXmlOutput(wsResponse.getGetOrderByPoNumResult());
			result.setOrderId(b2bOptic.getHeader().getCustomersOrderId().trim());
		} else {
			result.setProcessed(result.UNPROCESSED);
			result.setErrorText(wsResponse.getGetOrderByPoNumResult());
		}
	}

}
