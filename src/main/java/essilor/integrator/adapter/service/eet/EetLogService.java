package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.dao.eet.EetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EetLogService {

    @Autowired
    private EetDao eetDao;

    @Transactional
    public void logResult(AdapterRequest adapterRequest, EetResult eetResult) {
        eetDao.logResult(adapterRequest, eetResult);
    }
}
