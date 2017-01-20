package essilor.integrator.adapter.dao;


import essilor.integrator.adapter.domain.optosys.Order;
import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;

public interface OrderDao {

	Order getOrder(String zakazka, String skupina, String objednavka);

	void updateOrderAfterGetOrder(Order order);
	
	void updateOrderAfterUpload(String zakazka, String skupina, String url);
	
	void updateOrderAfterUploadOrderByAction(String zakazka, String skupina, String url, String stav, String orderId);
	
	void updateOrderAfterGetAsPDF(String zakazka, String skupina, String pathToFile);

	String getLensName(String manufacturerCode, String code);
	
	String getCoatingName(String manufacturerCode, String code);
	
	void logResult(AdapterRequest request, Result result);

}
