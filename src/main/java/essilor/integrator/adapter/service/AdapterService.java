package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;

public interface AdapterService {

	Result uploadCustomFile(AdapterRequest request);
	
	Result getOrderByPoNum(AdapterRequest request);
	
	Result getOrderByPoNum_2(AdapterRequest request);
	
	
	Result getOrderAsPDFByPoNum(AdapterRequest request);
	
	Result uploadOrderByAction(AdapterRequest request);
	
	Result validateOrderFromPMS(AdapterRequest request);
	
	Result getSuppliers(AdapterRequest request);
}
