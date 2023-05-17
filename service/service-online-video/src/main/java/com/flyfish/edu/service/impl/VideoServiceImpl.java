package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.VideoService;
import com.flyfish.edu.mapper.VideoMapper;
import com.flyfish.onlineEdu.model.vod.Video;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【video(课程视频)】的数据库操作Service实现
* @createDate 2023-05-16 11:46:34
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




