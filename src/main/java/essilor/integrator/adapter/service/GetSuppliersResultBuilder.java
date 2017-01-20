package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.supplier.GetSuppliers;
import essilor.integrator.adapter.domain.supplier.GetSuppliersResponse;

public class GetSuppliersResultBuilder extends ResultBuilder {

	private GetSuppliersResultBuilder(AdapterRequest request,
									  GetSuppliers wsRequest, GetSuppliersResponse wsResponse) {
	}

	public static GetSuppliersResultBuilder getInstance(AdapterRequest request,
											GetSuppliers wsRequest, GetSuppliersResponse wsResponse) {
		return new GetSuppliersResultBuilder(request, wsRequest, wsResponse);
	}

	@Override
	public void build(Result result) {
	}

}
