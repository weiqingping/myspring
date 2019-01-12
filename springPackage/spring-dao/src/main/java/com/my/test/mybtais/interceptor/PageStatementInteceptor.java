package com.my.test.mybtais.interceptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.springframework.util.StringUtils;

import com.my.test.dao.entity.Page;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageStatementInteceptor implements Interceptor {



	private String dialect;

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		if (target instanceof RoutingStatementHandler) {
			RoutingStatementHandler statmentHander = (RoutingStatementHandler) target;
			BaseStatementHandler baseHanlder = (BaseStatementHandler) getFiledVlue(target, "delegate");
			BoundSql boundSql = baseHanlder.getBoundSql();
			Object obj = boundSql.getParameterObject();
			Page page = null;
			if (obj instanceof MapperMethod.ParamMap) {
				MapperMethod.ParamMap map = (MapperMethod.ParamMap) obj;
				if (null != map) {
					Object pageObj = map.values().stream().filter((ob) -> ob instanceof Page).findFirst().get();
					if (null != pageObj) {
						page = (Page) pageObj;
					}
				}
			} else {
				if (obj instanceof Page) {
					page = (Page) obj;
				}
			}

			if (null != page&&StringUtils.hasText(dialect)) {
				Dialect dia= DialectConfig.getDialectMap().get(dialect);
            	Configuration configuration = (Configuration)getFiledVlue(baseHanlder,"configuration"); 
            	if(configuration.getVariables()==null){
            		configuration.setVariables(new Properties());
            	}

                configuration.getVariables().setProperty("dialect", this.dialect);

				String pageSql=dia.getPageSql(boundSql.getSql(), page.getPageNumber()*page.getPageSize(), page.getPageSize());
				setFiledVlue(boundSql, "sql", pageSql);
				
			}
			
		
			
			

		}
		
		return invocation.proceed();
	}
	
	private  void queryTotalNumber(Page page,  
	           MappedStatement mappedStatement, Connection connection,Dialect dia)throws Exception{ 
	       BoundSql boundSql = mappedStatement.getBoundSql(page);  
	       String sql = boundSql.getSql();  
	       String countSql = dia.getCountString(sql) ;
	       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
	       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);  
	       ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);  
	       PreparedStatement pstmt = null;  
	       ResultSet rs = null;  
	       try {  
	           pstmt = connection.prepareStatement(countSql);  
	           parameterHandler.setParameters(pstmt);  
	           rs = pstmt.executeQuery();  
	           if (rs.next()) {  
	              int totalRecord = rs.getInt(1);  
	              page.setTotal(totalRecord);  
	           }  
	       } catch (SQLException e) {  
	           e.printStackTrace();  
	       } finally {  
	           try {  
	              if (rs != null)  
	                  rs.close();  
	               if (pstmt != null)  
	                  pstmt.close();  
	           } catch (SQLException e) {  
	              e.printStackTrace();  
	           }  
	       }  

	}

	private Object getFiledVlue(Object target, String fieldName)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field =null;
		try {
			 field = target.getClass().getDeclaredField(fieldName);

		} catch (Exception e) {
			if(e instanceof NoSuchFieldException){
				 Class superClass=target.getClass().getSuperclass();
				 field = superClass.getDeclaredField(fieldName);
			}else{
				throw e;
			}
		}
	
		field.setAccessible(true);
		return field.get(target);

	}

	private void setFiledVlue(Object target, String fieldName, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = target.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(target, value);

	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
