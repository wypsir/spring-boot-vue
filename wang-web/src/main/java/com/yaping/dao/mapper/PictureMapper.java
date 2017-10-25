package com.yaping.dao.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yaping.common.entity.Picture;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wyp
 * @since 2017-09-18
 */
public interface PictureMapper extends BaseMapper<Picture> {


    @Select("selectUserList")
    List<Picture> selectUserList(Pagination  page,@Param("ew") Wrapper<Picture> wrapper);
}