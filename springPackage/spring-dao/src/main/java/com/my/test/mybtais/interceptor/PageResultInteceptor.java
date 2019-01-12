package com.my.test.mybtais.interceptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
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
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.springframework.util.StringUtils;

import com.my.test.dao.entity.Page;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PageResultInteceptor implements Interceptor {


	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		if (target instanceof DefaultResultSetHandler) {
			
			DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler) target;
			MetaObject metaResultSetHandler = MetaObject.forObject(resultSetHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory()); 
			 ParameterHandler parameterHandler = (ParameterHandler) metaResultSetHandler.getValue("parameterHandler");  
			 Object parameterObject = parameterHandler.getParameterObject();  
			 		
			  
			 Page pagination = null;  
  
            if(parameterObject instanceof MapperMethod.ParamMap){  
  
                MapperMethod.ParamMap paramMapObject = (MapperMethod.ParamMap)parameterObject ;  
               
                if(paramMapObject != null){  
                    for(Object key : paramMapObject.keySet()){  
                        if(paramMapObject.get(key) instanceof  Page){  
                            pagination = (Page) paramMapObject.get(key);  
                            break;  
                        }  
                    }  
                }  
            }  
  
            if (pagination != null) {  
            	BoundSql boundSql = (BoundSql) metaResultSetHandler.getValue("parameterHandler.boundSql");	
            	Configuration configuration = (Configuration) metaResultSetHandler.getValue("configuration");                 
            	Connection connection = (Connection) metaResultSetHandler.getValue("executor.delegate.transaction.connection");
                String originalSql = boundSql.getSql();  
                String dialect=(String) configuration.getVariables().get("dialect");
                String sql = DialectConfig.getDialectMap().get(dialect).getCountString(originalSql);  
                int totalRecord = getTotalRecord(connection, sql, parameterHandler);  
                Object result = invocation.proceed();  
                pagination.setTotal(totalRecord);  
                pagination.setResult((List)result);
                List<Page>list=new ArrayList<Page>(1);
                list.add(pagination);
  
//            
  
                return list;  
            } 
			

		}
	
		return invocation.proceed();
	}
	

	private int getTotalRecord(Connection connection,String sql,ParameterHandler parameterHandler) throws SQLException{  
        PreparedStatement preparedStatement = null;  
        ResultSet resultSet = null;  
        try {  
  
            preparedStatement = connection.prepareStatement(sql);  
            parameterHandler.setParameters(preparedStatement);  
            resultSet = preparedStatement.executeQuery();  
            if(resultSet.next()){
            	return resultSet.getInt(1);
            }
  
            return 0;  
        } catch (SQLException e) {  
            e.printStackTrace();  
            return 0;
        }finally {  
        	if(null!=resultSet){
            	resultSet.close();

        	}
        	if(null!=preparedStatement){
            	preparedStatement.close();

        	}
        }  
    }  


	@Override
	public Object plugin(Object target) {
		
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		

	}

}
