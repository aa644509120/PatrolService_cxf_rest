/** * A级 */
package com.meiah.manager.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.sys.SysRole;

@Service
@Transactional
public class SysRoleManager extends AbstractCrudManager<SysRole> {
	@SuppressWarnings("unchecked")
	public List<SysRole> getRoleByRids(String rids){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("rids", rids);
		List<SysRole> sysRoleList = this.dao.findByNamedQuery("SysRole.getRoleByRids", map);
		return sysRoleList;
	}
}
