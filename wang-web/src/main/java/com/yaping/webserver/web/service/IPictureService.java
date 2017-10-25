package com.yaping.webserver.web.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yaping.common.entity.Picture;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyp
 * @since 2017-09-18
 */
public interface IPictureService extends IService<Picture> {

    List<Picture> selectUserList(Pagination page, Wrapper wrapper);
}
