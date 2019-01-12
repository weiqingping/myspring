package com.my.test.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchedulerConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchedulerConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andJobClassIsNull() {
            addCriterion("JOB_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andJobClassIsNotNull() {
            addCriterion("JOB_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andJobClassEqualTo(String value) {
            addCriterion("JOB_CLASS =", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassNotEqualTo(String value) {
            addCriterion("JOB_CLASS <>", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassGreaterThan(String value) {
            addCriterion("JOB_CLASS >", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_CLASS >=", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassLessThan(String value) {
            addCriterion("JOB_CLASS <", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassLessThanOrEqualTo(String value) {
            addCriterion("JOB_CLASS <=", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassLike(String value) {
            addCriterion("JOB_CLASS like", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassNotLike(String value) {
            addCriterion("JOB_CLASS not like", value, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassIn(List<String> values) {
            addCriterion("JOB_CLASS in", values, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassNotIn(List<String> values) {
            addCriterion("JOB_CLASS not in", values, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassBetween(String value1, String value2) {
            addCriterion("JOB_CLASS between", value1, value2, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobClassNotBetween(String value1, String value2) {
            addCriterion("JOB_CLASS not between", value1, value2, "jobClass");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameIsNull() {
            addCriterion("JOB_BEAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameIsNotNull() {
            addCriterion("JOB_BEAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameEqualTo(String value) {
            addCriterion("JOB_BEAN_NAME =", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameNotEqualTo(String value) {
            addCriterion("JOB_BEAN_NAME <>", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameGreaterThan(String value) {
            addCriterion("JOB_BEAN_NAME >", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_BEAN_NAME >=", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameLessThan(String value) {
            addCriterion("JOB_BEAN_NAME <", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameLessThanOrEqualTo(String value) {
            addCriterion("JOB_BEAN_NAME <=", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameLike(String value) {
            addCriterion("JOB_BEAN_NAME like", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameNotLike(String value) {
            addCriterion("JOB_BEAN_NAME not like", value, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameIn(List<String> values) {
            addCriterion("JOB_BEAN_NAME in", values, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameNotIn(List<String> values) {
            addCriterion("JOB_BEAN_NAME not in", values, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameBetween(String value1, String value2) {
            addCriterion("JOB_BEAN_NAME between", value1, value2, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andJobBeanNameNotBetween(String value1, String value2) {
            addCriterion("JOB_BEAN_NAME not between", value1, value2, "jobBeanName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("METHOD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("METHOD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("METHOD_NAME =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("METHOD_NAME <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("METHOD_NAME >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("METHOD_NAME <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("METHOD_NAME like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("METHOD_NAME not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("METHOD_NAME in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("METHOD_NAME not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("METHOD_NAME between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("METHOD_NAME not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNull() {
            addCriterion("CRON_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIsNotNull() {
            addCriterion("CRON_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCronExpressionEqualTo(String value) {
            addCriterion("CRON_EXPRESSION =", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <>", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThan(String value) {
            addCriterion("CRON_EXPRESSION >", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION >=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThan(String value) {
            addCriterion("CRON_EXPRESSION <", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLessThanOrEqualTo(String value) {
            addCriterion("CRON_EXPRESSION <=", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionLike(String value) {
            addCriterion("CRON_EXPRESSION like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotLike(String value) {
            addCriterion("CRON_EXPRESSION not like", value, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionIn(List<String> values) {
            addCriterion("CRON_EXPRESSION in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotIn(List<String> values) {
            addCriterion("CRON_EXPRESSION not in", values, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andCronExpressionNotBetween(String value1, String value2) {
            addCriterion("CRON_EXPRESSION not between", value1, value2, "cronExpression");
            return (Criteria) this;
        }

        public Criteria andConcurrentIsNull() {
            addCriterion("CONCURRENT is null");
            return (Criteria) this;
        }

        public Criteria andConcurrentIsNotNull() {
            addCriterion("CONCURRENT is not null");
            return (Criteria) this;
        }

        public Criteria andConcurrentEqualTo(Integer value) {
            addCriterion("CONCURRENT =", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentNotEqualTo(Integer value) {
            addCriterion("CONCURRENT <>", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentGreaterThan(Integer value) {
            addCriterion("CONCURRENT >", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentGreaterThanOrEqualTo(Integer value) {
            addCriterion("CONCURRENT >=", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentLessThan(Integer value) {
            addCriterion("CONCURRENT <", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentLessThanOrEqualTo(Integer value) {
            addCriterion("CONCURRENT <=", value, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentIn(List<Integer> values) {
            addCriterion("CONCURRENT in", values, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentNotIn(List<Integer> values) {
            addCriterion("CONCURRENT not in", values, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentBetween(Integer value1, Integer value2) {
            addCriterion("CONCURRENT between", value1, value2, "concurrent");
            return (Criteria) this;
        }

        public Criteria andConcurrentNotBetween(Integer value1, Integer value2) {
            addCriterion("CONCURRENT not between", value1, value2, "concurrent");
            return (Criteria) this;
        }

        public Criteria andEnableIpIsNull() {
            addCriterion("ENABLE_IP is null");
            return (Criteria) this;
        }

        public Criteria andEnableIpIsNotNull() {
            addCriterion("ENABLE_IP is not null");
            return (Criteria) this;
        }

        public Criteria andEnableIpEqualTo(String value) {
            addCriterion("ENABLE_IP =", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpNotEqualTo(String value) {
            addCriterion("ENABLE_IP <>", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpGreaterThan(String value) {
            addCriterion("ENABLE_IP >", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpGreaterThanOrEqualTo(String value) {
            addCriterion("ENABLE_IP >=", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpLessThan(String value) {
            addCriterion("ENABLE_IP <", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpLessThanOrEqualTo(String value) {
            addCriterion("ENABLE_IP <=", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpLike(String value) {
            addCriterion("ENABLE_IP like", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpNotLike(String value) {
            addCriterion("ENABLE_IP not like", value, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpIn(List<String> values) {
            addCriterion("ENABLE_IP in", values, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpNotIn(List<String> values) {
            addCriterion("ENABLE_IP not in", values, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpBetween(String value1, String value2) {
            addCriterion("ENABLE_IP between", value1, value2, "enableIp");
            return (Criteria) this;
        }

        public Criteria andEnableIpNotBetween(String value1, String value2) {
            addCriterion("ENABLE_IP not between", value1, value2, "enableIp");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIsNull() {
            addCriterion("DELAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIsNotNull() {
            addCriterion("DELAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDelayTimeEqualTo(Long value) {
            addCriterion("DELAY_TIME =", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotEqualTo(Long value) {
            addCriterion("DELAY_TIME <>", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeGreaterThan(Long value) {
            addCriterion("DELAY_TIME >", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("DELAY_TIME >=", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeLessThan(Long value) {
            addCriterion("DELAY_TIME <", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeLessThanOrEqualTo(Long value) {
            addCriterion("DELAY_TIME <=", value, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeIn(List<Long> values) {
            addCriterion("DELAY_TIME in", values, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotIn(List<Long> values) {
            addCriterion("DELAY_TIME not in", values, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeBetween(Long value1, Long value2) {
            addCriterion("DELAY_TIME between", value1, value2, "delayTime");
            return (Criteria) this;
        }

        public Criteria andDelayTimeNotBetween(Long value1, Long value2) {
            addCriterion("DELAY_TIME not between", value1, value2, "delayTime");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andEffectTimeIsNull() {
            addCriterion("EFFECT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEffectTimeIsNotNull() {
            addCriterion("EFFECT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEffectTimeEqualTo(Date value) {
            addCriterion("EFFECT_TIME =", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeNotEqualTo(Date value) {
            addCriterion("EFFECT_TIME <>", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeGreaterThan(Date value) {
            addCriterion("EFFECT_TIME >", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EFFECT_TIME >=", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeLessThan(Date value) {
            addCriterion("EFFECT_TIME <", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeLessThanOrEqualTo(Date value) {
            addCriterion("EFFECT_TIME <=", value, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeIn(List<Date> values) {
            addCriterion("EFFECT_TIME in", values, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeNotIn(List<Date> values) {
            addCriterion("EFFECT_TIME not in", values, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeBetween(Date value1, Date value2) {
            addCriterion("EFFECT_TIME between", value1, value2, "effectTime");
            return (Criteria) this;
        }

        public Criteria andEffectTimeNotBetween(Date value1, Date value2) {
            addCriterion("EFFECT_TIME not between", value1, value2, "effectTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("EXPIRE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("EXPIRE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("EXPIRE_TIME =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("EXPIRE_TIME <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("EXPIRE_TIME >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EXPIRE_TIME >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("EXPIRE_TIME <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("EXPIRE_TIME <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("EXPIRE_TIME in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("EXPIRE_TIME not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("EXPIRE_TIME between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("EXPIRE_TIME not between", value1, value2, "expireTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}