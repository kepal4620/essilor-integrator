package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;

import java.util.Map;

public interface ConfDao {

	String getOrganisation();
	String getCity();
	String getStreet();
	String getZipCode();
	String getOrdersDir();
	String getSoftwareOriginatorName();
	String getSoftwareSenderName();
	String getSoftwareOriginatorVersion();
	String getSoftwareSenderVersion();
	String getDic();
	String getIdProvoz();
	String getIdPokl();
	String getEetUri();
	String getEetKeystoreType();
	Map<String, EetConfigInfo> getEetConfig();
}
