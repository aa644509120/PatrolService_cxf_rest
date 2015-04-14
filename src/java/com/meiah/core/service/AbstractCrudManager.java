/** * A级 */
package com.meiah.core.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.annotation.Cacheable;
import com.meiah.core.orm.Page;
import com.meiah.core.orm.hibernate.SimpleHibernateTemplate;
import com.meiah.core.util.GenericsUtils;
import com.meiah.core.util.Validator;

/**
 * 整个T模块内的业务逻辑Facade.
 * DAO均由SimpleHibernateTemplate指定泛型生成.
 * 
 * @author zengxb<br>
 * @email: zengxianbin@gmail.com<br>
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractCrudManager<T> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected SimpleHibernateTemplate<T, Serializable> dao;
	protected Class entityClass;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<T, Serializable>(sessionFactory, GenericsUtils.getSuperClassGenricType(getClass()));
		if(dao == null)
			logger.debug("dao is null");
		boolean flag = getEntityClass().isAnnotationPresent(Cacheable.class);
		dao.setCacheable(flag);
	}
	
	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		}
		return entityClass;
	}
	
	@Transactional(readOnly = true)
	public T get(Serializable id) {
		return dao.get(id);
	}
	
	@Transactional(readOnly = true)
	public T load(Serializable id) {
		return dao.load(id);
	}
	
	@Transactional(readOnly = true)
	public List<T>getAll(){
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<T> getAll(Page<T> page) {
		return dao.findAll(page);
	}

	@Transactional(readOnly = true)
	public List<T> getAll(Criterion... criterion) {
		return dao.findByCriteria(criterion);
	}
	

	@Transactional(readOnly = true)
	public List<T>getAll(Criterion[] criterion, Order...order){
		return dao.findByCriteria(criterion, order);
	}
	
	@Transactional(readOnly = true)
	public Page<T> getAll(Page<T> page, Criterion... criterion) {
		if(criterion == null)
			return dao.findAll(page);
		return dao.findByCriteria(page, criterion);
	}
	
	@Transactional(readOnly = true)
	public Page<T> getAll(Page<T> page, Criterion[] criterion, Order...order) {
		if(criterion == null)
			return dao.findAll(page, order);
		return dao.findByCriteria(page, criterion, order);
	}
	/**
	 * 添加对象，返回对象主键
	 * @param entity
	 * @return
	 * @author hepl
	 * 2010.01.29 16:17
	 */
	public Serializable add(T entity) {
		return dao.add(entity);
	}
	public void save(T entity) {
		dao.save(entity);
	}
	
	public void saveByNewSession(T entity) {
		dao.saveByNewSession(entity);
	}

	public void delete(Serializable id) {
		if(Validator.isNotNull(id)){
			T entity = dao.get(id);
			delete(entity);
		}
	}
	
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	public List<T> find(String hql,Object... values) {
		return dao.find(hql, values);
	}
	
	public List<T> find(String hql, int size, Object... values) {
		return dao.find(hql, size, values);
	}
	
	public List<T> findByProperty(String propertyName, Object value){
		return dao.findByProperty(propertyName, value);
	}
	
	public T findByPropertySingle(String propertyName,Object value){
		List<T> temp = this.findByProperty(propertyName, value);
		if(Validator.isNotNull(temp)) return temp.get(0);
		return null;
	}
	
	public List<T> findByProperty(String propertyName, Object value, Order... orders){
		return dao.findByProperty(propertyName, value,orders);
	}
	
	public List<T> findByCriteria(Criterion... criterion){
		return dao.findByCriteria(criterion);
	}
	
	public void evict(T entity){
		dao.getSession().evict(entity);
	}
	
	public void merge(T entity){
		dao.getSession().merge(entity);
	}
	
	/**
	 * 批量插入数据
	 * @param list
	 */
	public void batchInsert(List<T> list){
		dao.batchInsert(list);
	}
	
	/**
	 * 批量插入数据
	 * @param Collection
	 * @author sufq 
	 */
	public void batchInsert(Collection<T> collection){
		dao.batchInsert(collection);
	}
	
	/**
	 * 
	 * @param ids多个ID组成的字符串
	 * luwb修改：考虑到表的主键类型和主键名称不固定，利用反射机制和组装hql语句来避免不通用的情况
	 * 时间：2012-04-20
	 */
	 public void deleteByIds(String ids){
		 Class clazz = getGenericType(0);
		 Field[] fs = clazz.getDeclaredFields();
		 String name = fs[0].getName();
		 //如果为static final long类型则往下取
		 if (fs[0].toGenericString().contains("static final long")) {
			 name = fs[1].getName();
		 }
		 String hql = "delete from "+clazz.getName()+" where "+name+" in ("+ids+")";
		 dao.batchDelete(hql);
	 }
	 
	 /**
	  * 
	  * @param ids多个ID组成的字符串
	  * luwb修改：考虑到表的主键类型和主键名称不固定，利用反射机制和组装hql语句来避免不通用的情况
	  * 时间：2012-04-23
	  * @author huanglb
	  * isShare 0-不共享，1-共享
	  */
	 public void shareByIds(String ids, Integer isShare){
		 Class clazz = getGenericType(0);
		 Field[] fs = clazz.getDeclaredFields();
		 String name = fs[0].getName();
		 String hql = "update "+clazz.getName()+ " set isshare=" + isShare + " where "+name+" in ("+ids+")";
		 dao.batchDelete(hql);
	 }
	 
	 /**
		 * 通过实体属性删除数据
		 * @param list
		 */
		public void deleteByProperty(String property,Long propertyValue){
			 Class clazz = getGenericType(0);
			 String hql = "delete from "+clazz.getName()+" where "+property+" = "+propertyValue;
			 dao.executeHql(hql);
		}
		
		public void execute(String hql) {
			dao.executeHql(hql);
		}
	/**
	 * 获取父类转过来的泛型的具体类;
	 * @param index
	 * @return
	 */
	 public Class getGenericType(int index) {
		  Type genType = getClass().getGenericSuperclass();
		  if (!(genType instanceof ParameterizedType)) {
		   return Object.class;
		  }
		  Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		  if (index >= params.length || index < 0) {
		   throw new RuntimeException("Index outof bounds");
		  }
		  if (!(params[index] instanceof Class)) {
		   return Object.class;
		  }
		  return (Class) params[index];
		 }
	 
}
