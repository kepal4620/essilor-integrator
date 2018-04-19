package essilor.integrator.adapter.datasource;

import essilor.integrator.adapter.tools.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

public class CustomDriverManagerDataSource  extends DriverManagerDataSource {

	@Autowired
	private Encryptor encryptor;

    private String  encrypt;

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getEncrypt() {
		return this.encrypt;
	}


    @PostConstruct
    public void postConstruct() {
        try {
            if ("true".equalsIgnoreCase(encrypt)) {
                super.setPassword(encryptor.decrypt(super.getPassword()));
            }
        } catch(Exception e) {
            new RuntimeException(e);
        }
    }

//	@Override
//	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//		return null;
//	}

	@Override
	public Logger getParentLogger() {
		return null;
	}
}
