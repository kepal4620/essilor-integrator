package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.service.AdapterReplyBuilder;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;

public class PingReplyBuilder extends AdapterReplyBuilder {

    @Override
    public String build() {
        return new StringBuilder()
            .append("OK")
            .append(ServiceCallTimestampHolder.getAsDateTime())
            .append(result.getUrl()).toString();
    }
}

