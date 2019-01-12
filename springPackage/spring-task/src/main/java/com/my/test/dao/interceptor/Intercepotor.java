package com.my.test.dao.interceptor;

import java.util.Map;

import com.my.test.task.DataContainer;

public interface Intercepotor {
   boolean test(DataContainer o)throws Exception;

}
