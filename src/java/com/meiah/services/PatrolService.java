/** * A级 */
package com.meiah.services;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.meiah.core.orm.Page;
import com.meiah.entity.DataRecordOfString;
import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.pre.PreReport;
import com.meiah.entity.reportinfo.Finfo;
import com.meiah.entity.reportinfo.Hwinfo;
import com.meiah.entity.reportinfo.Winfo;
import com.meiah.entity.reportinfo.Yinfo;
import com.meiah.entity.sys.Log;
import com.meiah.entity.sys.LoginMap;
import com.meiah.entity.sys.SysCode;
import com.meiah.entity.sys.SysGroup;
import com.meiah.entity.sys.SysNotice;
import com.meiah.entity.sys.SysResource;
import com.meiah.entity.sys.SysRole;
import com.meiah.entity.sys.SysUser;
import com.meiah.entity.task.TaskNotice;
import com.meiah.entity.task.WbPatrolLog;
@WebService(targetNamespace="http://tempuri.org/")
public interface PatrolService {
	public String testServer(@WebParam(name="content" )String content);
	
	/**
	 * 添加任务提醒,userid、tuserid、content都不能为空
	 * @param taskNotice
	 * @param comeFrom 系统来源
	 * @return 是否成功
	 */
	public String addTaskNotice(@WebParam(name="taskNotice" )TaskNotice taskNotice,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	/**
	 * 根据用户ID获取用户收藏的标签
	 * @param userid 用户标签
	 * @param ouserid 哪个用户查询(操作用户ID)
	 * @param comeFrom 系统来源
	 * @return 是否成功
	 */
	public DataRecordOfString favoriteLabel(@WebParam(name="userid" )Long userid,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 是否收藏成功
	 */
	public String addFavorite(@WebParam(name="favorite" )Favorite favorite ,@WebParam(name="userid" ) Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 成功则返回收藏的Id
	 */
	public Long addFavoriteReturn(@WebParam(name="favorite" )Favorite favorite ,@WebParam(name="userid" ) Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 取消收藏
	 * @param fid 要取消的收藏id
	 * @param ouserid 收藏的用户
	 * @return 成功则返回收藏的Id
	 */
	public String cancleFavorite(@WebParam(name="fid" )Long fid ,@WebParam(name="userid" ) Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 系统日志保存
	 * @param sysLog 系统日志
	 * @param ouserid 日志操作用户
	 * @param comeFrom 系统来源
	 * @return 是否添加成功
	 */
	public String addLog(@WebParam(name="log" )Log log,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 根据用户登录名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public SysUser getUserByLoginName(@WebParam(name="loginName" )String loginName,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 根据用户姓名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param name 用户的姓名
	 * @param flag 是否启用模糊匹配，空为不启用，其他为启用
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public List<SysUser> getUserByName(@WebParam(name="name" )String name,@WebParam(name="flag")String flag, @WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 根据用户姓名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param groupId 用户的组id
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public List<SysUser> getUserByGroupId(@WebParam(name="groupId" )Long groupId , @WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 根据用户ID获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param uid用户ID
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public SysUser getUserByLoginUserId(@WebParam(name="uid" )Long uid,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	/**
	 * 根据浏览器序列号获取在线用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param objid 浏览器序列号
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public SysUser getLoginMapOnLine(@WebParam(name="objid" )String objid,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 根据浏览器序列号获取LoginMap，不做任何逻辑处理，只取符合条件的loginMap
	 * @param objid 浏览器序列号
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public LoginMap getLoginMapByObjid(@WebParam(name="objid" )String objid,@WebParam(name="comeFrom" )String comeFrom)throws Exception;
	
	/**
	 * 获取该用户的子用户
	 * @param userid
	 * @param comeFrom
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getChildUsers(@WebParam(name="userid" )Long userid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 传入name!!!password!!!number的base64，获取用户，包括逻辑
	 * @param npn
	 * @param comeFrom
	 * @return
	 * @throws Exception
	 */
	public SysUser getUserNP(@WebParam(name="npn" )String npn,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 获取所有用户
	 * @param comeFrom 系统来源
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getAllSysUser(@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 专项信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @return 是否成功
	 * @throws Exception
	 */
	public String reportHwinfo(@WebParam(name="hwinfo" )Hwinfo hwinfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="cutImgFlag" )Integer cutImgFlag,@WebParam(name="attfileFlag" )Integer attfileFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 专项信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @return 入库ID
	 * @throws Exception
	 */
	public Long reportHwinfoReturn(@WebParam(name="hwinfo" )Hwinfo hwinfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="cutImgFlag" )Integer cutImgFlag,@WebParam(name="attfileFlag" )Integer attfileFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 互联网信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public String reportWinfo(@WebParam(name="winfo" )Winfo winfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileFlag" )Integer attfileFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 互联网信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @return 入库ID
	 * @throws Exception
	 */
	public Long reportWinfoReturn(@WebParam(name="winfo" )Winfo winfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileFlag" )Integer attfileFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public String reportPre(@WebParam(name="preReport" )PreReport preReport ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom)throws Exception;
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public Long reportPreReturn(@WebParam(name="preReport" )PreReport preReport ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom)throws Exception;
	
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public String reportPreWeChat(@WebParam(name="preReport" )PreReport preReport ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom,String wechat)throws Exception;
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public Long reportPreReturnWeChat(@WebParam(name="preReport" )PreReport preReport ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom,String wechat)throws Exception;
	
	
	
	/**
	 * 判断URL是否在专项中已经存在
	 * @param url url
	 * @param ouserid 操作用户ID
	 * @param comeFrom 系统来源
	 * @return 返回结果，1存在，0不存在
	 * @throws Exception
	 */
	public String checkURL(@WebParam(name="url")String url ,@WebParam(name="ouserid")Long ouserid, @WebParam(name="comeFrom")String comeFrom) throws Exception;
	
	/**
	 * 判断URL是否在上报中已经存在
	 * @param url url
	 * @param ouserid 操作用户ID
	 * @param comeFrom 系统来源
	 * @param 如果是修改上报信息，传入该条上报信息的ID，如果是新增上报，preId为空
	 * @return 返回结果，1存在，0不存在
	 * @throws Exception
	 */
	public String checkPreURL(@WebParam(name="url")String url ,@WebParam(name="ouserid")Long ouserid, @WebParam(name="comeFrom")String comeFrom,@WebParam(name="preId")Long preId) throws Exception;
	
	/**
	 * 根据URL截图，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 如果截图成功，返回字符串数组，["图片名称","图片查看地址","图片入库的ID"]
	 * @throws Exception
	 */
	public String[] cutImg(@WebParam(name="url" )String url,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	/**
	 * 根据URL获取htm，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 如果htm成功，返回字符串数组，["htm名称","htm查看地址","htm入库的ID"]
	 * @throws Exception
	 */
	public String[] htm(@WebParam(name="url" )String url,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	/**
	 * 根据URL获取mht，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 如果mht成功，返回字符串数组，["mht名称","mht查看地址","mht入库的ID"]
	 * @throws Exception
	 */
	public String[] mht(@WebParam(name="url" )String url,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 通过codeType获取状态为1的编码
	 * @param codeType 编码类别,如果编码为空,则返回所有的syscode
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return syscode集合
	 * @throws Exception
	 */
	public List<SysCode> getSysCode(@WebParam(name="codeType" )String codeType,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 获取资源列表
	 * @param resources  资源类,支持查询字段有:comFrom、enable、level、name（前后%name%）、typeId
	 * @param ouserid 系统用户ID
	 * @param comeFrom 系统来源
	 * @param flag flag为1，代表根据权限查询，为空或者其他，不按权限
	 * @return
	 * @throws Exception
	 */
	public List<SysResource> getResources(@WebParam(name="resources" )SysResource resources,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom,String flag) throws Exception;
	
	/**
	 * 获取平台公告
	 * @param comeFrom 系统来源
	 * @return
	 */
	public Page<SysNotice> getSysNotice(@WebParam(name="sysNotice" )SysNotice sysNotice ,@WebParam(name="page" )Page<SysNotice> page,@WebParam(name="ouserid" )Long ouserid ,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 微博巡查日志
	 * @param wbPatrolLog 微博巡查日志
	 * @param ouserid 操作用户ID
	 * @param comeFrom 系统来源
	 * @return
	 */
	public String addWbPatrolLog(WbPatrolLog wbPatrolLog,@WebParam(name="ouserid" )Long ouserid ,@WebParam(name="comeFrom" )String comeFrom);
	
	/**
	 * 
	 * @param ids 角色ids串,可为空,如果为空,返回所有角色
	 * @param ouserid 操作用户ID,可为空
	 * @param  comeFrom 系统来源 
	 * @return 返回角色集合,如果不存在,角色集合为空
	 */
	public List<SysRole> getRoleList(@WebParam(name="ids")String ids,@WebParam(name="ouserid" )Long ouserid ,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param pwd 用户新密码（md5）
	 * @param ouserid 操作用户id
	 * @param comeFrom 系统来源
	 * @return 返回是否更改成功标志,success是成功
	 */
	public String alterpw(@WebParam(name="uid" )Long uid ,@WebParam(name="pwd" )String pwd,@WebParam(name="ouserid" )Long ouserid ,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 绑定微信号
	 * @param loginname 账号
	 * @param wechat 微信uin
	 * @param comeFrom 系统来源
	 * @return 返回是否更改成功标志,success是成功
	 */
	public String bindWeChat(@WebParam(name="loginname" )String loginname ,@WebParam(name="wechat" )String wechat,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 同步关键词,涉及到多个系统的同步关键词接口的调用，建议用线程池控制，当连接第三方系统接口失败，记录失败的系统
	 * @param keysJSON 同步的JSON串，其中格式为:
	 * 	{
	 * 		type:"add",        ---其中type为操作类型，add为新增、del为删除、edit为修改
	 * 		total:18,		   ---其中total为本次操作受影响的关键词总数
	 * 		userid:["1","2","3"],	   ---userid为共享对象的用户id或者其他的用户唯一标识，不限制类型
	 * 		keys:[			   		   ---keys为操作的关键词数字
	 * 				{"key":"暴力","type":"涉及警察","uuid":"121xc234"}, --key为关键词 ，type为关键词类别 ，uuid为关键词的唯一标识
	 * 				{"key":"反恐","type":"涉及警察","uuid":"121xc235"}
	 * 			 ]
	 *  }
	 * @param comeFrom 系统来源
	 * @return 返回是否更改成功标志,success是成功
	 */
	public String syncKeys(String keysJSON,Long ouserid ,String comeFrom)throws Exception;
	
	
	/**
	 * 
	 * @param parId 父节点ID
	 * @param ouserid 操作用户ID,可为空
	 * @param  comeFrom 系统来源 
	 * @return 返回用户组集合
	 */
	public List<SysGroup> getSysGroupList(@WebParam(name="parId")Long parId,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * 
	 * @param code 父节点ID
	 * @param ouserid 操作用户ID,可为空
	 * @param cflag 是否包含非直接子节点，空为只取直接子节点，非空取所有
	 * @param sflag	是否获取包含code的节点，空为不获取，非空获取
	 * @param  comeFrom 系统来源 
	 * @return 返回用户组集合
	 */
	public List<SysGroup> getSysGroupListByCode(@WebParam(name="code")String code,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="cflag" )String cflag,@WebParam(name="sflag" )String sflag,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 四类信息报送
	 * @param Finfo 四类信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @return 入库ID
	 * @throws Exception
	 */
	public Long reportFinfoReturn(@WebParam(name="finfo" )Finfo finfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="cutImgFlag" )Integer cutImgFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	
	/**
	 * 专项信息报送
	 * @param Yinfo YQ信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @return 入库ID
	 * @throws Exception
	 */
	public Long reportYinfoReturn(@WebParam(name="yinfo" )Yinfo yinfo,@WebParam(name="username" )String username ,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="cutImgFlag" )Integer cutImgFlag,@WebParam(name="attfileIds" )String[] attfileIds,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * @param Yinfo排重对象
	 * @param ouserid操作用户
	 * @param comeFrom系统来源
	 * @return
	 * @throws Exception
	 */
	public String checkYinfoUniqueness(@WebParam(name="yinfo" )Yinfo yinfo,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * @param Finfo 排重对象
	 * @param ouserid 操作用户
	 * @param comeFrom 系统来源
	 * @return
	 * @throws Exception
	 */
	public String checkFinfoUniqueness(@WebParam(name="finfo" )Finfo finfo,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * @param Hwinfo排重对象
	 * @param ouserid操作用户
	 * @param comeFrom系统来源
	 * @return
	 * @throws Exception
	 */
	public String checkHwinfoUniqueness(@WebParam(name="hwinfo" )Hwinfo hwinfo,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
	
	/**
	 * @param Winfo排重对象
	 * @param ouserid操作用户
	 * @param comeFrom系统来源
	 * @return
	 * @throws Exception
	 */
	public String checkWinfoUniqueness(@WebParam(name="winfo" )Winfo winfo,@WebParam(name="ouserid" )Long ouserid,@WebParam(name="comeFrom" )String comeFrom) throws Exception;
}
