package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Chapter;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【chapter(课程)】的数据库操作Mapper
* @createDate 2023-05-16 11:45:45
* @Entity com.flyfish.edu.pojo.Chapter
*/
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

}




