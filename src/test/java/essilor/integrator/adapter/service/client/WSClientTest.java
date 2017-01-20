package essilor.integrator.adapter.service.client;

import essilor.integrator.adapter.service.EssilorService;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WSClientTest {

	private static ClassPathXmlApplicationContext context = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		context = new ClassPathXmlApplicationContext("/system-test-config.xml");
	}

	@Test
	public void testUploadFile() throws Exception {
		EssilorService service =  context
				.getBean("essilorWebService", EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/uploadcustomfile.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.uploadCustomFile(sb.toString());
		System.out.println(resp);
	}


	@Test
	public void testGetOrder() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/get_order_by_ponum.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.getOrderByPoNum(sb.toString());
		System.out.println(resp);
	}
	
	@Test
	public void testGetOrderAsPDF() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/get_order_as_pdf_by_ponum.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.getOrderAsPDFByPoNum(sb.toString());
		System.out.println(resp);
	}
	
	@Test
	public void testUploadOrderByAction() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/UploadOrderByAction_01.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.uploadOrderByAction(sb.toString());
		System.out.println(resp);
	}

	@Test
	public void testUploadOrderByAction2() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/UploadOrderByAction_02.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.uploadOrderByAction(sb.toString());
		System.out.println(resp);
	}

	@Test
	public void testValidateOrderFromPMS() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/ValidateOrderFromPMS_01.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.validateOrderFromPMS(sb.toString());
		System.out.println(resp);
	}

	@Test
	public void testGetSuppliers() throws Exception {
		EssilorService service = context.getBean("essilorService",
				EssilorService.class);
		StringBuilder sb = new StringBuilder();
		Scanner scanner = null;
		try {
			scanner = new Scanner(
					new File("test_resource/GetSuppliers_01.xml"));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		System.out.println(sb.toString());
		String resp = service.getSuppliers(sb.toString());
		System.out.println(resp);
	}

}
