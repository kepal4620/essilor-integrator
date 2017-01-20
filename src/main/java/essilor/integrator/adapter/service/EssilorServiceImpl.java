package essilor.integrator.adapter.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

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

public class EssilorServiceImpl implements EssilorService {

	@Autowired
	private WebServiceTemplate uploadFileWSTemplate;

	@Autowired
	private WebServiceTemplate getOrderWSTemplate;
	
	@Autowired
	private WebServiceTemplate owValidationWSTemplate;
	
	@Autowired
	private WebServiceTemplate supplierWSTemplate;

	

	@Override
	public UploadCustomFileResponse uploadCustomFile(UploadCustomFile request) {
		UploadCustomFileResponse response = (UploadCustomFileResponse) uploadFileWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String uploadCustomFile(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		uploadFileWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	@Override
	public GetOrderByPoNumResponse getOrderByPoNum(GetOrderByPoNum request) {
		GetOrderByPoNumResponse response = (GetOrderByPoNumResponse) getOrderWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String getOrderByPoNum(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		getOrderWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	@Override
	public GetOrderAsPDFByPoNumResponse getOrderAsPDFByPoNum(
			GetOrderAsPDFByPoNum request) {
		GetOrderAsPDFByPoNumResponse response = (GetOrderAsPDFByPoNumResponse) getOrderWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String getOrderAsPDFByPoNum(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		getOrderWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	@Override
	public UploadOrderByActionResponse uploadOrderByAction(
			UploadOrderByAction request) {
		UploadOrderByActionResponse response = (UploadOrderByActionResponse) uploadFileWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String uploadOrderByAction(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		uploadFileWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	@Override
	public ValidateOrderFromPMSResponse validateOrderFromPMS(
			ValidateOrderFromPMS request) {
		ValidateOrderFromPMSResponse response = (ValidateOrderFromPMSResponse) owValidationWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String validateOrderFromPMS(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		owValidationWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	@Override
	public GetSuppliersResponse getSuppliers(GetSuppliers request) {
		GetSuppliersResponse response = (GetSuppliersResponse) supplierWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	@Override
	public String getSuppliers(String message) {
		StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		supplierWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}
}
