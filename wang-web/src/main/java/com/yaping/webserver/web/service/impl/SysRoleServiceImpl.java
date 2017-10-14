package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.user.SysRole;
import com.yaping.dao.mapper.SysRoleMapper;
import com.yaping.webserver.web.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyp
 * @since 2017-10-01
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
	
}
