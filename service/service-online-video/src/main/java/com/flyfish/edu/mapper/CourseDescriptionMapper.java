package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.CourseDescription;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【course_description(课程简介)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:25
* @Entity com.flyfish.edu.pojo.CourseDescription
*/
@Mapper
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {

}




