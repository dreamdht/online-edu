package com.flyfish.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyfish.onlineEdu.model.vod.VideoVisitor;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author 会飞的鱼
* @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Service
* @createDate 2023-05-16 11:46:39
*/
public interface VideoVisitorService extends IService<VideoVisitor> {

    Map<String,Object> getCountInfo(Long courseId, String startTime, String endTime);
}
