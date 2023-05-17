package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.CommentService;
import com.flyfish.edu.mapper.CommentMapper;
import com.flyfish.onlineEdu.model.vod.Comment;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【comment(评论)】的数据库操作Service实现
* @createDate 2023-05-16 11:46:02
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




