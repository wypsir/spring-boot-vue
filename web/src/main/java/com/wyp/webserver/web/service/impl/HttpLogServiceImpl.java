package com.wyp.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wyp.common.entity.HttpLog;
import com.wyp.dao.mapper.HttpLogMapper;
import com.wyp.webserver.web.service.IHttpLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyp
 * @since 2017-09-19
 */
@Service
public class HttpLogServiceImpl extends ServiceImpl<HttpLogMapper, HttpLog> implements IHttpLogService {
	
}
