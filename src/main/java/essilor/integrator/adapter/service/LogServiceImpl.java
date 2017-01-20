package essilor.integrator.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.dao.OrderDao;

public class LogServiceImpl implements LogService {

	@Autowired
	OrderDao dao;
	
	@Override
	public void logResult(AdapterRequest request, Result result) {
		dao.logResult(request, result);
	}

}
