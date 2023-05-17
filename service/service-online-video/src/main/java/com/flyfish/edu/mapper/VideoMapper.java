package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Video;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【video(课程视频)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:34
* @Entity com.flyfish.edu.pojo.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

}




