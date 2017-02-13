package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.EetDataBuilder;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.service.AdapterService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBException;

public class EetServiceTest {
    private static ClassPathXmlApplicationContext context = null;

    @Before
    public void setUp() {
        org.apache.log4j.BasicConfigurator.configure();
        context = new ClassPathXmlApplicationContext("/system-test-config.xml");
    }

    @Test
    public void testEet() throws Exception {
        StringBuilder sb = new StringBuilder()
                .append("110")
                .append("N") // overeni
                .append("N") // prvni zaslani
                .append("000000001.00")
                .append("000000001.00")
                .append("000000001.00")
                .append("000000001.00")
                .append("-00000001.00")
                .append("2017-02-10 22:00:00")
                .append("0209300007")
                .append("002")
                .append("01")
                .append("012345678901234567890123456789")
                .append("ZL");

        AdapterRequest req = AdapterRequest.getEmptyRequest();
        req.setEetData(EetDataBuilder.newInstance().withRequest(sb.toString()).build());
        req.setMethodName(AdapterRequest.MethodName.Eet);

        EetService service = context.getBean("eetService",
                EetService.class);

        service.processRequest(req);
    }

    @Test
    public void testEet2() throws Exception {
        StringBuilder sb = new StringBuilder()
                .append("110")
                .append("N") // overeni
                .append("N") // prvni zaslani
                .append("     1112.11")
                .append("     2222.22")
                .append("     3333.33")
                .append("     4444.44")
                .append("     5555.55")
                .append("2016-09-30 08:40:00")
                .append("0209300007")
                .append("002")
                .append("01")
                .append("012345678901234567890123456789")
                .append("  CZ01640836")
                .append("  CZ99999999")
                .append(" ");


        AdapterRequest req = AdapterRequest.getEmptyRequest();
        req.setEetData(EetDataBuilder.newInstance().withRequest(sb.toString()).build());
        req.setMethodName(AdapterRequest.MethodName.Eet);

        EetService service = context.getBean("eetService",
                EetService.class);

        service.processRequest(req);
    }

    @Test
    public void testEet3() throws Exception {
        StringBuilder sb = new StringBuilder()
                .append("110")
                .append("N") // overeni
                .append("N") // prvni zaslani
                .append("     1112.11")
                .append("     2222.22")
                .append("     3333.33")
                .append("     4444.44")
                .append("000000000000")
                .append("2016-09-30 08:40:00")
                .append("0209300007")
                .append("002")
                .append("01")
                .append("012345678901234567890123456789")
                .append("  CZ01640836")
                .append("  CZ99999999")
                .append(" ");


        AdapterRequest req = AdapterRequest.getEmptyRequest();
        req.setEetData(EetDataBuilder.newInstance().withRequest(sb.toString()).build());
        req.setMethodName(AdapterRequest.MethodName.Eet);

        EetService service = context.getBean("eetService",
                EetService.class);

        service.processRequest(req);
    }
}
