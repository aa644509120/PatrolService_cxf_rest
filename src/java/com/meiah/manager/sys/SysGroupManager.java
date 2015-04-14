/** * A级 */
package com.meiah.manager.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.sys.SysGroup;

@Service
@Transactional
public class SysGroupManager extends AbstractCrudManager<SysGroup> {
}
