package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【teacher(讲师)】的数据库操作Mapper
* @createDate 2023-05-11 15:43:33
* @Entity com.flyfish.edu.pojo.Teacher
*/
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}




