package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.EetDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
                .append("A") // prvni zaslani
                .append("000000001.00")
                .append("000000000.83")
                .append("000000000.17")
                .append("000000000.00")
                .append("000000000.00")
                .append("2017-02-17 22:31:00")
                .append("0209300007")
                .append("002")
                .append("01")
                .append("012345678901234567890123456789")
                .append("ZL");

        AdapterRequest req = AdapterRequest.getEmptyRequest();
        req.setEetData(EetDataBuilder.newInstance().withRequest(sb.toString()).build());
        req.setMethodName(AdapterRequest.MethodName.OdeslaniTrzby);

        EetService service = context.getBean("eetService",
                EetService.class);

        service.processRequest(req);
    }

    @Test
    public void testEetPing() throws Exception {
        StringBuilder sb = new StringBuilder()
                .append("110")
                .append("A") // overeni
                .append("A") // prvni zaslani
                .append("000000001.00")
                .append("000000000.83")
                .append("000000000.17")
                .append("000000000.00")
                .append("000000000.00")
                .append("2017-02-17 22:31:00")
                .append("9999999999")
                .append("999")
                .append("99")
                .append("012345678901234567890123456789")
                .append("ZL");

        AdapterRequest req = AdapterRequest.getEmptyRequest();
        req.setEetData(EetDataBuilder.newInstance().withRequest(sb.toString()).build());
        req.setMethodName(AdapterRequest.MethodName.OdeslaniTrzby);

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
        req.setMethodName(AdapterRequest.MethodName.OdeslaniTrzby);

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
        req.setMethodName(AdapterRequest.MethodName.OdeslaniTrzby);

        EetService service = context.getBean("eetService",
                EetService.class);

        service.processRequest(req);
    }
}
