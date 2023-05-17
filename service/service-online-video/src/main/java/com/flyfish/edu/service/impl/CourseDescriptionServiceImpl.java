package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.CourseDescriptionService;
import com.flyfish.edu.mapper.CourseDescriptionMapper;
import com.flyfish.onlineEdu.model.vod.CourseDescription;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【course_description(课程简介)】的数据库操作Service实现
* @createDate 2023-05-16 11:46:25
*/
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription>
    implements CourseDescriptionService{

}




