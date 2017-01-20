package essilor.integrator.adapter.service;

import java.util.List;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.owvalidation.OrderError;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMS;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMSResponse;

public class ValidateOrderFromPMSResultBuilder extends ResultBuilder {

	private ValidateOrderFromPMS wsRequest;
	private ValidateOrderFromPMSResponse wsResponse;

	private ValidateOrderFromPMSResultBuilder(AdapterRequest request,
			ValidateOrderFromPMS wsRequest, ValidateOrderFromPMSResponse wsResponse) {
		super.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
	}

	public static  ResultBuilder getInstance(AdapterRequest request,
			ValidateOrderFromPMS wsRequest, ValidateOrderFromPMSResponse wsResponse) {
		return new ValidateOrderFromPMSResultBuilder(request, wsRequest, wsResponse);
	}
	
	@Override
	protected void build(Result result) {
		List <OrderError> list = wsResponse.getValidateOrderFromPMSResult().getOrderError();
		if (list == null || list.size() ==0) {
			result.setProcessed(1);
		} else {
			result.setProcessed(0);
			StringBuilder sb = new StringBuilder();
			for (OrderError error : list) {
				sb.append(error.getMessage()).append("\r\n");
			}
			result.setErrorText(sb.toString());
		}
		result.setXmlInput(wsRequest.getOrderFile());
	}
}
