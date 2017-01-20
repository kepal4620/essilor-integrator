package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.service.eet.EetResult;

public interface OrderService {

	// updates an order in DMS by b2bOptic structure returned from GetOrderByPoNum function call 
	void updateOrderAfterGetOrder(AdapterRequest request, B2BOptic b2bOptic);
	
	// updates an order in DMS after UploadCustomFile function was called
	void updateOrderAfterUpload(AdapterRequest request, String url);

	// updates an order in DMS after UploadOrderByAction function was called
	void updateOrderAfterUploadOrderByAction(AdapterRequest request, Result result);

	String saveOrderAsPDF(AdapterRequest request, String fileName, byte[] pdf);

	void updateOrderAfterEet(AdapterRequest request, EetResult result);

}
