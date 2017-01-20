package essilor.integrator.adapter.service;

import java.io.File;
import java.io.IOException;

import essilor.integrator.adapter.dao.eet.EetDao;
import essilor.integrator.adapter.service.eet.EetResult;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.dao.OrderDao;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.domain.optosys.Order;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;

	@Autowired
	private ConfDao confDao;

	@Autowired
	private OrderBuilder orderBuilder;

	@Autowired
	private EetDao eetDao;

	@Override
	@Transactional
	public void updateOrderAfterGetOrder(AdapterRequest request,
			B2BOptic b2bOptic) {
		Order order = orderBuilder.build(b2bOptic, request.getManufacturer());
		dao.updateOrderAfterGetOrder(order);
	}

	@Override
	@Transactional
	public void updateOrderAfterUpload(AdapterRequest request, String url) {
		dao.updateOrderAfterUpload(request.getOrderNumber(),
				request.getOrderGroup(), url);

	}

	@Override
	@Transactional
	public String saveOrderAsPDF(AdapterRequest request, String fileName, byte[] pdf) {
		try {
			File f = new File(confDao.getOrdersDir(), fileName);		
			FileUtils.writeByteArrayToFile(f, pdf, false);
			dao.updateOrderAfterGetAsPDF(request.getOrderNumber(), request.getOrderGroup(), f.getName());
			return f.getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateOrderAfterEet(AdapterRequest request, EetResult result) {
		if (request == null) {
			throw new IllegalArgumentException("request is null");
		}
		if (result == null) {
			throw new IllegalArgumentException("result is null");
		}
		if (request.getEetData() == null) {
			throw new IllegalStateException("eetData is null");
		}
		eetDao.updateFik(request.getEetData(), result);
	}

	@Override
	@Transactional
	public void updateOrderAfterUploadOrderByAction(AdapterRequest request,
			Result result) {
		String status = "";
		if (Result.PROCESSED == result.getProcessed()) {
			status = "O";
		}
		dao.updateOrderAfterUploadOrderByAction(request.getOrderNumber(),
				request.getOrderGroup(), result.getUrl(), status, result.getOrderId());
	}
}
