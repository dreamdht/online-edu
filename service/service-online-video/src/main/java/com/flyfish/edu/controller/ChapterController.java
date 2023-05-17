package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.ChapterService;
import com.flyfish.onlineEdu.model.vod.Chapter;
import com.flyfish.onlineEdu.vo.vod.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @autohr flyfish
 * @date: 2023/5/16 11:53
 * @description:
 * 操作课程的章节。
 * 章节里包含了小杰
 * ************结构：*****************
 *          第一章:
 *              第1节
 *              第2节
 *          第二章:
 *              第1节
 *              第2节
 *          .....
 *          .....
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    // 获取章节+对应小节的树状结构集合
    @GetMapping("/getNodeList/{id}")
    public R getNodeListTree(@PathVariable Long id) {
        List<ChapterVo> list = chapterService.getNodeListTree(id);

        return R.ok(list);
    }

    // 添加章节
    @PostMapping("/add")
    public R add(@RequestBody Chapter chapterParam){
        chapterService.save(chapterParam);
        return R.ok();
    }

    // 修改章节
    @PostMapping("/update")
    public R update(@RequestBody Chapter chapterParam){
        chapterService.updateById(chapterParam);
        return R.ok();
    }

    // 根据id查询章节
    @GetMapping("/get/{id}")
    public R getOne(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return R.ok(chapter);
    }

    // 删除章节
    @DeleteMapping("/remove/{id}")
    public R delete(@PathVariable Long id){
        chapterService.removeById(id);
        return R.ok();
    }
}
