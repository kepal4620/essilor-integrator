package essilor.integrator.adapter.datasource;

import essilor.integrator.adapter.tools.Encryptor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.logging.Logger;

public class CustomDriverManagerDataSource  extends DriverManagerDataSource {

	private String  encrypt;

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getEncrypt() {
		return this.encrypt;
	}



	@Override
	public void setPassword(String password) {
		try {
			if ("true".equalsIgnoreCase(password)) {
				Encryptor e = Encryptor.getInstance();
				super.setPassword(e.decrypt(password));
			} else {
				super.setPassword(password);
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
