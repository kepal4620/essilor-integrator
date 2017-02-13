package essilor.integrator.adapter.service.eet;


import cz.mfcr.fs.eet.schema.v3.Odpoved;
import cz.mfcr.fs.eet.schema.v3.Trzba;
import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import essilor.integrator.adapter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Service
public class EetService {

    @Autowired
    private EetConnector eetConnector;

    @Autowired
    private EetLogService logService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EetTrzbaBuilder eetTrzbaBuilder;

    @Autowired
    private EetResultBuilder eetResultBuilder;

    private Map<String, EetConfigInfo> eetConfigMap;

    @Resource
    private void setEetConfigMap(Map<String, EetConfigInfo> eetConfigMap) {
        this.eetConfigMap = eetConfigMap;
    }

    public Result processRequest(AdapterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request is null");
        }
        if (request.getEetData() == null) {
            throw new IllegalStateException("eetData is null");
        }

        EetConfigInfo eetConfigInfo = eetConfigMap.get(request.getEetData().getKod());
        if (eetConfigInfo == null) {
            throw new IllegalStateException("eetConfigInfo null for kod: " + request.getEetData().getKod());
        }

        Trzba trzba = eetTrzbaBuilder.new Builder()
                .withDateOdesl(System.currentTimeMillis())
                .withPrvniZaslani(request.getEetData().isFirst())
                .withOvereni(request.getEetData().getMode())
                .withDicPopl(eetConfigInfo.getDic())
                .withDicPover(eetConfigInfo.getDic_poverujuceho())
                .withIdProvoz(Integer.parseInt(eetConfigInfo.getId_provoz()))
                .withIdPokl(eetConfigInfo.getId_pokl())
                .withPoradCis(request.getEetData().getPoradoveCislo())
                .withDatTrzby(request.getEetData().getDatTrzby())
                .withCelkTrzba(request.getEetData().getCelkTrzba())
                .withDan1(request.getEetData().getDan1())
                .withZaklDan1(request.getEetData().getZaklDan1())
                .withZaklDan2(request.getEetData().getZaklDan2())
                .withDan2(request.getEetData().getDan2())
                .withRezim(1)
                .withKod(request.getEetData().getKod())
                .build();

        Odpoved odpoved = eetConnector.sendTrzba(trzba, request.getEetData().getKod());

        EetResult result = (EetResult) eetResultBuilder.new Builder()
                .withAdapterRequest(request)
                .withTrzba(trzba)
                .withOdpoved(odpoved)
                .buildResult();

        orderService.updateOrderAfterEet(request, result);
        logService.logResult(request, result);

        return result;
    }
}
