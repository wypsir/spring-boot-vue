package com.wyp.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wyp.common.entity.user.SysPermission;
import com.wyp.dao.mapper.SysPermissionMapper;
import com.wyp.webserver.web.service.ISysPermissionService;
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
