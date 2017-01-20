package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;

public abstract class ResultBuilder {
	
	protected AdapterRequest request;

	protected Result newResult() {
		return new Result();
	}

	public final Result buildResult() {
		Result result = newResult();
		result.setOrderNumber(request.getOrderNumber());
		result.setOrderGroup(request.getOrderGroup());
		result.setPurchaseOrderNumber(request.getPurchaseOrderNumber());
		result.setMethodName(request.getMethodName().name());
		if (request.getManufacturer() != null) {
			result.setManufacturer(request.getManufacturer().getCode());
		}
		result.setDataSourceName(request.getDataSourceName());
		if (request.getDirection()!= null) {
			result.setDirection(request.getDirection().getCode());
		}
		build(result);
		return result;
	}
	
	protected abstract void build(Result result);
}
