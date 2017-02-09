package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@ContextConfiguration(locations = {"classpath:/system-test-config.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfDaoImplTest {

    @Autowired
    private ConfDaoImpl confDao;

    @Test
    public void testGetEetConfigInfo() {
        Map<String,EetConfigInfo> eetConfig = confDao.getEetConfig();
    }
}
