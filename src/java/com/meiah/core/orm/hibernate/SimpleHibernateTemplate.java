/** * A级 */
package com.meiah.core.orm.hibernate;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.util.Assert;

import com.meiah.core.orm.Page;
import com.meiah.core.util.BeanUtils;

/**
 * Hibernate的范型基类.
 * 
 * 可以在service类中直接创建使用.也可以继承出DAO子类,在多个Service类中共享DAO操作.
 * 通过Hibernate的sessionFactory.getCurrentSession()获得session.
 *
 * @param <T> DAO操作的对象类
 * @param <PK> 主键类型
 * 
 * @author zengxb
 * 
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateTemplate<T, PK extends Serializable> {

	protected Logger logger = Logger.getLogger(getClass());

	protected SessionFactory sessionFactory;
	
	protected Class<T> entityClass;

	protected boolean cacheable = false;
	
	public void setCacheable(boolean cacheable){
		this.cacheable = cacheable;
	}
	
	public boolean getCacheable(){
		return this.cacheable;
	}
	
	public SimpleHibernateTemplate(SessionFactory sessionFactory, Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session openSession() {
		return sessionFactory.openSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public Serializable add(T entity) {
		Assert.notNull(entity);
		return getSession().save(entity);
	}
	public void save(T entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
	}
	
	public void saveByNewSession(T entity) {
		Assert.notNull(entity);
		Session session = this.openSession();
		try{
			session.saveOrUpdate(entity);
		}finally{
			session.close();
		}
	}

	public void delete(T entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id);
		getSession().delete(get(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}
	
	public List<T> findAll(Order... orders) {
		Criteria criteria = createCriteria();
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}
	
	public Page<T> findAll(Page<T> page) {
		return findByCriteria(page);
	}

	public Page<T> findAll(Page<T> page, Order... orders) {
		return findByCriteria(page, null,orders);
	}
	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		return (T) getSession().load(entityClass, id);
	}
	
	/**
	 * 按id获取对象.用get的方法获取
	 */
	public T load(final PK id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 */
	public List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql语句
	 * @param values 数量可变的参数
	 * @param size 要取的数量
	 */
	public List find(String hql, int size, Object... values) {
		return createQuery(hql, values).setFirstResult(0).setMaxResults(size).list();
	}
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数.包括pageSize 和firstResult.
	 * @param hql hql语句.
	 * @param values 数量可变的参数.
	 * 
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	public Page<T> find(Page<T> page, String hql, Object... values) {
		Assert.notNull(page);
		Query q = createQuery(hql, values);
		
		if (page.isFirstSetted()) {
			q.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			q.setMaxResults(page.getPageSize());
		}
		page.setResult(q.list());
		return page;
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数.包括pageSize 和firstResult.
	 * @param hql hql语句.
	 * @param values 数量可变的参数.
	 * 
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	public Page<T> find(Page<T> page, String hql, String countHql, Object... values) {
		Assert.notNull(page);
		Assert.notNull(page);
		if (page.isAutoCount()) {
			Query countQuery = createQuery(countHql, values);;
			List countList = countQuery.list();
			page.setTotalCount(((Long) countList.get(0)).intValue());
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
		
		Query q = createQuery(hql, values);
		if (page.isFirstSetted()) {
			q.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			q.setMaxResults(page.getPageSize());
		}
		page.setResult(q.list());
		return page;
	}
	
	
	public Page<T> findByQueryname(Page<T> page, String queryname, Map<String,Object> param) {
		Assert.notNull(page);
		Query query = getNamedQuery(queryname, param);
		String[] nameParams = query.getNamedParameters();
		for (String nameParam : nameParams) {
			query.setParameter(nameParam, param.get(nameParam));
		}
		if (page.isFirstSetted()) {
			query.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			query.setMaxResults(page.getPageSize());
		}
		page.setResult(query.list());
		return page;
	}
	
	
	
	public Page<T> findByQueryname(Page<T> page, String queryname, String countqueryname, Object... values) {
		Assert.notNull(page);
		if (page.isAutoCount()) {
			Query countQuery = getNamedQuery(countqueryname);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					countQuery.setParameter(i, values[i]);
				}
			}
			List countList = countQuery.list();
			page.setTotalCount((Integer) countList.get(0));
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
		
		Query query = getNamedQuery(queryname);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		if (page.isFirstSetted()) {
			query.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			query.setMaxResults(page.getPageSize());
		}
		page.setResult(query.list());
		return page;
	}
	
	
	public Page<T> findByQueryname(Page<T> page, String queryname, String countqueryname, Map<String,Object> param) {
		Assert.notNull(page);
		if (page.isAutoCount()) {
			Query countQuery = getNamedQuery(countqueryname, param);
			String[] nameParams = countQuery.getNamedParameters();
			for (String nameParam : nameParams) {
				countQuery.setParameter(nameParam, param.get(nameParam));
			}
			List countList = countQuery.list();
			page.setTotalCount((Integer) countList.get(0));
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
		
		Query query = getNamedQuery(queryname, param);
		String[] nameParams = query.getNamedParameters();
		for (String nameParam : nameParams) {
			query.setParameter(nameParam, param.get(nameParam));
		}
		
		if (page.isFirstSetted()) {
			query.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			query.setMaxResults(page.getPageSize());
		}
		page.setResult(query.list());
		return page;
	}

	public Page<T> find(Page<T> page, Query query) {
		Assert.notNull(page);
		if (page.isFirstSetted()) {
			query.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			query.setMaxResults(page.getPageSize());
		}
		page.setResult(query.list());
		return page;
	}

	
	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询Intger类形结果. 
	 */
	public Integer findInt(String hql, Object... values) {
		return (Integer) findUnique(hql, values);
	}

	/**
	 * 按HQL查询Long类型结果. 
	 */
	public Long findLong(String hql, Object... values) {
		return (Long) findUnique(hql, values);
	}

	/**
	 * 按Criterion查询对象列表.
	 * @param criterion 数量可变的Criterion.
	 */
	public List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * 按Criterion和Order查询对象列表,
	 * @param criterion 数量可变的Criterion.
	 */
	public List<T> findByCriteria(Criterion[] criterion, Order... orders) {
		Criteria criteria = createCriteria(criterion);
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}
	
	/**
	 * 按Criterion分页查询.
	 * @param page 分页参数.包括pageSize、firstResult、orderBy、asc、autoCount.
	 *             其中firstResult可直接指,也可以指定pageNo.
	 *             autoCount指定是否动自获取总记录结果数.
	 * @param criterion 数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page<T> findByCriteria(Page page, Criterion... criterion) {
		Assert.notNull(page);

		Criteria c = createCriteria(criterion);

		if (page.isAutoCount()) {
			page.setTotalCount(countQueryResult(page, c));
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
        if( page.getTotalCount() % page.getPageSize()==0){//最后一页只有一条数据删除时判断，否则有问题 2013-07-20xiexb
	       	 if (page.getPageNo() > page.getTotalCount() / page.getPageSize()) {
	                page.setPageNo(page.getTotalCount() / page.getPageSize());
	         }
        }
		if (page.isFirstSetted()) {
			c.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			c.setMaxResults(page.getPageSize());
		}

		if (page.isOrderBySetted()) {
			if (page.isAsc()) {
				c.addOrder(Order.asc(page.getOrderBy()));
			} else {
				c.addOrder(Order.desc(page.getOrderBy()));
			}
		}
		if(page.getPageNo()>page.getTotalCount()/page.getPageSize()+1){
			page.setPageNo(1);
		}	
		page.setResult(c.list());
		return page;
	}

	public Page<T> findByCriteria(Page page, Criterion[] criterion, Order... orders) {
		Assert.notNull(page);

		Criteria c = createCriteria(criterion);

		if (page.isAutoCount()) {
			page.setTotalCount(countQueryResult(page, c));
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
	    if( page.getTotalCount() % page.getPageSize()==0){//最后一页只有一条数据删除时判断
	       	 if (page.getPageNo() > page.getTotalCount() / page.getPageSize()) {
	                page.setPageNo(page.getTotalCount() / page.getPageSize());
	         }
        }
		if (page.isFirstSetted()) {
			c.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			c.setMaxResults(page.getPageSize());
		}

		for (Order order : orders) {
			if(order.toString().indexOf(page.getOrderBy())==-1)
				c.addOrder(order);
		}
		if (page.isOrderBySetted()) {
			if (page.isAsc()) {
				c.addOrder(Order.asc(page.getOrderBy()));
			} else {
				c.addOrder(Order.desc(page.getOrderBy()));
			}
		}
		
		page.setResult(c.list());
		return page;
	}
	
	/**
	 * 按属性查找对象列表.
	 */
	public List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(Restrictions.eq(propertyName, value)).list();
	}
	
	/**
	 * 按照属性名称来查找按顺序排列的列表
	 * @param propertyName 属性名称
	 * @param value 值
	 * @param orders 排序
	 * @return
	 * @author "zhangshaofeng"  
	 * @time Jun 14, 2012 3:47:49 PM
	 */
	public List<T> findByProperty(String propertyName, Object value, Order... orders) {
		Assert.hasText(propertyName);
		Criteria criteria = createCriteria(Restrictions.eq(propertyName, value));
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	/**
	 * 按属性查找唯一对象.
	 */
	public T findUniqueByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	public Query createSQLQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	
	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if(criterions != null)
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		criteria.setCacheable(this.getCacheable());
		return criteria;
	}

	/**
	 * 判断对象的属性在数据库内是否唯一.
	 * 
	 * 在修改对象的情景中,如果属性新修改的值 (value)等于属性原仩(orgValue)则不作比�?.
	 * 传回orgValue的设计侧重于从页面上发出Ajax判断请求的场�?.
	 * 否则�?要SS2里那种以对象ID作为�?3个参数的isUnique函数.
	 */
	public boolean isUnique(String propertyName, Object value, Object orgValue) {
		if (value == null || value.equals(orgValue))
			return true;

		Object object = findUniqueByProperty(propertyName, value);
		if (object == null)
			return true;
		else
			return false;
	}

	/**
	 * 通过count查询获得本次查询�?能获得的对象总数.
	 * @return page对象中的totalCount属�?�将赋�??.
	 */
	protected int countQueryResult(Page<T> page, Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
		// 先把Projection、OrderBy、ResultTransformer取出�?,清空三�?�后再执行Count操作
		Projection projection = impl.getProjection();

		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
		if (totalCount < 1)
			return 0;

		// 将之前的Projection和OrderBy条件重新设回�?
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		if (transformer != null) {
			c.setResultTransformer(transformer);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		return totalCount;
	}

	public int executeByNamedQuery(String queryName,Object... values){
		Query query = getNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();
	}
	
	public List findByNamedQuery(String queryName, Object... values){
		Query query = getNamedQuery(queryName);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}
	
	 public int executeByNamedQuery(String queryName, Map<String, Object> param) {
	        Query query = getNamedQuery(queryName, param);
	        String[] nameParams = query.getNamedParameters();
	        for (String nameParam : nameParams) {
	            if(param.get(nameParam) instanceof Collection){//参数是个集合
	                query.setParameterList(nameParam, (Collection)param.get(nameParam)); 
	            }else{
	                query.setParameter(nameParam, param.get(nameParam));
	            }
	        }
	        return query.executeUpdate();
	 }

    public List findByNamedQuery(String queryName, Map<String, Object> param) {
        Query query = getNamedQuery(queryName, param);
        String[] nameParams = query.getNamedParameters();
        for (String nameParam : nameParams) {
            if(param.get(nameParam) instanceof Collection){//参数是个集合
                query.setParameterList(nameParam, (Collection)param.get(nameParam)); 
            }else{
                query.setParameter(nameParam, param.get(nameParam));
            }
        }
        return query.list();
    }
	
	public Query getNamedQuery(String queryName){
		Query query = getSession().getNamedQuery(queryName);
		return query;
	}
	
	public Query getNamedQuery(String queryName, Map<String,Object> param){
		Query query = getSession().getNamedQuery(queryName);
		Query rsQuery = null; 
		try {
			Velocity.init();
			/* 初始化运行时引擎， 默认初始化 */
			VelocityContext context = new VelocityContext();
			Set<String> keys = param.keySet();
			for(Iterator<String> it = keys.iterator(); it.hasNext();){
				String key = it.next();
				context.put(key, param.get(key));
			}
			StringWriter sql = new StringWriter(); 
			Velocity.evaluate(context, sql, null, query.getQueryString());
			rsQuery = getSession().createSQLQuery(sql.toString());
			BeanUtils.forceSetProperty(rsQuery, "queryReturns", BeanUtils.forceGetProperty(query,"queryReturns"));
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return rsQuery;
	}
	
	public Page<T> getListByQueryString(Page<T> page,String queryString,String countqueryname,T t,Object... values){
		Assert.notNull(page);
		if (page.isAutoCount()) {
			Query countQuery = getNamedQuery(countqueryname);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					countQuery.setParameter(i, values[i]);
				}
			}
			List countList = countQuery.list();
			page.setTotalCount((Integer) countList.get(0));
		}
		
		if(page.getTotalCount()==-1 || page.getTotalCount() ==0)
			return page;
		
		Query query = getSession().createSQLQuery(queryString).addEntity(t.getClass());
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		if (page.isFirstSetted()) {
			query.setFirstResult(page.getFirst());
		}
		if (page.isPageSizeSetted()) {
			query.setMaxResults(page.getPageSize());
		}
		page.setResult(query.list());
		return page;
	}
	
	/**
	 * 批量插入数据
	 * @param list
	 */
	public void batchInsert(List<T> list){
		//打开Session
	   Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction tx = session.beginTransaction();
		try{
	    int listSize=list.size();
	    for (int i = 0; i < listSize; i++) {
	    	try {
		    	session.save(list.get(i));
		    	//每当累加器是25的倍数时，将Session中的数据刷入数据库，并清空Session缓存
		    	if (i % 25 == 0)
		        {
		            session.flush();
		            session.clear();
		        }
	    	} catch (Exception e) {
				logger.error("批量保存数据出错", e);
			}
		}
	    //提交事务
	    tx.commit();
		} catch (Exception e) {  
            session.getTransaction().rollback(); // 出错将回滚事物  
        } finally {  
		    //关闭事务
		    session.close();
        }
	}
	
	/** 批量插入数据
	 *  @param collection
	 *  @author sufq
	 */
	public void batchInsert(Collection<T> collection) {
		//打开Session
		Session session = sessionFactory.openSession();
		//开始事务
		Transaction tx = session.beginTransaction();
			try{
			    int i=1;
			    for (T t : collection) {
			    	try {
				    	session.save(t);
				    	//每当累加器是25的倍数时，将Session中的数据刷入数据库，并清空Session缓存
				    	if (i++ % 25 == 0) {
				            session.flush();
				            session.clear();
				        }
			    	} catch (Exception e) {
						logger.error("批量保存数据出错", e);
					}
				}
			    //提交事务
			    tx.commit();
			} catch (Exception e) {  
	            session.getTransaction().rollback(); // 出错将回滚事物  
	        } finally {  
			    //关闭事务
			    session.close();
	        }
	}
	
	/**
	 * 批量删除数据
	 * @param hql
	 */
	public void batchDelete(String hql) {
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	/**
	 * 执行hql语句
	 * @param hql
	 */
	public void executeHql(String hql) {
		Query query=this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	   /**
     * 执行sql语句获取该sql返回的结果集行数
     * 
     * @param sql
     * @param param 参数列表
     * @return int 个数
     * @throws
     * @author Lin Chenglin 2012-10-19
     */
    public int getTotalCountBySql(String sql, Map<String, Object> param) {
        sql = "select count(*) from (" + sql + ") as A";
        Query query = this.createSQLQuery(sql, param);

        List list = query.list();
        if (list != null && list.size() > 0) {
            return Integer.parseInt(list.get(0).toString());
        } else {
            return 0;
        }
    }

    /**
     * 执行sql语句获取分页数据
     * 
     * @param page
     * @param queryName 在xml中配置的sql名称
     * @param param 参数列表
     * @return Page<Map<String,Object>>
     * @throws
     * @author Lin Chenglin 2012-10-19
     */
    public Page<Map<String, Object>> findBySql(Page<Map<String, Object>> page, String queryName,
            Map<String, Object> param) {

        String sql = getNamedQuery(queryName, param).getQueryString();
        if (page.isAutoCount()) {// 自动计算总记录数
            page.setTotalCount(this.getTotalCountBySql(sql, param));
        }

        if (page.getTotalCount() == -1 || page.getTotalCount() == 0) {// 没有数据
            page.setResult(new ArrayList<Map<String, Object>>());
            return page;
        }
        if( page.getTotalCount() % page.getPageSize()==0){//最后一页只有一条数据删除时判断，否则有问题 2013-07-20xiexb
	       	 if (page.getPageNo() > page.getTotalCount() / page.getPageSize()) {
	                page.setPageNo(page.getTotalCount() / page.getPageSize());
	         }
        }
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(" SELECT TOP " + page.getPageSize() + " * FROM (");
        sqlBuffer.append("   SELECT ROW_NUMBER() OVER (ORDER BY " + page.getOrderBy() + ") AS rowNumber,* FROM");
        sqlBuffer.append("   (");
        sqlBuffer.append(sql);
        sqlBuffer.append("   )A");
        sqlBuffer.append(" ) B");
        sqlBuffer.append(" WHERE RowNumber > " + page.getPageSize() + "*(" + page.getPageNo() + "-1)");
        sqlBuffer.append(" ORDER BY " + page.getOrderBy());

        Query query = this.createSQLQuery(sqlBuffer.toString(), param).setResultTransformer(
                Transformers.ALIAS_TO_ENTITY_MAP); // 建立查询对象

        if (page.getPageNo() > page.getTotalCount() / page.getPageSize() + 1) {// 设置页数
            page.setPageNo(1);
        }

        page.setResult(query.list());// 设置结果集

        return page;
    }

    /**
     * 查询获取结果集
     * 
     * @param queryName sql名称
     * @param param 参数列表
     * @return List<Map<String,Object>>
     * @throws
     * @author Lin Chenglin 2012-10-22
     */
    public List<Map<String, Object>> findListByNamedQuery(String queryName, Map<String, Object> param) {
        Query query = createQuery(queryName, param);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    /**
     * 查询获取单结果，类型为Map<String,Object>
     * 
     * @param queryName sql名称
     * @param param 参数列表
     * @return Map<String,Object>
     * @throws
     * @author Lin Chenglin 2012-10-30
     */
    public Map<String, Object> findMapByNamedQuery(String queryName, Map<String, Object> param) {
        Query query = this.createQuery(queryName, param);
        return (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    /**
     * 根据sql句柄以及参数，构建Query对象
     * 
     * @param queryName
     * @param param
     * @return Query
     * @throws
     * @author Lin Chenglin 2012-11-3
     */
    public Query createQuery(String queryName, Map<String, Object> param) {
        Query query = this.getNamedQuery(queryName, param);
        String[] nameParams = query.getNamedParameters();
        for (String nameParam : nameParams) {
            if(param.get(nameParam) instanceof Collection){//参数是个集合
                query.setParameterList(nameParam, (Collection)param.get(nameParam)); 
            }else{
                query.setParameter(nameParam, param.get(nameParam));
            }
        }
        return query;
    }

    /**
     * 根据sql及参数，创建Query对象
     * 
     * @param queryString
     * @param param
     * @return Query
     * @throws
     * @author Lin Chenglin 2012-11-5
     */
    public Query createSQLQuery(String queryString, Map<String, Object> param) {
        Assert.hasText(queryString);
        Query query = getSession().createSQLQuery(queryString);
        if (param == null) {
            return query;
        }
        String[] nameParams = query.getNamedParameters();
        for (String nameParam : nameParams) {
            if(param.get(nameParam) instanceof Collection){//参数是个集合
                query.setParameterList(nameParam, (Collection)param.get(nameParam)); 
            }else{
                query.setParameter(nameParam, param.get(nameParam));
            }
        }
        return query;
    }
}
