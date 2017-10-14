package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.user.SysPermission;
import com.yaping.dao.mapper.SysPermissionMapper;
import com.yaping.webserver.web.service.ISysPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyp
 * @since 2017-10-01
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

}
