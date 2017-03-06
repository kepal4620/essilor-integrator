package essilor.integrator.adapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import essilor.integrator.adapter.service.PingService;
import essilor.integrator.adapter.service.eet.EetService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import essilor.integrator.adapter.service.AdapterReplyBuilder;
import essilor.integrator.adapter.service.AdapterService;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;

public class Adapter implements ApplicationContextAware {

	private static final Logger logger = Logger.getLogger(Adapter.class);

	private final ExecutorService exec = Executors.newFixedThreadPool(10);
	private int port;
	
	private ApplicationContext ctx;

	private AdapterService service;

	@Autowired
	private EetService eetService;

	@Autowired
	private PingService pingService;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public AdapterService getService() {
		return service;
	}

	public void setService(AdapterService service) {
		this.service = service;
	}

	public EetService getEetService() {
		return eetService;
	}

	public void setEetService(EetService eetService) {
		this.eetService = eetService;
	}


	public void start() throws IOException {
		logger.info("adapter started");
		ServerSocket socket = new ServerSocket(port);
		while (!exec.isShutdown()) {
			try {
				final Socket conn = socket.accept();
				exec.execute(new Runnable() {
					@Override
					public void run() {
						try {
							handleRequest(conn);
						} catch (IOException e) {
							logger.error(e);
						}
					}
				});
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown()) {
					logger.error(e);
				}
			}
		}
		logger.info("Adapter finished");

	}

	public void stop() {
		exec.shutdown();
	}

	void handleRequest(Socket conn) throws IOException {
		int bufFrame = 131;
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
				conn.getOutputStream(), bufFrame));
		DataInputStream dis = new DataInputStream(new BufferedInputStream(
				conn.getInputStream(), bufFrame));
		String reply = null;
		try {
			byte[] buf = new byte[bufFrame];
			dis.readFully(buf, 0, bufFrame);
			String s = new String(buf);
			String opCode = s.substring(0, 3);
			int operationCode = Integer.parseInt(opCode);
			if (operationCode == 999) {
				stop();
				return;
			}
			AdapterRequest request = AdapterRequest.getRequest(ctx, s);
			logger.info("Adapter request: " + request.toString());
			Result result = null;
			switch (request.getMethodName()) {
			case UploadCustomFile:
				result = service.uploadCustomFile(request);
				break;
			case UploadOrderByAction:
				result = service.uploadOrderByAction(request);
				break;
			case GetOrderByPoNum:
				result = service.getOrderByPoNum(request);
				break;
			case GetOrderByPoNum_2:
				result = service.getOrderByPoNum_2(request);
				break;
			case GetOrderAsPDFByPoNum:
				result = service.getOrderAsPDFByPoNum(request);
				break;
			case ValidateOrderFromPMS:
				result = service.validateOrderFromPMS(request);
				break;
			case GetSuppliers:
				result = service.getSuppliers(request);
				break;
			case OdeslaniTrzby:
				result = eetService.processRequest(request);
				break;
				case Ping:
				result = pingService.processRequest(request);
				break;
			} // switch

			logger.debug("Result: " + result.toString());
			reply = buildReply(request, result);
			dos.write(reply.getBytes());

		} catch (Exception e) {
			ServiceCallTimestampHolder.setTimestamp(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			sb.append("ER").append(ServiceCallTimestampHolder.getAsDateTime())
					.append(e.getMessage());
			try {
				dos.write(sb.toString().getBytes());
			} catch (Exception e1) {
			}
			logger.error(e);
		} finally {
			try {
				dos.flush();
				dos.close();
				logger.info("Reply sent: " + reply);
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	private String buildReply(AdapterRequest request, Result result)
			throws IOException {
		return AdapterReplyBuilder.getBuilder(request, result).build();
	}

	public static void main(String[] args) {

		String log4jConfig = System.getProperty("log4j.configuration");
		if (log4jConfig == null) {
			BasicConfigurator.configure();
		} else {
			PropertyConfigurator.configure(log4jConfig);
		}

		ApplicationContext ctx;
		String resProp = System.getProperty("ext.res.dir");
		if (resProp != null) {
			ctx = new FileSystemXmlApplicationContext(resProp
					+ "/run-config.xml");
		} else {
			ctx = new ClassPathXmlApplicationContext("/run-config.xml");
		}

		Adapter adapter = ctx.getBean("adapter", Adapter.class);
		adapter.setApplicationContext(ctx);
		try {
			adapter.start();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
}
