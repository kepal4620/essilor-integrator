package essilor.integrator.adapter.service;

import essilor.integrator.adapter.Result;

public class GetOrderByPoNum_2ReplyBuilder extends AdapterReplyBuilder {

	@Override
	public String build() {
		StringBuilder reply = new StringBuilder();
		if (result.getErrorText() == null && 
				Result.PROCESSED == result.getProcessed()) {
			reply.append(result.getReturnCode())
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getOrderId());
			for (int i = 0; i < 15 - result.getOrderId().length(); i++) {
				reply.append(" ");
			}
			reply.append(result.getUrl());
		} else if (result.getErrorText() == null && 
				Result.UNPROCESSED == result.getProcessed()){
			reply.append("KO")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getErrorText());
		} else {
			reply.append("ER")
			.append(ServiceCallTimestampHolder.getAsDateTime())
			.append(result.getErrorText());
		}
		return reply.toString();
	}

}
