package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;

public interface LogService {
	
	
	void logResult(AdapterRequest request, Result result);
}
