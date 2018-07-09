package com.manger.common.mybatis;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页拦截器
 * @author lcg
 * @date 2017-7-28
 */

@Intercepts({@Signature(type= Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class QueryInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(QueryInterceptor.class);
	private static String DATABASE_MYSQL="mysql";
	private static String DATABASE_ORACLE="oracle";
	
	private String databaseType;//配置数据库的类型
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] objects=invocation.getArgs();
		MappedStatement mappedStatement=(MappedStatement)objects[0];
		synchronized(mappedStatement) {
			SqlSource sqlSource=mappedStatement.getSqlSource();
			Object parms=objects[1];//sql中的参数
			BoundSql boundSql=sqlSource.getBoundSql(parms);
			if(mappedStatement.getId().toUpperCase().endsWith("PAGE")&& boundSql.getSql().toUpperCase().startsWith("SELECT")){//如果不是分页语句直接跳过
				RowBounds rowBounds=(RowBounds)objects[2];
				String sql=getPageSql(boundSql.getSql(),databaseType,rowBounds.getOffset(),rowBounds.getLimit());
				
				logger.debug("parameter:"+invocation.getArgs()[1]+" sql:"+sql);
				
				BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
				MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));
				objects[0]=newMs;
				Invocation newInvocation=new Invocation(invocation.getTarget(),invocation.getMethod(),objects);
				
				return newInvocation.proceed();
			}else{
				return invocation.proceed();
			}
		}
	}

	@Override
	public Object plugin(Object target) {
		
		return Plugin.wrap(target,this);
	}

	@Override
	public void setProperties(Properties properties) {
		databaseType = properties.getProperty("databaseType");//获取配置的数据库类型
		if(databaseType==null){
			throw new RuntimeException("mybatis interceptor property 'databaseType' can not be null");
		}
	}
    
	/**
	 * 
	 * @param sql
	 * @param databaseType
	 * @return
	 */
	public String getPageSql(String sql,String databaseType,int offsetPlaceholder,int limitPlaceholder){
		
		if(databaseType.toLowerCase().equals(DATABASE_MYSQL)){//mysql
			StringBuffer sb=new StringBuffer(sql);
			sb.append(" limit " + offsetPlaceholder + "," + limitPlaceholder);
			return sb.toString();
		}
		if(databaseType.toLowerCase().equals(DATABASE_ORACLE)){//oracle
			sql = sql.trim();
			boolean isForUpdate = false;
			if ( sql.toLowerCase().endsWith(" for update") ) {
				sql = sql.substring( 0, sql.length()-11 );
				isForUpdate = true;
			}
			StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
			if (offsetPlaceholder > 0) {
				pagingSelect.append("select * from (select row_.*, rownum rownum_ from ( ");
			}
			else {
				pagingSelect.append("select row_.*, rownum rownum_ from ( ");
			}
			pagingSelect.append(sql);
			if (offsetPlaceholder > 0) {
				String endString = offsetPlaceholder+"+"+limitPlaceholder;
				pagingSelect.append(" ) row_ where rownum<= "+endString+" ) where  rownum_ > "+offsetPlaceholder);
			}
			else {
				pagingSelect.append(") row_ where rownum<=" + limitPlaceholder);
			}

			if ( isForUpdate ) {
				pagingSelect.append( " for update" );
			}
			
			return pagingSelect.toString();
		}
		return sql;
	}
	
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
			Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
			
			builder.resource(ms.getResource());
			builder.fetchSize(ms.getFetchSize());
			builder.statementType(ms.getStatementType());
			builder.keyGenerator(ms.getKeyGenerator());
			
			String[] keys=ms.getKeyProperties();
			if(keys!=null){
				StringBuffer aa=new StringBuffer();
				for(String a:keys){
					aa.append(a).append(",");
				}
				String key=aa.toString().substring(0, aa.length()-1);
				builder.keyProperty(key);
			}
			

			
			//setStatementTimeout()
			builder.timeout(ms.getTimeout());
			
			//setStatementResultMap()
			builder.parameterMap(ms.getParameterMap());
			
			//setStatementResultMap()
			builder.resultMaps(ms.getResultMaps());
			builder.resultSetType(ms.getResultSetType());
		    
			//setStatementCache()
			builder.cache(ms.getCache());
			builder.flushCacheRequired(ms.isFlushCacheRequired());
			builder.useCache(ms.isUseCache());
			
			return builder.build();
		}
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
	
	
	public String concatCountSql(String sql){
        StringBuffer sb=new StringBuffer("select count(*) from ");
        sql=sql.toLowerCase();
        
        if(sql.lastIndexOf("order")>sql.lastIndexOf(")")){
            sb.append(sql.substring(sql.indexOf("from")+4, sql.lastIndexOf("order")));
        }else{
            sb.append(sql.substring(sql.indexOf("from")+4));
        }
        return sb.toString();
    }
}