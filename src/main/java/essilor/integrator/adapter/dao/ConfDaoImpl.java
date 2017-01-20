package essilor.integrator.adapter.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ConfDaoImpl implements ConfDao {

	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select val from conf_ini where var = ?";
	
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

	
}
