package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Course;
import com.flyfish.onlineEdu.vo.vod.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【course(课程)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:07
* @Entity com.flyfish.edu.pojo.Course
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    //TODO 这个SQL多表再看看
    CoursePublishVo selectCoursePublishInfo(Long id);

}




