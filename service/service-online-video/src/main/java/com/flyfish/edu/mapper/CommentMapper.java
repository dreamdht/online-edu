package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.vod.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【comment(评论)】的数据库操作Mapper
* @createDate 2023-05-16 11:46:02
* @Entity com.flyfish.edu.pojo.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




