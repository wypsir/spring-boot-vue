package com.wyp.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wyp.common.entity.Picture;
import com.wyp.webserver.web.service.IPictureService;
import org.springframework.stereotype.Service;
import  com.wyp.dao.mapper.PictureMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyp
 * @since 2017-09-18
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements IPictureService {
}
