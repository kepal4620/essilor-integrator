package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.dao.ConfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EetConfigurationService {

    @Autowired
    private ConfDao confDaoImpl;

    public String getIdPokl() {
        return confDaoImpl.getIdPokl();
    }
}
