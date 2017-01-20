package essilor.integrator.adapter.datasource;

import org.springframework.util.Assert;

public class DataSourceNameHolder {
	
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	
	public static void setDataSourceName(String dataSourceName) {
		Assert.notNull(dataSourceName, "dataSourceName cannot be null!");
		holder.set(dataSourceName);
	}
	
	public static String getDataSourceName() {
		return holder.get();
	}
		
}
