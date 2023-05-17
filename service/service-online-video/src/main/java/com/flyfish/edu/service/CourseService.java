package com.flyfish.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flyfish.onlineEdu.model.vod.Course;
import com.flyfish.onlineEdu.vo.vod.CourseFormVo;
import com.flyfish.onlineEdu.vo.vod.CoursePublishVo;
import com.flyfish.onlineEdu.vo.vod.CourseQueryVo;

import java.util.Map;
import java.util.Objects;

/**
* @author 会飞的鱼
* @description 针对表【course(课程)】的数据库操作Service
* @createDate 2023-05-16 11:46:07
*/
public interface CourseService extends IService<Course> {

    Map<String, Object> findPageCourse(Page pageParam, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getInfoById(Long id);

    boolean updateCourseInfo(CourseFormVo courseFormVoParam);

    CoursePublishVo selectPublishInfo(Long id);

    void confirmPublish(Long id);

    void removeByCourseId(Long id);
}
