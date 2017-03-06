package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;

public class PingResultBuilder extends ResultBuilder {

    private PingResultBuilder(AdapterRequest request) {
        this.request = request;
    }

    public static  ResultBuilder getInstance(AdapterRequest request) {
        return new PingResultBuilder(request);
    }

    @Override
    protected final void build(Result result) {
    }

}
