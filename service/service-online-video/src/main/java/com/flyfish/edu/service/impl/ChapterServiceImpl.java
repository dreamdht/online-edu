package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.mapper.VideoMapper;
import com.flyfish.edu.service.ChapterService;
import com.flyfish.edu.mapper.ChapterMapper;
import com.flyfish.onlineEdu.model.vod.Chapter;
import com.flyfish.onlineEdu.model.vod.Video;
import com.flyfish.onlineEdu.vo.vod.ChapterVo;
import com.flyfish.onlineEdu.vo.vod.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 会飞的鱼
* @description 针对表【chapter(课程)】的数据库操作Service实现
* @createDate 2023-05-16 11:45:45
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService{

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVo> getNodeListTree(Long id) {
        // 定义最终返回结果List集合
        List<ChapterVo> resultList = new ArrayList<>();

        // 根据课程id获取课程里所有章节
        LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getCourseId,id);
        List<Chapter> chapterList = baseMapper.selectList(queryWrapper);

        // 根据课程id获取课程里面的所有小节
        LambdaQueryWrapper<Video> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Video::getCourseId,id);
        List<Video> videoList = videoMapper.selectList(wrapper);

        // 遍历封装数据
        for (Chapter chapter : chapterList) {
            // 封装章节
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);

            // 封装小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for (Video video : videoList) {
                // 由于查询的是所有章节的id，所以要将当前遍历的章节中的小节从videoList中筛选出来
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }

            chapterVo.setChildren(videoVoList);
            resultList.add(chapterVo);
        }

        return resultList;
    }
}




