package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.oauth.OAuthUser;
import com.yaping.dao.mapper.OAuthUserMapper;
import com.yaping.webserver.web.service.IOAuthUserService;
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
public class OAuthUserServiceImpl extends ServiceImpl<OAuthUserMapper, OAuthUser> implements IOAuthUserService {
	
}
