package com.my.test.task;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;
import com.my.test.dao.entity.SchedulerConfig;
import com.my.test.dao.interceptor.Intercepotor;
import com.my.test.task.annotation.Arugument;
import com.my.test.task.annotation.MethodName;

public class ScheduleMappingConfigurer implements BeanDefinitionRegistryPostProcessor, BeanNameAware, Ordered,
		ApplicationContextAware, InitializingBean, BeanFactoryAware {

	public ScheduleMappingConfigurer() {
		super();
	}

	private static Map<String, Method> methodMapping;
	static {
		synchronized (MethodInvokingJobDetailFactoryBean.class) {
			methodMapping = new HashMap<String, Method>();
		}
	}

	private String beanName;
	private String sessionFactoryName;
	private String sessionFactoryClass;
	private ApplicationContext applicationContext;
	private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();
	private BeanDefinitionRegistry registry;
	private final static String REF_PREFIX = "REF$";
	private final static String REF_FUFFIX = "$";
	private DefaultScheduleArugumentResolver resolver = new DefaultScheduleArugumentResolver(
			new FastJsonArubumentConvert());
	private ArugumentRefValueResolver refResolver = new ArugumentRefValueResolver(
			new ArugumentRefValueHelper(REF_PREFIX, REF_FUFFIX));

	private Map<String, String> configMap = new ConcurrentHashMap<String, String>();
	private BeanFactory beanFactory;

	public Resource getJdbcConfig() {
		return jdbcConfig;
	}

	public void setJdbcConfig(Resource jdbcConfig) {
		this.jdbcConfig = jdbcConfig;
	}

	private Resource jdbcConfig;

	public String getSessionFactoryClass() {
		return sessionFactoryClass;
	}

	public void setSessionFactoryClass(String sessionFactoryClass) {
		this.sessionFactoryClass = sessionFactoryClass;
	}

	public String getSessionFactoryName() {
		return sessionFactoryName;
	}

	public void setSessionFactoryName(String sessionFactoryName) {
		this.sessionFactoryName = sessionFactoryName;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	private Map<String, BeanFactoryPostProcessor> getBeans(ConfigurableListableBeanFactory beanFactory) {
		return beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
	}

	public DefaultScheduleArugumentResolver getResolver() {
		return resolver;
	}

	public void setResolver(DefaultScheduleArugumentResolver resolver) {
		this.resolver = resolver;
	}

	static class ArugumentRefValueHelper {
		private String prefix;
		private String suffix;

		public ArugumentRefValueHelper(String prefix, String suffix) {
			super();
			this.prefix = prefix;
			this.suffix = suffix;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String reslove(String value) {
			int startIndex = value.indexOf(prefix);
			int endIndex = value.lastIndexOf(suffix);
			return value.substring(startIndex + prefix.length(), endIndex);
		}

	}

	private static class ArugumentRefValueResolver implements StringValueResolver {

		private ArugumentRefValueHelper hepler;

		public ArugumentRefValueResolver(ArugumentRefValueHelper hepler) {
			super();
			this.hepler = hepler;
		}

		public ArugumentRefValueHelper getHepler() {
			return hepler;
		}

		public void setHepler(ArugumentRefValueHelper hepler) {
			this.hepler = hepler;
		}

		@Override
		public String resolveStringValue(String strVal) {
			return hepler.reslove(strVal);
		}
	}

	@Override
	public void setBeanName(String name) {
		beanName = name;

	}

	@Override
	public int getOrder() {

		return 999999999;
	}

	private List<SchedulerConfig> getEnableSchedulerConfig() {

		Connection conn = getConn();
		final String sql = "select  ID, NAME, JOB_CLASS, JOB_BEAN_NAME, METHOD_NAME, CRON_EXPRESSION, ENABLE_IP, FLAG ,ARGUMENTS,CONCURRENT,DELAY_TIME,EFFECT_TIME,EXPIRE_TIME FROM TF_SCHEDULER_CONF WHERE FLAG=1";
		PreparedStatement pstmt = null;
		List<SchedulerConfig> result = new ArrayList<SchedulerConfig>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String jobclass = rs.getString("JOB_CLASS");
				String jobBeanName = rs.getString("JOB_BEAN_NAME");
				String methodName = rs.getString("METHOD_NAME");
				String cron = rs.getString("CRON_EXPRESSION");
				String ip = rs.getString("ENABLE_IP");
				long delayTime = rs.getLong("DELAY_TIME");
				byte[] arguments = rs.getBytes("ARGUMENTS");
				int concurrent = rs.getInt("CONCURRENT");
				Date effectTime = rs.getDate("EFFECT_TIME");
				Date expireTime = rs.getDate("EXPIRE_TIME");
				int flag = rs.getInt("FLAG");
				result.add(new SchedulerConfig(id, name, jobclass, jobBeanName, methodName, cron, concurrent, ip,
						delayTime, flag, effectTime, expireTime, arguments));

			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	private Connection getConn() {
		String driver = configMap.get("jdbc.driver");
		String url = configMap.get("jdbc.url");
		String username = configMap.get("jdbc.username");
		String password = configMap.get("jdbc.password");
		;
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		this.registry = registry;
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		List<SchedulerConfig> list = getEnableSchedulerConfig();
		if (null != list && !list.isEmpty()) {
			try {
				decorateScheduler(list);

			} catch (Exception e) {
				e.printStackTrace();
				throw new BeanDefinitionStoreException(e.getMessage(), e);
			}

		}

	}

	public void decorateScheduler(List<SchedulerConfig> list) throws Exception {
		if (null != list && !list.isEmpty()) {
			GenericBeanDefinition schedulerFactoryBefinition = new GenericBeanDefinition();
			schedulerFactoryBefinition.setBeanClass(SchedulerFactoryBean.class);
			schedulerFactoryBefinition.setBeanClassName(SchedulerFactoryBean.class.getName());
			String schedulerBeanName = this.beanNameGenerator.generateBeanName(schedulerFactoryBefinition,
					this.registry);
			ManagedList<Object> objList = decorateTrigerBean(list, schedulerBeanName);
			if (null != objList && !objList.isEmpty()) {
				if (this.checkCandidate(schedulerBeanName, schedulerFactoryBefinition)) {
					schedulerFactoryBefinition.getPropertyValues().add("triggers", objList);
					schedulerFactoryBefinition.setLazyInit(true);
					schedulerFactoryBefinition.setAutowireCandidate(true);
					schedulerFactoryBefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
					BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(schedulerFactoryBefinition,
							schedulerBeanName);

					registerBeanDefinition(definitionHolder, registry);

				}
			}
		}
	}

	private ManagedList<Object> decorateTrigerBean(List<SchedulerConfig> list, String parentName) throws Exception {
		
		ManagedList<Intercepotor> interceptors=new ManagedList<Intercepotor>();
		if(beanFactory instanceof ConfigurableListableBeanFactory){
			ConfigurableListableBeanFactory listableBeanFactory=(ConfigurableListableBeanFactory)beanFactory;
			String[] postProcessorNames =
					listableBeanFactory.getBeanNamesForType(Intercepotor.class, true, false);
			if(null!=postProcessorNames&&postProcessorNames.length>0){
				for (String name : postProcessorNames) {
					Intercepotor intercepotor=listableBeanFactory.getBean(name, Intercepotor.class);
					interceptors.add(intercepotor);
				}
			}
			
		}
		
		ManagedList<Object> target = null;
		if (null != list && !list.isEmpty()) {
			target = new ManagedList<Object>(list.size());

			for (SchedulerConfig config : list) {
				// job2
				GenericBeanDefinition jobBefinition = new GenericBeanDefinition();
				jobBefinition.setBeanClass(MethodInvokingJobDetailFactoryBean.class);
				jobBefinition.setBeanClassName(MethodInvokingJobDetailFactoryBean.class.getName());
				jobBefinition.setAutowireCandidate(true);
				jobBefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
				jobBefinition.setLazyInit(false);

				String jobBeanName = this.beanNameGenerator.generateBeanName(jobBefinition, registry);

				if (this.checkCandidate(jobBeanName, jobBefinition)) {
					jobBefinition.getPropertyValues().add("targetObject",
							new RuntimeBeanReference(config.getJobBeanName()));
					jobBefinition.getPropertyValues().add("targetMethod", config.getMethodName());
					jobBefinition.getPropertyValues().add("concurrent",
							Integer.compare(1, config.getConcurrent()) == 0 ? true : false);
					jobBefinition.getPropertyValues().add("filterIp", config.getEnableIp());
					jobBefinition.getPropertyValues().add("effectTime", config.getEffectTime());
					jobBefinition.getPropertyValues().add("expireTime", config.getExpireTime());
					jobBefinition.getPropertyValues().add("interceptors", interceptors);

					fillAruguments(jobBefinition, config);
					BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(jobBefinition, jobBeanName);
					registerBeanDefinition(definitionHolder, registry);

				}
				String cornBeanName = null;
				GenericBeanDefinition cronTriggerBefinition = new GenericBeanDefinition();
				try {
					cronTriggerBefinition.setBeanClass(CronTriggerFactoryBean.class);
					cronTriggerBefinition.setBeanClassName(CronTriggerFactoryBean.class.getName());
					cornBeanName = this.beanNameGenerator.generateBeanName(cronTriggerBefinition, registry);
				} catch (Exception e) {
					throw e;
				}

				if (this.checkCandidate(cornBeanName, cronTriggerBefinition)) {
					cronTriggerBefinition.getPropertyValues().add("jobDetail", new RuntimeBeanReference(jobBeanName));
					cronTriggerBefinition.getPropertyValues().add("cronExpression", config.getCronExpression());
					cronTriggerBefinition.getPropertyValues().add("startDelay", config.getDelayTime());
					cronTriggerBefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
					BeanDefinitionHolder corndefinitionHolder = new BeanDefinitionHolder(cronTriggerBefinition,
							cornBeanName);
					registerBeanDefinition(corndefinitionHolder, registry);
					target.add(new RuntimeBeanReference(cornBeanName));

				}

			}

		}

		return target;
	}

	private void fillAruguments(GenericBeanDefinition jobBefinition, SchedulerConfig config)
			throws LinkageError, Exception {
		Class<?> targetClass = beanFactory.getType(config.getJobBeanName());
		Method method = getTagetMethod(targetClass, config.getMethodName());
		jobBefinition.getPropertyValues().add("methodObject", method);
		if (null != method && !StringUtils.isEmpty(config.getArguments())) {
			LocalVariableTableParameterNameDiscoverer discover = new LocalVariableTableParameterNameDiscoverer();
			String[] argNames = discover.getParameterNames(method);
			Parameter[] parmter = method.getParameters();
			Class<?>[] types = method.getParameterTypes();
			if (parmter != null && parmter.length > 0) {
				ManagedList<Object> manageList = new ManagedList<Object>(parmter.length);
				Object[] aruguments = new Object[parmter.length];
				for (int i = 0; i < parmter.length; i++) {
					String argumentName = null;
					if (parmter[i].isAnnotationPresent(Arugument.class)) {
						argumentName = parmter[i].getAnnotation(Arugument.class).name();
					}
					if (StringUtils.isEmpty(argumentName)) {
						argumentName = argNames[i];
					}
					Class<?> type = types[i];
					Object obj = null;
					if (parmter.length == 1) {
						obj = resolver.resolve(new String(config.getArguments(), "UTF-8"), type);
					} else {
						obj = resolver.resolve(new String(config.getArguments(), "UTF-8"), argumentName, type);
					}

					if (null != obj && obj instanceof String) {
						String value = (String) obj;
						if (value.startsWith(REF_PREFIX) && value.endsWith(REF_FUFFIX)) {
							value = refResolver.resolveStringValue(value);
							if (StringUtils.hasLength(value)) {
								obj = new RuntimeBeanReference(value);
							}
						}

					}
					aruguments[i] = obj;
					manageList.add(obj);

				}
				jobBefinition.getPropertyValues().add("arguments", manageList);

			}

		}

	}

	private Method getTagetMethod(Class<?> targetClass, String beanPath) {
		synchronized (MethodInvokingJobDetailFactoryBean.class) {
			if (methodMapping.containsKey(beanPath)) {
				return methodMapping.get(beanPath);
			}
			Method[] methods = targetClass.getDeclaredMethods();
			if (null != methods && methods.length > 0) {
				for (Method method : methods) {
					if (method.isAnnotationPresent(MethodName.class)) {
						MethodName path = method.getAnnotation(MethodName.class);
						if (path.value().equals(beanPath)) {
							methodMapping.put(beanPath, method);
							return method;
						}
					}
				}
			}
		}
		return null;
	}

	protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
		if (!this.registry.containsBeanDefinition(beanName)) {
			return true;
		}
		BeanDefinition existingDef = this.registry.getBeanDefinition(beanName);
		BeanDefinition originatingDef = existingDef.getOriginatingBeanDefinition();
		if (originatingDef != null) {
			existingDef = originatingDef;
		}
		if (isCompatible(beanDefinition, existingDef)) {
			return false;
		}
		throw new ConflictingBeanDefinitionException("Annotation-specified bean name '" + beanName
				+ "' for bean class [" + beanDefinition.getBeanClassName() + "] conflicts with existing, "
				+ "non-compatible bean definition of same name and class [" + existingDef.getBeanClassName() + "]");
	}

	protected boolean isCompatible(BeanDefinition newDefinition, BeanDefinition existingDefinition) {
		return (!(existingDefinition instanceof ScannedGenericBeanDefinition) || // explicitly
																					// registered
																					// overriding
																					// bean
				newDefinition.getSource().equals(existingDefinition.getSource()) || // scanned
																					// same
																					// file
																					// twice
				newDefinition.equals(existingDefinition)); // scanned equivalent
															// class twice
	}

	protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
		BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	@SuppressWarnings("serial")
	class ConflictingBeanDefinitionException extends IllegalStateException {

		public ConflictingBeanDefinitionException(String message) {
			super(message);
		}

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = new Properties();
		properties.load(jdbcConfig.getInputStream());
		if (!properties.isEmpty()) {
			for (Object key : properties.keySet()) {
				configMap.putIfAbsent(key.toString(), properties.getProperty(key.toString()));
			}
		}

	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
