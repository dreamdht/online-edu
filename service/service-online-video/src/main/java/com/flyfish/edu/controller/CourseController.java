package com.flyfish.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyfish.edu.R.R;
import com.flyfish.edu.service.CourseService;
import com.flyfish.onlineEdu.model.vod.Course;
import com.flyfish.onlineEdu.vo.vod.CourseFormVo;
import com.flyfish.onlineEdu.vo.vod.CoursePublishVo;
import com.flyfish.onlineEdu.vo.vod.CourseQueryVo;
import com.flyfish.onlineEdu.vo.vod.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @autohr flyfish
 * @date: 2023/5/16 11:55
 * @description:
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/page/{page}/{limit}")
    public R getPageListInfo(@PathVariable Long page,
                             @PathVariable Long limit,
                             CourseQueryVo courseQueryVo){
        Page<Course> pageParam = new Page<>(page,limit);
        Map<String, Object> map = courseService.findPageCourse(pageParam,courseQueryVo);
        return R.ok().data(map);
    }

    @PostMapping("/save")
    public R saveCourse(@RequestBody CourseFormVo courseFormVo){
        Long id = courseService.saveCourseInfo(courseFormVo);
        return R.ok(id);
    }

    // 根据ID获取课程信息
    @GetMapping("/get/{id}")
    public R getCourseInfo(@PathVariable Long id){
        CourseFormVo courseFormVo = courseService.getInfoById(id);
        return R.ok(courseFormVo);
    }

    // 修改课程信息
    @PostMapping("/update")
    public R updateCourseInfo(@RequestBody CourseFormVo courseFormVoParam){
        System.out.println(courseFormVoParam);
        boolean result = courseService.updateCourseInfo(courseFormVoParam);
        if(result) return R.ok(courseFormVoParam.getId());
        return R.error("修改失败");
    }

    // 根据课程ID查询课程发布信息（设计三张表，teacher、subject、course）
    @GetMapping("/publishInfo/{id}")
    public R getCoursePublishInfo(@PathVariable Long id){
        CoursePublishVo coursePublishVo = courseService.selectPublishInfo(id);
        return R.ok(coursePublishVo);
    }

    // 最终发布课程信息
    @PutMapping("/publish/{id}")
    public R confirmPublishCourse(@PathVariable Long id){
        courseService.confirmPublish(id);
        return R.ok();
    }

    // 查询所有课程
    @GetMapping("/findAll")
    public R getAllCourse(){
        List<Course> list = courseService.list();
        return R.ok(list);
    }

    // 根据courseI_id删除课程和关联的数据
    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable Long id){
        courseService.removeByCourseId(id);
        return R.ok();
    }

}
