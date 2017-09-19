package com.wyp.webserver.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wyp.common.entity.BeautifulPictures;
import com.wyp.common.entity.Result;
import com.wyp.webserver.web.service.IBeautifulPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyp
 * @since 2017-09-18
 */
@RestController
@RequestMapping("/api/beautifulPictures")
@CacheConfig(cacheNames = "beautifulPictures")
public class BeautifulPicturesController {


    @Autowired
    private IBeautifulPicturesService beautifulPicturesService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping
    @Cacheable
    public Result page(Page<BeautifulPictures> page) {

        page = beautifulPicturesService.selectPage(page);
        return Result.success(page);
    }
}
