package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.Picture;
import com.yaping.webserver.web.service.IPictureService;
import org.springframework.stereotype.Service;
import com.yaping.dao.mapper.PictureMapper;

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
