package essilor.integrator.adapter.tools;

import essilor.integrator.adapter.service.AdapterService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EncryptorTest {

    private static ClassPathXmlApplicationContext context = null;

    @Before
    public void setUp() {
        org.apache.log4j.BasicConfigurator.configure();
        context = new ClassPathXmlApplicationContext("/encryptor-test-config.xml");
    }

    @Test
    public void testEncrypt()  throws Exception {
        Encryptor encryptor = context.getBean("encryptor",
                Encryptor.class);

        String enc = encryptor.encrypt("mysql");
        System.out.println(enc);
    }
}
