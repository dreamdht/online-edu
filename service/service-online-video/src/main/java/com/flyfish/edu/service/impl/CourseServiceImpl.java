package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.mapper.*;
import com.flyfish.edu.service.CourseService;
import com.flyfish.onlineEdu.model.vod.*;
import com.flyfish.onlineEdu.vo.vod.CourseFormVo;
import com.flyfish.onlineEdu.vo.vod.CoursePublishVo;
import com.flyfish.onlineEdu.vo.vod.CourseQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
* @author 会飞的鱼
* @description 针对表【course(课程)】的数据库操作Service实现
* @createDate 2023-05-16 11:46:07
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private CourseDescriptionMapper descriptionMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

    private Course getTeacherOrSubjectName(Course course){
        // 查询讲师名称
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        if(teacher != null){
            Map<String, Object> map = course.getParam();
            map.put("teacherName",teacher.getName());
        }

        // 查询一级分类名称
        Subject subjectParent = subjectMapper.selectById(course.getSubjectParentId());
        if(subjectParent != null){
            Map<String, Object> map = subjectParent.getParam();
            map.put("subjectParentTitle",subjectParent.getTitle());
        }

        // 查询二级分裂名称
        Subject subject = subjectMapper.selectById(course.getSubjectId());
        if(subject != null){
            Map<String, Object> map = subject.getParam();
            map.put("subjectTitle",subject.getTitle());
        }

        return course;
    }

    @Override
    public Map<String, Object> findPageCourse(Page pageParam, CourseQueryVo courseQueryVo) {
        // 讲师id
        Long teacherId = courseQueryVo.getTeacherId();
        // 课程名称
        String title = courseQueryVo.getTitle();
        // 二级分类id
        Long subjectId = courseQueryVo.getSubjectId();
        // 一级分类id
        Long subjectParentId = courseQueryVo.getSubjectParentId();

        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.like(Course::getTitle,title);
        }
        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq(Course::getTeacherId,teacherId);
        }
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq(Course::getSubjectId,subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq(Course::getSubjectParentId,subjectParentId);
        }

        // 查询
        Page<Course> pageInfo = baseMapper.selectPage(pageParam, queryWrapper);
        // 总记录数
        long totalCount = pageInfo.getTotal();
        // 总页数
        long totalPage = pageInfo.getPages();
        // 当前页
        long currentPage = pageInfo.getCurrent();
        //每页记录数
        long size = pageInfo.getSize();
        // 每页数据集合
        List<Course> records = pageInfo.getRecords();

        //遍历封装讲师和分类名称
        records.stream().forEach(item -> {
            this.getTeacherOrSubjectName(item);
        });

        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);

        return map;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        // 添加课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);

        //添加课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());

        //设置课程ID
        courseDescription.setCourseId(course.getId());

        descriptionMapper.insert(courseDescription);

        return course.getId();
    }

    @Override
    public CourseFormVo getInfoById(Long id) {
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }

        // 获取课程描述信息
        LambdaQueryWrapper<CourseDescription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseDescription::getCourseId,id);
        CourseDescription courseDescription = descriptionMapper.selectOne(queryWrapper);

        // 封装数据
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        courseFormVo.setDescription(courseDescription.getDescription());

        return courseFormVo;
    }

    @Override
    public boolean updateCourseInfo(CourseFormVo courseFormVoParam) {
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVoParam,course);

        baseMapper.updateById(course);

        LambdaQueryWrapper<CourseDescription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseDescription::getCourseId,courseFormVoParam.getId());
        CourseDescription description = descriptionMapper.selectOne(queryWrapper);
        description.setDescription(courseFormVoParam.getDescription());

        // 设置课程描述id
        description.setCourseId(course.getId());

        int update = descriptionMapper.updateById(description);

        return update>0;
    }

    @Override
    public CoursePublishVo selectPublishInfo(Long id) {
        CoursePublishVo coursePublishVo = baseMapper.selectCoursePublishInfo(id);
        return coursePublishVo;
    }

    @Override
    public void confirmPublish(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }

    // 根据课程ID删除该课程有挂的所有信息
    @Override
    public void removeByCourseId(Long id) {
        // 删除小节
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId,id);
        videoMapper.delete(videoLambdaQueryWrapper);

        // 删除章节
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId,id);
        chapterMapper.delete(chapterLambdaQueryWrapper);

        // 删除描述
        LambdaQueryWrapper<CourseDescription> courseDescriptionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseDescriptionLambdaQueryWrapper.eq(CourseDescription::getCourseId,id);
        descriptionMapper.delete(courseDescriptionLambdaQueryWrapper);

        // 删除课程
        baseMapper.deleteById(id);

    }


}




