package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.TeacherService;
import com.flyfish.edu.mapper.TeacherMapper;
import com.flyfish.onlineEdu.model.vod.Teacher;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【teacher(讲师)】的数据库操作Service实现
* @createDate 2023-05-11 15:43:34
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




