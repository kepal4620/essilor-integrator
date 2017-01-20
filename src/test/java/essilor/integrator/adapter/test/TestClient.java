package essilor.integrator.adapter.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.AdapterRequest.ActionType;
import essilor.integrator.adapter.AdapterRequest.Direction;
import essilor.integrator.adapter.AdapterRequest.Manufacturer;
import essilor.integrator.adapter.AdapterRequest.MethodName;
import essilor.integrator.adapter.service.AdapterService;

public class TestClient {

	@Test
	public void test() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("010")
		.append("000000011402532")
		.append("001")
		.append("000000000003132")
		.append(" E")
		.append("kto_1                         ")
		.append("0000008137")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
	}

	@Test
	public void testUploadCustomFile() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("010")
		.append("000000012501861")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("kto_1                         ")
		.append("0000008137")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
	}
	
	@Test
	public void testGetOrderAsPDF() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 130);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 130);
		StringBuilder sb = new StringBuilder();
		sb.append("021")
		.append("000000015503462")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("kto_1                         ")
		.append("0000009137")
		.append("           Send")
		.append("0000000")
		.append("000000000000000000000000000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 130);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
		
	}

	@Test
	public void testGetOrderByPoNum_2() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("022")
		.append("000000012501861")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("kto_1                         ")
		.append("0000009137")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
		
	}

	@Test
	public void testUploadOrderByAction() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("011")
		.append("000000012501861")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("ZL                            ")
		.append("0000008317")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
		
	}

	@Test
	public void testUploadOrderByAction2() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("011")
		.append("000000015503453")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("ZL                            ")
		.append("0000008317")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
		
	}
	
	@Test
	public void testValidateOrderFromPMS() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 100);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 100);
		StringBuilder sb = new StringBuilder();
		sb.append("030")
		.append("000000015503453")
		.append("001")
		.append("000000000003247")
		.append(" E")
		.append("ZL                            ")
		//.append("0000008316")
		.append("      8316")
		.append("           Send")
		.append("0000000");
		os.write(sb.toString().getBytes("UTF-8"), 0, 100);
		
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);

	}

	@Test
	public void testEet() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 155);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 155);

		StringBuilder sb = new StringBuilder()
				.append("110")
				.append("N") // overeni
				.append("N") // prvni zaslani
				.append("000001112.11")
				.append("000002222.22")
				.append("000003333.33")
				.append("000004444.44")
				.append("-00005555.55")
				.append("2017-01-19 19:40:00")
				.append("0209300007")
				.append("002")
				.append("01")
				.append("012345678901234567890123456789")
				.append("  CZ00000019")
				.append("  CZ00000019")
				.append("  ");

		os.write(sb.toString().getBytes("UTF-8"), 0, 155);
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
	}

	@Test
	public void testEet2() throws Exception {
		Socket s = new Socket("localhost", 6900);
		InputStream is = new BufferedInputStream(s.getInputStream(), 155);
		OutputStream os = new BufferedOutputStream(s.getOutputStream(), 155);

		StringBuilder sb = new StringBuilder()
				.append("110")
				.append("N") // overeni
				.append("N") // prvni zaslani
				.append("000001112.11")
				.append("000002222.22")
				.append("000003333.33")
				.append("000000000000")
				.append("-00005555.55")
				.append("2016-09-30 08:40:00")
				.append("0209300007")
				.append("002")
				.append("01")
				.append("012345678901234567890123456789")
				.append(" CZ683555118")
				.append(" CZ683555118")
				.append("  ");

		os.write(sb.toString().getBytes("UTF-8"), 0, 155);
		os.flush();
		byte[] frame = new byte[1024];
		is.read(frame, 0, 1024);
		String str = new String(frame);
		System.out.println("received: " + str);
	}

}
