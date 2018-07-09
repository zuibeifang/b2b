package com.manger.core.readWritPart;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author lcg
 * @date 2017-8-2
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		//System.out.println("----获取dataSource1-----"+DynamicDataSourceHolder.getDataSouce());
		return DynamicDataSourceHolder.getDataSouce();
	}

}
