/** * A级 */
package com.meiah.manager.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.entity.sys.LoginMap;
import com.meiah.core.service.AbstractCrudManager;

@Service
@Transactional
public class LoginMapManager extends AbstractCrudManager<LoginMap> {
	
}
