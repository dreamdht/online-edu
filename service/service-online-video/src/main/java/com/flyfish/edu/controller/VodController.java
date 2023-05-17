package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autohr flyfish
 * @date: 2023/5/17 18:27
 * @description: 第三方服务腾讯云VOD功能
 */
@RestController
@RequestMapping("/vod")
public class VodController {
    @Autowired
    private VodService vodService;

    // TODO 目前实现的硬上传，后续自己实现动态上传
    // 地址： http://service-bmygm7hm-1317112389.gz.apigw.tencentcs.com/release

    @PostMapping("/trueUpload")
    public R trueUpload(){
        return null;
    }

    @PostMapping("/upload")
    public R uploadVideo(){
        String fileId = vodService.upload();
        return R.ok(fileId);
    }

    @PostMapping("/remove/{id}")
    public R removeVideo(@PathVariable String id){
        String fileId = vodService.remove(id);
        return R.ok(fileId);
    }
}
