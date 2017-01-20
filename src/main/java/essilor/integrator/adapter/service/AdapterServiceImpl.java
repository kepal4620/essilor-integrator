package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterServiceImpl implements AdapterService {

	private static final Logger logger = Logger.getLogger(AdapterServiceImpl.class);
	
	@Autowired
	private EssilorService essilorService;

	@Autowired
	private LogService logService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private B2BOpticBuilder b2bBuilder;
	
	@Autowired
	private JAXBContext jaxbContext;

	
	private String username;
	private String password;
	private String locale;
	private String temp;
	private String refid;

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	@Override
	public Result uploadCustomFile(AdapterRequest request) {
		try {
			B2BOptic b2bOptic = b2bBuilder.build(request.getOrderNumber(),
					request.getOrderGroup(), request.getPurchaseOrderNumber());

			UploadCustomFile wsRequest = new UploadCustomFile();
			wsRequest.setUsername(username);
			wsRequest.setPassword(password);
			wsRequest.setRefid(refid);
			wsRequest.setLocale(locale);
			wsRequest.setTempId(temp);
			wsRequest.setTrace(null);

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setData(sw.toString());

			UploadCustomFileResponse wsResponse = essilorService
					.uploadCustomFile(wsRequest);

			Result result = UploadCustomFileResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(username);

			if (Result.PROCESSED == result.getProcessed()) {
				orderService.updateOrderAfterUpload(request, result.getUrl());
			}
			logService.logResult(request, result);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Result getOrderByPoNum(AdapterRequest request) {

		GetOrderByPoNum wsRequest = new GetOrderByPoNum();
		wsRequest.setUsername(username);
		wsRequest.setPassword(password);
		wsRequest.setRefid(refid);
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPonum(sb.toString());

		GetOrderByPoNumResponse wsResponse = essilorService
				.getOrderByPoNum(wsRequest);

		B2BOptic b2bOptic;
		try {
//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			b2bOptic = (B2BOptic) unmarshaller.unmarshal(new StringSource(
					wsResponse.getGetOrderByPoNumResult()));
		} catch (JAXBException e) {
			// returned error structure from web service
			b2bOptic = null;
		}

		Result result = GetOrderByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, b2bOptic).buildResult();
		result.setUsername(username);

		if (b2bOptic != null) {
			orderService.updateOrderAfterGetOrder(request, b2bOptic);
		}
		logService.logResult(request, result);
		return result;
	}

	@Override
	public Result getOrderByPoNum_2(AdapterRequest request) {

		GetOrderByPoNum wsRequest = new GetOrderByPoNum();
		wsRequest.setUsername(username);
		wsRequest.setPassword(password);
		wsRequest.setRefid(refid);
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPonum(sb.toString());

		GetOrderByPoNumResponse wsResponse = essilorService
				.getOrderByPoNum(wsRequest);

		B2BOptic b2bOptic;
		try {
//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			b2bOptic = (B2BOptic) unmarshaller.unmarshal(new StringSource(
					wsResponse.getGetOrderByPoNumResult()));
		} catch (JAXBException e) {
			// returned error structure from web service
			b2bOptic = null;
		}

		Result result = GetOrderByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, b2bOptic).buildResult();
		result.setUsername(username);

		logService.logResult(request, result);
		return result;
	}

	@Override
	public Result getOrderAsPDFByPoNum(AdapterRequest request) {
		GetOrderAsPDFByPoNum wsRequest = new GetOrderAsPDFByPoNum();
		wsRequest.setUsername(username);
		wsRequest.setPassword(password);
		wsRequest.setRefid(refid);
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPoNum(sb.toString());

		GetOrderAsPDFByPoNumResponse wsResponse = essilorService
				.getOrderAsPDFByPoNum(wsRequest);

		String pathToFile = null;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		StringBuilder fileName = new StringBuilder();
		fileName.append(wsRequest.getPoNum()).append("_")
				.append(df.format(new Date(ServiceCallTimestampHolder.getAsLong())))
				.append(".pdf");
		if (wsResponse.getGetOrderAsPDFByPoNumResult().getError() == null) {
			pathToFile = orderService.saveOrderAsPDF(request, fileName.toString(), wsResponse.getGetOrderAsPDFByPoNumResult()
					.getPdf());
		}

		Result result = GetOrderAsPDFByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, pathToFile,
				wsResponse.getGetOrderAsPDFByPoNumResult().getPdf())
				.buildResult();
		result.setUsername(username);

		logService.logResult(request, result);

		return result;
	}
	
	@Override
	public Result uploadOrderByAction(AdapterRequest request) {
		try {
			B2BOptic b2bOptic = b2bBuilder.build(request.getOrderNumber(),
					request.getOrderGroup(), request.getPurchaseOrderNumber());

			UploadOrderByAction wsRequest = new UploadOrderByAction();
			wsRequest.setUsername(username);
			wsRequest.setPassword(password);
			wsRequest.setRefid(refid);
			wsRequest.setLocale(locale);
			wsRequest.setTempId(temp);
			wsRequest.setTrace(null);
			wsRequest.setSloid(request.getSloId());
			wsRequest.setActionType(request.getActionType().name());

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setData(sw.toString());

			UploadOrderByActionResponse wsResponse = essilorService
					.uploadOrderByAction(wsRequest);

			Result result = UploadOrderByActionResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(username);

			if (Result.PROCESSED == result.getProcessed()) {
				orderService.updateOrderAfterUploadOrderByAction(request, result);
			}
			logService.logResult(request, result);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Result validateOrderFromPMS(AdapterRequest request) {
		try {
			B2BOptic b2bOptic = b2bBuilder.build(request.getOrderNumber(),
					request.getOrderGroup(), request.getPurchaseOrderNumber());
			ValidateOrderFromPMS wsRequest = new ValidateOrderFromPMS();
			wsRequest.setUsername(username);
			wsRequest.setPassword(password);
			wsRequest.setRefid(refid);
			wsRequest.setLocale(locale);
			wsRequest.setSloId(request.getSloId());

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setOrderFile(sw.toString());
			
			ValidateOrderFromPMSResponse wsResponse = essilorService
					.validateOrderFromPMS(wsRequest);
			
			Result result = ValidateOrderFromPMSResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(username);

			logService.logResult(request, result);

			return result;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Result getSuppliers(AdapterRequest request) {
		GetSuppliers wsRequest = new GetSuppliers();
		wsRequest.setUsername(username);
		wsRequest.setPassword(password);

		
		GetSuppliersResponse wsResponse = essilorService
				.getSuppliers(wsRequest);
		
		Result result = GetSuppliersResultBuilder.getInstance(request,
				wsRequest, wsResponse).buildResult();
		result.setUsername(username);

		logService.logResult(request, result);

		return result;
	}
}
