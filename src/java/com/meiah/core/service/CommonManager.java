/** * A级 */
package com.meiah.core.service;

/**
 * @genby <a href="mailto:zengxianbin@gmail.com">Zeng Xianbin</a>
 */

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zengxb
 */

@SuppressWarnings("unchecked")
@Service
@Transactional
public class CommonManager extends AbstractCrudManager{
	public List find(String hql, Object...values ){
		return dao.find(hql, values);
	}
	
	public int executeByNamedQuery(String queryName,Object... values){
		return dao.executeByNamedQuery(queryName, values);
	}
	
	public int executeByNamedQuery(String queryName, Map<String,Object> param){
		return dao.executeByNamedQuery(queryName, param);
	}
	
	@Transactional(readOnly = true)
	public List findByNamedQuery(String queryName, Object... values){
		return dao.findByNamedQuery(queryName, values);
	}
	
	public void evict(Object entity){
		dao.getSession().evict(entity);
	}
	
	public void merge(Object entity){
		dao.getSession().merge(entity);
	}
}
