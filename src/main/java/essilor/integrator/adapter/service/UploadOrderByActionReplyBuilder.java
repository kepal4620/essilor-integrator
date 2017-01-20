package essilor.integrator.adapter.service;

import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import essilor.integrator.adapter.Result;

public class UploadOrderByActionReplyBuilder extends AdapterReplyBuilder{
	
	public String build() {
		try {
			StringBuilder builder = new StringBuilder();
			if (result.getProcessed() == Result.PROCESSED) {
				builder.append(result.getReturnCode())
						.append(ServiceCallTimestampHolder.getAsDateTime())
						.append(result.getOrderId());
				for (int i = 0; i < 15 - result.getOrderId().length(); i++) {
					builder.append(" ");
				}
				builder.append(result.getUrl());
				if ("KO".equals(result.getReturnCode())
						&& result.getErrorText() != null) {
					builder.append("\r\n");
					builder.append(buildErrorMessages());
				}
			} else {
				builder.append("ER")
						.append(ServiceCallTimestampHolder.getAsDateTime())
						.append("               ")
						.append(result.getErrorText());
			}
			return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String buildErrorMessages() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("ERROR MESSAGE").append("\r\n");
		
		SAXBuilder sbl=new SAXBuilder();
		Document doc=sbl.build(new StringReader(result.getErrorText()));
		String text = doc.getRootElement().getChild("ERROR").getText();
		text = "<E>" + text + "</E>";
		Document doc1 = sbl.build(new StringReader(text));
		List<Element> l1 = doc1.getRootElement().getChildren("ERROR");
		for (Element e1 : l1) {
				sb.append(e1.getText()).append("\r\n");
		}
		return sb.toString();
	}
}
