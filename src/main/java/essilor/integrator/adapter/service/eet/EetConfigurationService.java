package essilor.integrator.adapter.service.eet;

import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EetConfigurationService {

    @Autowired
    private ConfDao confDaoImpl;

    public String getIdPokl() {
        return confDaoImpl.getIdPokl();
    }

    public Map<String, EetConfigInfo> getEetConfiguration() {
        return confDaoImpl.getEetConfig();
    }

    public String getEetUri() {
        return confDaoImpl.getEetUri();
    }

    public String getEetKeystoreType() {
        return confDaoImpl.getEetKeystoreType();
    }

}
