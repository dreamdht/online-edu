package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
* @author 会飞的鱼
* @description 针对表【subject(课程科目)】的数据库操作Mapper
* @createDate 2023-05-15 20:09:16
* @Entity com.flyfish.edu.pojo.Subject
*/
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

}




