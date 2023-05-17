package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.CourseCollect;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【course_collect(课程收藏)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:28
* @Entity com.flyfish.edu.pojo.CourseCollect
*/
@Mapper
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {

}




