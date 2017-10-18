package com.yaping.webserver.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yaping.common.entity.Picture;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyp
 * @since 2017-09-18
 */
public interface IPictureService extends IService<Picture> {

    List<Picture> selectUserList(Pagination page, Integer picturesId);
}
