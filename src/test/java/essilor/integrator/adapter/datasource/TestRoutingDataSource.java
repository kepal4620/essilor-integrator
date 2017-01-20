package essilor.integrator.adapter.datasource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.AdapterRequest.Direction;
import essilor.integrator.adapter.AdapterRequest.Manufacturer;
import essilor.integrator.adapter.AdapterRequest.MethodName;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.uploadfile.CustomFileInfo;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFile;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFileResponse;
import essilor.integrator.adapter.service.LogService;
import essilor.integrator.adapter.service.UploadCustomFileResultBuilder;

public class TestRoutingDataSource {
	private static ClassPathXmlApplicationContext context = null;

	@Before
	public void setup() {
		org.apache.log4j.BasicConfigurator.configure();
		context = new ClassPathXmlApplicationContext("/system-test-config.xml");
	}

	@Test
	public void testRoutingDataSource() throws Exception {
		LogService logService = context.getBean("logService", LogService.class);
		AdapterRequest req = AdapterRequest.getEmptyRequest();
		req.setDataSourceName("kto_1");
		req.setDirection(Direction.SEND);
		req.setManufacturer(Manufacturer.Essilor);
		req.setMethodName(MethodName.UploadCustomFile);
		req.setOrderGroup("1");
		req.setOrderNumber("1");
		req.setPurchaseOrderNumber("1");
		
		UploadCustomFile wsRequest = new UploadCustomFile();
		wsRequest.setData("data");
		wsRequest.setLocale("locale");
		wsRequest.setPassword("password");
		wsRequest.setRefid("refid");
		wsRequest.setTempId("tempid");
		wsRequest.setTrace(null);
		wsRequest.setUsername("username");
		
		UploadCustomFileResponse wsResponse = new UploadCustomFileResponse();
		CustomFileInfo info = new CustomFileInfo();
		info.setError("error");
		info.setGuid("guid");
		info.setUniqueID("uniqueid");
		info.setUrl("url");
		info.setXml("xml");
		
		wsResponse.setUploadCustomFileResult(info);
		Result result = UploadCustomFileResultBuilder.getInstance(req, wsRequest, wsResponse).buildResult();
		result.setUsername("username");
		
		logService.logResult(req, result);
		DataSourceNameHolder.setDataSourceName("kto_1");
		logService.logResult(req, result);
	}
}
