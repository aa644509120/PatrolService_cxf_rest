/** * A级 */
/**
 * 
 */
package com.meiah.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author xiegh
 * 
 */
public interface ICommonDao {
	/**
	 * 通过SQL查询结果集
	 * 
	 * @param sql
	 */
	public List<?> find(String sql) throws Exception;

	/**
	 * 通过SQL及参数值paramValues查询符合条件的结果
	 * 
	 * @param sql
	 * @param paramValues
	 */
	public List<?> find(String sql, Object... paramValues) throws Exception;

	/**
	 * 通过SQL及参数值paramValues查询符合条件的结果
	 * 
	 * @param sql
	 * @param paramNames
	 * @param paramValues
	 */
	public List<?> find(String sql, String[] paramNames, Object[] paramValues)
			throws Exception;

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询
	 * 
	 * @param queryName
	 */
	public List<?> findByNamedQuery(String queryName) throws Exception;

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询。同时使用paramValues作 为此SQL中的参数值
	 * 
	 * @param queryName
	 * @param paramValues
	 */
	public List<?> findByNamedQuery(String queryName, Object[] paramValues)
			throws Exception;

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询
	 * 
	 * @param queryName
	 * @param paramNames
	 *            sql中的参数名
	 * @param paramValues
	 *            参数名对应的参数值
	 */
	public List<?> findByNamedQuery(String queryName, String[] paramNames,
			Object[] paramValues) throws Exception;

	/**
	 * 保存单实体
	 * 
	 * @param entity
	 */
	public void save(Object entity) throws Exception;

	/**
	 * 保存或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) throws Exception;

	/**
	 * 保存或更新多个实体
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<?> entities) throws Exception;

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql) throws Exception;

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @param paramValue
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql, Object paramValue) throws Exception;

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @param paramValues
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql, Object[] paramValues) throws Exception;

	/**
	 * 执行增删改的HQL
	 * 
	 * @param hql
	 */
	public int executeBySQL(final String hql) ;

	/**
	 * 执行增删改的HQL
	 * 
	 * @param hql
	 * @param paramNames
	 * @param paramValues
	 */
	public int executeBySQL(final String hql, final String[] paramNames,
			final Object[] paramValues) ;

	/**
	 * find entities by criteria
	 * 
	 * @param criteria
	 */
	public List<?> findByCriteria(DetachedCriteria criteria) throws Exception;

	/**
	 * find entities by criteria
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public List<?> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults) throws Exception;

	/**
	 * get an entity
	 * 
	 * @param clazz
	 * @param id
	 */
	public Object get(Class<?> clazz, Serializable id) throws Exception;

	/**
	 * load an entity
	 * 
	 * @param clazz
	 * @param id
	 */
	public Object load(Class<?> clazz, Serializable id) throws Exception;

	/**
	 * 删除某个实体
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(Object entity) throws Exception;

	/**
	 * 删除多个实体
	 * 
	 * @param entities
	 * @throws Exception
	 */
	public void deleteAll(Collection<?> entities) throws Exception;

	/**
	 * 按此实体的属性做匹配搜索符合条件的实体
	 * 
	 * @param example
	 *            待匹配的实体及其属性值；需注意对外键及主键
	 * @return
	 * @throws Exception
	 */
	public List<?> findByExample(Object example) throws Exception;

	/**
	 * 从指定起始位置开始查询指定个数的结果集
	 * 
	 * @param hql
	 *            使用的查询语句
	 * @param maxRes
	 *            查询的最大结果集
	 * @param first
	 *            查询的起始位置
	 * @return
	 * @throws Exception
	 */
	public List<?> find(String hql, int maxRes, int first) throws Exception;

	/**
	 * 从指定起始位置开始查询指定个数的结果集
	 * 
	 * @param hql
	 * @param maxRes
	 * @param first
	 * @param pvalues
	 *            此HQL中的参数值(即用于替代？的值)
	 * @return
	 * @throws Exception
	 */
	public List<?> find(final String hql, final int maxRes, final int first,
			final Object... pvalues) throws Exception;

	/**
	 * 从指定起始位置开始查询指定个数的结果集
	 * 
	 * @param hql
	 * @param maxRes
	 * @param first
	 * @param pnames
	 *            此HQL中使用到的参数名
	 * @param pvalues
	 *            此HQL中使用到的参数值
	 * @return
	 * @throws Exception
	 */
	public List<?> find(final String hql, final int maxRes, final int first,
			final String[] pnames, final Object[] pvalues) throws Exception;

	/**
	 * 使用原生SQL执行查询
	 * 
	 * @param sql
	 * @param pnames
	 * @param pvalues
	 * @param first
	 * @param max
	 * @return
	 */
	public List<?> findUsingNativeSQL(final String sql, final String[] pnames,
			final Object[] pvalues, final int first, final int max);

	/**
	 * 执行原生SQL
	 * 
	 * @param sql
	 * @param names
	 * @param pvalues
	 */
	public void executeUsingNativeSQL(final String sql, final String[] pnames,
			final Object[] pvalues);

	/**
	 * 如果提供通用方法无法满足需求时，允许披露此数据库操作模板类给接口。
	 * 
	 * @return
	 */
	public HibernateTemplate usingHibernateTemplate();

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询
	 * 
	 * @param queryName
	 *            定义的的query名称
	 * @param paramNames
	 *            sql中的参数名
	 * @param paramValues
	 *            参数名对应的参数值
	 */
	public List<?> findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] paramValues,
			final int first, final int max) throws Exception;
}
