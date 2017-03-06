package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import org.springframework.stereotype.Service;

@Service
public class PingService {

    public Result processRequest(AdapterRequest request) {
        return PingResultBuilder.getInstance(request).buildResult();
    }
}
