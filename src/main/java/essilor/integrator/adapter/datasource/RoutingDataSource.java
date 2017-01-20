package essilor.integrator.adapter.datasource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceNameHolder.getDataSourceName();
	}

	@Override
	public Logger getParentLogger()  {
		return null;
	}

}
