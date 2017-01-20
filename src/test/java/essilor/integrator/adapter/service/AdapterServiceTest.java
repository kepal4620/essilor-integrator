package essilor.integrator.adapter.service;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.AdapterRequest.ActionType;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.AdapterRequest.Direction;
import essilor.integrator.adapter.AdapterRequest.Manufacturer;
import essilor.integrator.adapter.AdapterRequest.MethodName;
import essilor.integrator.adapter.service.AdapterService;

public class AdapterServiceTest {

	private static ClassPathXmlApplicationContext context = null;

	@Before
	public void setUp() {
		org.apache.log4j.BasicConfigurator.configure();
		context = new ClassPathXmlApplicationContext("/system-test-config.xml");

	}

	@Test
	public void testUploadCustomFile() throws JAXBException {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);

		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("11402338");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3132");
		req.setMethodName(MethodName.UploadCustomFile);
		req.setManufacturer(Manufacturer.Essilor);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.uploadCustomFile(req);
	}

	@Test
	public void testUploadCustomFile2() throws JAXBException {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);

		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("12501861");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3247");
		req.setMethodName(MethodName.UploadCustomFile);
		req.setManufacturer(Manufacturer.Essilor);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");
		

		Result result = service.uploadCustomFile(req);
	}

	
	@Test
	public void testGetOrderByPoNum() throws JAXBException {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);

		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("12501861");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3247");
		req.setMethodName(MethodName.GetOrderByPoNum);
		req.setManufacturer(Manufacturer.Essilor);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.getOrderByPoNum(req);
	}

	@Test
	public void testGetOrderByPoNum_2() throws JAXBException {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);

		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("15503462");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("11570");
		req.setMethodName(MethodName.GetOrderByPoNum_2);
		req.setManufacturer(Manufacturer.Essilor);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.getOrderByPoNum_2(req);
	}

	@Test
	public void testGetOrderAsPDF() throws Exception {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);
		
		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setPersistPdfInLog(true);
//		req.setOrderNumber("11402532");
		req.setOrderNumber("15503462");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3247");
		req.setMethodName(MethodName.GetOrderAsPDFByPoNum);
		req.setManufacturer(Manufacturer.Essilor);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.getOrderAsPDFByPoNum(req);
	}

	@Test
	public void testGetOrderAsPDF_2() throws Exception {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);
		
		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("15503462");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("11570");
		req.setMethodName(MethodName.GetOrderAsPDFByPoNum);
		req.setManufacturer(Manufacturer.EssilorSmile);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.getOrderAsPDFByPoNum(req);
	}

	
	@Test
	public void testUploadOrderByAction() throws Exception {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);
		
		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("12501861");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3247");
		req.setMethodName(MethodName.UploadOrderByAction);
		req.setManufacturer(Manufacturer.Essilor);
		req.setSloId("8317");
		req.setActionType(ActionType.Send);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.uploadOrderByAction(req);
		
	}

	@Test
	public void testValidateOrderFromPMS() throws Exception {
		AdapterService service = context.getBean("adapterService",
				AdapterService.class);
		
		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setOrderNumber("15503453");
		req.setOrderGroup("1");
		req.setPurchaseOrderNumber("3247");
		req.setMethodName(MethodName.ValidateOrderFromPMS);
		req.setManufacturer(Manufacturer.Essilor);
		req.setSloId("8317");
		req.setActionType(ActionType.Send);
		req.setDirection(Direction.SEND);
		req.setDataSourceName("kto_1");

		Result result = service.validateOrderFromPMS(req);
		
	}
	
}
