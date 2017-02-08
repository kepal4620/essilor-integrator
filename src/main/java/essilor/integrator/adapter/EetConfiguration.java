package essilor.integrator.adapter;

import essilor.integrator.adapter.service.eet.EetConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EetConfiguration {

    @Autowired
    private EetConfigurationService configurationService;

    @Bean
    Map<String, String>  myMap() {
        System.out.println("id pokl: " + configurationService.getIdPokl());
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        return myMap;
    }

    @Bean
    Map<String, WebServiceTemplate> wsMap() {
        Map<String, WebServiceTemplate> wsMap = new HashMap<>();
        return wsMap;
    }


}

