package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.service.AdapterReplyBuilder;
import essilor.integrator.adapter.service.ServiceCallTimestampHolder;

public class EetReplyBuilder extends AdapterReplyBuilder {

    @Override
    public String build() {
        StringBuilder reply = new StringBuilder();
        if (result.getErrorText() == null || result.getErrorText().length() == 0) {
            reply.append("OK")
                    .append(ServiceCallTimestampHolder.getAsDateTime())
                    .append(((EetResult)result).getFik());
        } else {
            reply.append("KO")
                    .append(ServiceCallTimestampHolder.getAsDateTime())
                    .append(result.getErrorText());
        }
        return reply.toString();
    }

}