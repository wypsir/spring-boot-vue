package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.user.SysUser;
import com.yaping.dao.mapper.SysUserMapper;
import com.yaping.webserver.web.service.ISysUserService;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	
}
