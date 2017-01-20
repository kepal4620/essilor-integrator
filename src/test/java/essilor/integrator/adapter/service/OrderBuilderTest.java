package essilor.integrator.adapter.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import essilor.integrator.adapter.AdapterRequest.Manufacturer;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.domain.optosys.Order;

public class OrderBuilderTest {

	private static ClassPathXmlApplicationContext context = null;

	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("/system-test-config.xml");
	}
	
	@Test
	public void testBuilder() throws Exception {
		OrderBuilder builder = context.getBean("orderBuilder", OrderBuilder.class);
		JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
		Unmarshaller u = context.createUnmarshaller();
		B2BOptic b2bOptic = (B2BOptic) u.unmarshal(new File("test_resource/data/b2bOptic.xml"));
		Order o = builder.build(b2bOptic, Manufacturer.Essilor);
		System.out.println(o);
	}
}
