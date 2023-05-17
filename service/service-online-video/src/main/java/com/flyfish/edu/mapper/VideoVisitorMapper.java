package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.VideoVisitor;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorCountVo;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorQueryVo;
import com.flyfish.onlineEdu.vo.vod.VideoVisitorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author 会飞的鱼
* @description 针对表【video_visitor(视频来访者记录表)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:39
* @Entity com.flyfish.edu.pojo.VideoVisitor
*/
@Mapper
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> getUserVideoCountInfo(
            @Param("courseId") Long courseId,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}




