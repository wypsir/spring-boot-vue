package com.yaping.webserver.web.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaping.common.entity.Picture;
import com.yaping.dao.mapper.PictureMapper;
import com.yaping.webserver.web.service.IPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private PictureMapper pictureBaseMapper;

    @Override
    public List<Picture> selectUserList(Pagination page, Wrapper wrapper) {
       return pictureBaseMapper.selectUserList(page,wrapper);
    }
}
