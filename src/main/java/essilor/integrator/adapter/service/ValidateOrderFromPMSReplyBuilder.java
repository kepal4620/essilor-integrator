package essilor.integrator.adapter.service;

public class ValidateOrderFromPMSReplyBuilder extends AdapterReplyBuilder {

	@Override
	public String build() {
		StringBuilder sb = new StringBuilder();
		if (result.getProcessed() == 1) {
			sb.append("OK");
		} else {
			sb.append("KO");
		}
		sb.append(ServiceCallTimestampHolder.getAsDateTime());
		if (result.getErrorText() != null) {
			sb.append(result.getErrorText());
		}
		return sb.toString();
	}

}
