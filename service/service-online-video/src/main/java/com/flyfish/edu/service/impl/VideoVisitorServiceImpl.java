package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.VideoVisitorService;
import com.flyfish.edu.mapper.VideoVisitorMapper;
import com.flyfish.onlineEdu.model.vod.VideoVisitor;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorCountVo;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author 会飞的鱼
* @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Service实现
* @createDate 2023-05-16 11:46:39
*/
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor>
    implements VideoVisitorService{

    @Override
    public Map<String,Object> getCountInfo(
            @Param("courseId") Long courseId,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime) {

        Map<String,Object> map = new HashMap<>();

        List<VideoVisitorCountVo> videoInfoList = baseMapper.getUserVideoCountInfo(courseId, startTime, endTime);

        // 封装横坐标时间数据集合
        List<String> dateList = videoInfoList.stream()
                .map(VideoVisitorCountVo::getJoinTime)
                .collect(Collectors.toList());

        // 封装纵坐标观看次数数据集合
        List<Integer> countList = videoInfoList
                .stream()
                .map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());

        // 封装到Map集合
        map.put("xData",dateList);
        map.put("yData",countList);

        return map;
    }
}




