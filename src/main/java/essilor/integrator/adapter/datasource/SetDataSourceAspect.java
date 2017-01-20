package essilor.integrator.adapter.datasource;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.utils.PropertiesUtil;

@Aspect
public class SetDataSourceAspect implements ApplicationContextAware {

	private static final Logger logger = Logger.getLogger(SetDataSourceAspect.class);
	
	private ApplicationContext ctx;
		
	@Before("execution(* essilor.integrator.adapter.service.LogService.logResult(..))")
	@Order(Ordered.LOWEST_PRECEDENCE)
	public void setDataSourceForLogService() {
		logger.trace("in pointcut");
		PropertiesUtil props = ctx.getBean("properties",PropertiesUtil.class);
		logger.debug("ds: " + props.getProperty("adapter.main.dataSource"));
		DataSourceNameHolder.setDataSourceName(props.getProperty("adapter.main.dataSource"));
		logger.trace("out pointcut");
	}
	
	
	@Before("execution(* essilor.integrator.adapter.service.AdapterService.*(..)) && args(request,..)")
	public void setDataSourceForAdapterService(AdapterRequest request) {
		logger.trace("in pointcut");
		logger.debug("ds: " + request.getDataSourceName());
		DataSourceNameHolder.setDataSourceName(request.getDataSourceName());
		logger.trace("out pointcut");
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

}
