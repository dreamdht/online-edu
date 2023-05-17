package com.flyfish.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyfish.onlineEdu.model.vod.Chapter;
import com.flyfish.onlineEdu.vo.vod.ChapterVo;

import java.util.List;

/**
* @author 会飞的鱼
* @description 针对表【chapter(课程)】的数据库操作Service
* @createDate 2023-05-16 11:45:45
*/
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNodeListTree(Long id);
}
