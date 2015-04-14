/** * A级 */
/**
 * 
 */
package com.meiah.core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 数据库访问对象类。具体模块的dao可继承此类(同时最好创建该模块接口继承ICommonDao)
 * 
 * @author xiegh
 * 
 */
@SuppressWarnings("unchecked")
@Repository("hibernateCommonDao")
public class HibernateCommonDao extends HibernateDaoSupport implements
		ICommonDao {
	/** 声明日志通用类，使用protected以便子类也可以使用 */
	protected Log log = LogFactory.getLog(getClass());

	/**
	 * 注入sessionFactory，同时声明为私有，以便外部直接使用<code>HibernateTemplate</code>而非
	 * <code>SessionFactory</code>
	 */
	@Resource(name = "sessionFactory")
	private SessionFactory _sessionFactory;

	/**
	 * 此处添加此private方法是为了注入sessionFactory到HibernateDaoSupport中去，
	 * 同时为了保证此方法不被其他的方法所引用
	 */
	@SuppressWarnings("unused")
	@PostConstruct
	private void setupMySessionFactory() {
		super.setSessionFactory(_sessionFactory);
	}

	/**
	 * 通过SQL查询结果集
	 * 
	 * @param sql
	 */
	public List<?> find(String sql) throws Exception {
		return super.getHibernateTemplate().find(sql);
	}

	/**
	 * 通过SQL及参数值paramValues查询符合条件的结果
	 * 
	 * @param sql
	 * @param paramValues
	 */
	public List<?> find(String sql, Object... paramValues) throws Exception {
		return super.getHibernateTemplate().find(sql, paramValues);
	}

	/**
	 * 通过SQL及参数值paramValues查询符合条件的结果
	 * 
	 * @param sql
	 * @param paramNames
	 * @param paramValues
	 */
	public List<?> find(String sql, String[] paramNames, Object[] paramValues)
			throws Exception {
		List<?> list = super.getHibernateTemplate().findByNamedParam(sql,
				paramNames, paramValues);
		return list;
	}

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询
	 * 
	 * @param queryName
	 */
	public List<?> findByNamedQuery(String queryName) throws Exception {
		List<?> list = super.getHibernateTemplate().findByNamedQuery(queryName);
		return list;
	}

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询。同时使用paramValues作 为此SQL中的参数值
	 * 
	 * @param queryName
	 * @param paramValues
	 */
	public List<?> findByNamedQuery(String queryName, Object[] paramValues)
			throws Exception {
		List<?> list = super.getHibernateTemplate().findByNamedQuery(queryName,
				paramValues);
		return list;
	}

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
			Object[] paramValues) throws Exception {
		List<?> list = super.getHibernateTemplate()
				.findByNamedQueryAndNamedParam(queryName, paramNames,
						paramValues);
		return list;
	}

	/**
	 * 使用hibernate配置文件中声明的name为queryName的SQL来执行查询
	 * 
	 * @param queryName
	 * @param paramNames
	 *            sql中的参数名
	 * @param paramValues
	 *            参数名对应的参数值
	 * @param max
	 *            查询的最大结果集
	 * @param first
	 *            查询的初始列
	 */
	public List<?> findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] paramValues,
			final int first, final int max) throws Exception {
		List res = (List) super.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						Query q = s.getNamedQuery(queryName).setMaxResults(max)
								.setFirstResult(first);
						if (paramNames != null && paramNames.length > 0) {
							for (int i = 0; i < paramValues.length; i++) {
								q.setParameter(paramNames[i], paramValues[i]);
							}
						}

						return q.list();
					}
				});

		return res;
	}

	/**
	 * 保存单实体
	 * 
	 * @param entity
	 */
	public void save(Object entity) throws Exception {
		super.getHibernateTemplate().save(entity);
	}

	/**
	 * 保存或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) throws Exception {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 保存或更新多个实体
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<?> entities) throws Exception {
		super.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql) throws Exception {
		int updateRows = super.getHibernateTemplate().bulkUpdate(sql);
		return updateRows;
	}

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @param paramValue
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql, Object paramValue) throws Exception {
		int updateRows = super.getHibernateTemplate().bulkUpdate(sql,
				paramValue);
		return updateRows;
	}

	/**
	 * 执行此SQl，返回更新的数据库行数
	 * 
	 * @param paramValues
	 * @return 更新的行数
	 */
	public int bulkUpdate(String sql, Object[] paramValues) throws Exception {
		int updateRows = super.getHibernateTemplate().bulkUpdate(sql,
				paramValues);
		return updateRows;
	}

	/**
	 * 执行增删改的HQL
	 * 
	 * @param hql
	 */
	public int executeBySQL(final String hql){
		return executeBySQL(hql, null, null);
	}

	/**
	 * 执行增删改的HQL
	 * 
	 * @param hql
	 * @param paramNames
	 * @param paramValues
	 */
	public int executeBySQL(final String hql, final String[] paramNames,
			final Object[] paramValues) {
		int effectRows = super.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			
			public Integer doInHibernate(Session s) throws HibernateException,
					SQLException {
				org.hibernate.Query query = s.createQuery(hql);
				if (paramNames != null && paramNames.length > 0) {
					for (int i = 0; i < paramNames.length; i++) {
						// 在update-delete等情况下，hibernate无法自动识别List类型参数，故此处手工添加识别条件。 modified by xiegh,2012/10/23
						if(Collection.class.isAssignableFrom(paramValues[i].getClass())) {
							query.setParameterList(paramNames[i], (Collection)paramValues[i]);
						} else {
							query.setParameter(paramNames[i], paramValues[i]);
						}
					}
				}
				return query.executeUpdate();
			}
		});
		return effectRows;
	}

	/**
	 * find entities by criteria
	 * 
	 * @param criteria
	 */
	public List<?> findByCriteria(DetachedCriteria criteria) throws Exception {
		List<?> list = super.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

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
			int maxResults) throws Exception {
		List<?> list = super.getHibernateTemplate().findByCriteria(criteria,
				firstResult, maxResults);
		return list;
	}

	/**
	 * get an entity
	 * 
	 * @param clazz
	 * @param id
	 */
	public Object get(Class<?> clazz, Serializable id) throws Exception {
		return super.getHibernateTemplate().get(clazz, id);
	}

	/**
	 * load an entity
	 * 
	 * @param clazz
	 * @param id
	 */
	public Object load(Class<?> clazz, Serializable id) throws Exception {
		return super.getHibernateTemplate().load(clazz, id);
	}

	/**
	 * 删除某个实体
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(Object entity) throws Exception {
		super.getHibernateTemplate().delete(entity);
	}

	/**
	 * 删除多个实体
	 * 
	 * @param entities
	 * @throws Exception
	 */
	public void deleteAll(Collection<?> entities) throws Exception {
		super.getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 按此实体的属性做匹配搜索符合条件的实体
	 * 
	 * @param example
	 *            待匹配的实体及其属性值；需注意对外键及主键
	 * @return
	 * @throws Exception
	 */
	public List<?> findByExample(Object example) throws Exception {
		return super.getHibernateTemplate().findByExample(example);
	}

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
	public List<?> find(String hql, int maxRes, int first) throws Exception {
		return find(hql, maxRes, first, new Object[0]);
	}

	/**
	 * 从指定起始位置开始查询指定个数的结果集
	 * 
	 * @param hql
	 * @param maxRes
	 * @param first
	 * @return
	 * @throws Exception
	 */
	public List<?> find(final String hql, final int maxRes, final int first,
			final Object... pvalues) throws Exception {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = session.createQuery(hql);
						if (first >= 0)// 设置初始
							query.setFirstResult(first);
						if (maxRes >= 0)// 设置最大抓取
							query.setMaxResults(maxRes);
						// 填充参数
						if (pvalues != null && pvalues.length > 0) {
							for (int i = 0; i < pvalues.length; i++) {
								query.setParameter(i, pvalues[i]);
							}
						}

						return query.list();
					}
				});
	}

	/**
	 * 从指定起始位置开始查询指定个数的结果集
	 * 
	 * @param hql
	 * @param maxRes
	 * @param first
	 * @return
	 * @throws Exception
	 */
	public List<?> find(final String hql, final int maxRes, final int first,
			final String[] pnames, final Object[] pvalues) throws Exception {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = session.createQuery(hql);
						if (first >= 0)// 设置初始
							query.setFirstResult(first);
						if (maxRes >= 0)// 设置最大抓取
							query.setMaxResults(maxRes);
						// 填充参数
						if (pnames != null && pnames.length > 0) {
							for (int i = 0; i < pnames.length; i++) {
								query.setParameter(pnames[i], pvalues[i]);
							}
						}

						return query.list();
					}
				});
	}

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
			final Object[] pvalues, final int first, final int max) {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public List<?> doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.SQLQuery query = session
								.createSQLQuery(sql);
						if (first >= 0)// 设置初始
							query.setFirstResult(first);
						if (max >= 0)// 设置最大抓取
							query.setMaxResults(max);
						// 填充参数
						if (pnames != null && pnames.length > 0) {
							for (int i = 0; i < pnames.length; i++) {
								query.setParameter(pnames[i], pvalues[i]);
							}
						}

						return query.list();
					}
				});
	}

	/**
	 * 执行原生SQL
	 * 
	 * @param sql
	 * @param names
	 * @param pvalues
	 */
	public void executeUsingNativeSQL(final String sql, final String[] pnames,
			final Object[] pvalues) {
		super.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				org.hibernate.SQLQuery query = session.createSQLQuery(sql);

				// 填充参数
				if (pnames != null && pnames.length > 0) {
					for (int i = 0; i < pnames.length; i++) {
						query.setParameter(pnames[i], pvalues[i]);
					}
				}

				return query.executeUpdate();
			}
		});
	}

	/**
	 * 如果提供通用方法无法满足需求时，允许披露此数据库操作模板类给接口。
	 * 
	 * @return
	 */
	public HibernateTemplate usingHibernateTemplate() {
		return super.getHibernateTemplate();
	}
}
