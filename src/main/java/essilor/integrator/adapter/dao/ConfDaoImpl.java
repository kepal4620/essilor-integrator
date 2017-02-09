package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfDaoImpl implements ConfDao {

	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select val from conf_ini where var = ?";

	private static final String GET_EET_CONFIG_SQL = "select ico,dic,id_provozovny,id_pokl,eet_keystore_path,eet_keystore_pwd,eet_key_alias from c_prevadzky";

	private String softwareOriginatorName;
	
	private String softwareSenderName;
	
	private String softwareOriginatorVersion;
	
	private String softwareSenderVersion;

	public ConfDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public String getOrganisation() {
		return jdbcTemplate.queryForObject(SQL, String.class, "ORGANIZACE");

	}

	@Override
	public String getCity() {
		return jdbcTemplate.queryForObject(SQL, String.class, "MESTO");
	}

	@Override
	public String getStreet() {
		return jdbcTemplate.queryForObject(SQL, String.class, "ULICE");
	}

	@Override
	public String getZipCode() {
		return jdbcTemplate.queryForObject(SQL, String.class, "PSC");
	}

	@Override
	public String getOrdersDir() {
		return jdbcTemplate.queryForObject(SQL, String.class, "OBJEDNAVKY_PDF_DIR");
	}

	@Override
	public String getSoftwareOriginatorName() {
		return softwareOriginatorName;
	}

	@Override
	public String getSoftwareSenderName() {
		return softwareSenderName;
	}

	@Override
	public String getSoftwareOriginatorVersion() {
		return softwareOriginatorVersion;
	}

	@Override
	public String getSoftwareSenderVersion() {
		return softwareSenderVersion;
	}

	public String getDic() {
		return jdbcTemplate.queryForObject(SQL, String.class, "DIC");
	}

    public String getIdProvoz() {
        return jdbcTemplate.queryForObject(SQL, String.class, "EET_ID_PROVOZ");
    }

    public String getIdPokl() {
        return jdbcTemplate.queryForObject(SQL, String.class, "EET_ID_POKL");
    }

    public void setSoftwareOriginatorName(String softwareOriginatorName) {
		this.softwareOriginatorName = softwareOriginatorName;
	}

	public void setSoftwareSenderName(String softwareSenderName) {
		this.softwareSenderName = softwareSenderName;
	}

	public void setSoftwareOriginatorVersion(String softwareOriginatorVersion) {
		this.softwareOriginatorVersion = softwareOriginatorVersion;
	}

	public void setSoftwareSenderVersion(String softwareSenderVersion) {
		this.softwareSenderVersion = softwareSenderVersion;
	}

	public Map<String, EetConfigInfo> getEetConfig() {
		Map<String, EetConfigInfo> eetConfig = new HashMap<String, EetConfigInfo>();
		List<Map<String, Object>> rows =  jdbcTemplate.queryForList(GET_EET_CONFIG_SQL);
		for (Map<String, Object> row : rows) {
			EetConfigInfo eetConfigInfo = new EetConfigInfo();
			eetConfigInfo.setIco((String) row.get("ico"));
			eetConfigInfo.setDic((String) row.get("dic"));
			eetConfigInfo.setId_provoz((String) row.get("id_provozovny"));
            eetConfigInfo.setId_pokl((String) row.get("id_pokl"));
            eetConfigInfo.setKeystorePath((String) row.get("eet_keystore_path"));
            eetConfigInfo.setKeystorePwd((String) row.get("eet_keystore_pwd"));
            eetConfigInfo.setKeyAlias((String) row.get("eet_key_alias"));
            eetConfig.put(eetConfigInfo.getId_provoz(),eetConfigInfo);
		}
		return eetConfig;
	}
}
