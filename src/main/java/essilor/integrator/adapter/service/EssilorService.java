package essilor.integrator.adapter.service;

import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNumResponse;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNumResponse;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMS;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMSResponse;
import essilor.integrator.adapter.domain.supplier.GetSuppliers;
import essilor.integrator.adapter.domain.supplier.GetSuppliersResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFile;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFileResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByAction;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByActionResponse;

public interface EssilorService {
	
	UploadCustomFileResponse uploadCustomFile(UploadCustomFile request);
	
	String uploadCustomFile(String message);
	
	GetOrderByPoNumResponse getOrderByPoNum(GetOrderByPoNum request);
	
	String getOrderByPoNum(String message);
	
	GetOrderAsPDFByPoNumResponse getOrderAsPDFByPoNum(GetOrderAsPDFByPoNum request);
	
	String getOrderAsPDFByPoNum(String message);
	
	UploadOrderByActionResponse uploadOrderByAction(UploadOrderByAction request);
	
	String uploadOrderByAction(String message);
	
	ValidateOrderFromPMSResponse validateOrderFromPMS(ValidateOrderFromPMS request);
	
	String validateOrderFromPMS(String message);
	
	GetSuppliersResponse getSuppliers(GetSuppliers request);
	
	String getSuppliers(String message);
}
