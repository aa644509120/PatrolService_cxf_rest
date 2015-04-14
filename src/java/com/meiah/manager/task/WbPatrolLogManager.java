/** * A级 */
package com.meiah.manager.task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.task.WbPatrolLog;
@Service
@Transactional
public class WbPatrolLogManager extends AbstractCrudManager<WbPatrolLog>{

}
