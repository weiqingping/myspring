package com.my.test.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.my.test.dao.entity.SchedulerConfig;
import com.my.test.dao.entity.SchedulerConfigExample;
import com.my.test.dao.interfaces.SchedulerConfigMapper;

public class SchedulerInDBFactoryBean extends SchedulerFactoryBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;  
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
	 this.applicationContext=applicationContext;
	}


	private static List<SchedulerConfig>configList;
	static{
		configList=new ArrayList<>();
	}
	@Override
	public void afterPropertiesSet() throws Exception {
	    initSheudeConfig();
	    
		super.afterPropertiesSet();
	}
	
	
	private void initSheudeConfig(){
		synchronized(SchedulerInDBFactoryBean.class){
		SchedulerConfigMapper  mapper=applicationContext.getBean(SchedulerConfigMapper.class);
		SchedulerConfigExample exapme=new SchedulerConfigExample();
		SchedulerConfigExample.Criteria creation=exapme.createCriteria();
		creation.andFlagEqualTo(1);
		configList= mapper.selectByExample(exapme);
		}
		
	}

}
