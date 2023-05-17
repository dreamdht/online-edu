package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.VideoVisitorService;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @autohr flyfish
 * @date: 2023/5/16 11:56
 * @description: 统计视频播放量接口
 */
@RestController
@Slf4j
@RequestMapping("/videoVisitor")
public class VideoVisitorController {
    @Autowired
    private VideoVisitorService videoVisitorService;

    @GetMapping("/countInfo/{courseId}/{startTime}/{endTime}")
    public R getUserVideoVisitorCountInfo(
            @PathVariable Long courseId,
            @PathVariable String startTime,
            @PathVariable String endTime)
    {
        log.info("进入方法，获取了参数:{},{},{}",courseId,startTime,endTime);
        System.out.println("=======>进入了该方法!!");
        Map<String,Object> map = videoVisitorService.getCountInfo(courseId,startTime,endTime);
        return R.ok(map);
    }

//    @GetMapping("/demo/{x}/{y}")
//    public R demo(@PathVariable String x,@PathVariable String y){
//        return R.ok().data("你好啊哈哈"+x+y);
//    }

}
