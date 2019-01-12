package com.my.test.dao.interfaces;

import com.my.test.dao.entity.Page;
import com.my.test.dao.entity.SchedulerConfig;
import com.my.test.dao.entity.SchedulerConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("SchedulerConfigMapper")
public interface SchedulerConfigMapper {
    int countByExample(SchedulerConfigExample example);

    int deleteByExample(SchedulerConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchedulerConfig record);

    int insertSelective(SchedulerConfig record);

    List<SchedulerConfig> selectByExampleWithBLOBs(SchedulerConfigExample example);

    List<SchedulerConfig> selectByExample(SchedulerConfigExample example);

    SchedulerConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchedulerConfig record, @Param("example") SchedulerConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") SchedulerConfig record, @Param("example") SchedulerConfigExample example);

    int updateByExample(@Param("record") SchedulerConfig record, @Param("example") SchedulerConfigExample example);

    int updateByPrimaryKeySelective(SchedulerConfig record);

    int updateByPrimaryKeyWithBLOBs(SchedulerConfig record);

    int updateByPrimaryKey(SchedulerConfig record);

    @SuppressWarnings("unchecked")
    Page selectByPage(SchedulerConfigExample example, Page page);
}