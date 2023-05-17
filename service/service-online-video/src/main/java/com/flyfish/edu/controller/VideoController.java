package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.VideoService;
import com.flyfish.onlineEdu.model.vod.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @autohr flyfish
 * @date: 2023/5/16 11:55
 * @description:
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/get/{id}")
    public R getOne(@PathVariable Long id){
        Video byId = videoService.getById(id);
        return R.ok(byId);
    }

    @PostMapping("/add")
    public R addOne(@RequestBody Video videoParam){
        videoService.save(videoParam);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody Video videoParam){
        videoService.updateById(videoParam);
        return R.ok();
    }

    @DeleteMapping("remove/{id}")
    public R delete(@PathVariable Long id){
        videoService.removeById(id);
        return R.ok();
    }
}
