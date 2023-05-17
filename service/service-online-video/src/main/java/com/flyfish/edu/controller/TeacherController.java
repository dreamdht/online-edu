package com.flyfish.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyfish.edu.R.R;
import com.flyfish.edu.exception.GlobalExceptionHandler;
import com.flyfish.edu.exception.MyException;
import com.flyfish.edu.service.TeacherService;
import com.flyfish.onlineEdu.model.vod.Teacher;
import com.flyfish.onlineEdu.vo.vod.TeacherQueryVo;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @autohr flyfish
 * @date: 2023/5/11 15:55
 * @description:
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /*查询所有讲师*/
    @GetMapping("/findAll")
    public R getTeacherList(){
        List<Teacher> list = teacherService.list();
        return R.ok(list);
    }

    /*逻辑删除讲师*/
    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Long id){
        boolean result = teacherService.removeById(id);
        if(result) return R.error();
        return R.ok();
    }


    /*批量删除讲师*/
    @DeleteMapping("/deleteMany")
    public R deleteMany(@RequestBody List<Integer> list){
        boolean result = teacherService.removeBatchByIds(list);
        if(result) return R.ok();
        return R.error();
    }


    /*分页条件查询讲师*/
    @PostMapping("/page/{current}/{limit}")
    public R getTeacherPageList(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody TeacherQueryVo teacherQueryVo){

        System.out.println(current+"============");
        System.out.println(limit+"============");
        System.out.println(teacherQueryVo+"============");

        Page<Teacher> page = new Page<>(current,limit);
        Page<Teacher> teacherPage;

        if(teacherQueryVo == null){
            teacherPage = teacherService.page(page, null);
            return R.ok(teacherPage);
        }

        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();

        if(!StringUtils.isEmpty(teacherQueryVo.getName())){
            queryWrapper.like(Teacher::getName,teacherQueryVo.getName());
        }
        if(!StringUtils.isEmpty(teacherQueryVo.getLevel())){
            queryWrapper.eq(Teacher::getLevel,teacherQueryVo.getLevel());
        }
        if(!StringUtils.isEmpty(teacherQueryVo.getJoinDateBegin())){
            queryWrapper.ge(Teacher::getJoinDate,teacherQueryVo.getJoinDateBegin());
        }
        if(!StringUtils.isEmpty(teacherQueryVo.getJoinDateEnd())){
            queryWrapper.le(Teacher::getJoinDate,teacherQueryVo.getJoinDateEnd());
        }

        teacherPage = teacherService.page(page,queryWrapper);
        return R.ok(teacherPage);
    }

    /*添加讲师*/
    @PostMapping("/add")
    public R saveOneTeacher(@RequestBody Teacher teacherParam){
        if(Objects.isNull(teacherParam)){
            return R.error("添加参数为空");
        }

        boolean result = teacherService.save(teacherParam);
        if(result) return R.ok("添加成功");
        return R.error("添加失败");
    }

    /*根据ID查询讲师*/
    @GetMapping("/getOne/{id}")
    public R getOneById(@PathVariable Long id){
        if(StringUtils.isEmpty(id)) {
            return R.error("提交参数为空");
        }

        Teacher teacher = teacherService.getById(id);

        if(Objects.isNull(teacher)) {
            return R.error("没有查到");
        }

        return R.ok(teacher);
    }

    /*修改讲师 ById*/
    @PostMapping("/update")
    public R updateTeacherById(@RequestBody Teacher teacherParam){
        if(Objects.isNull(teacherParam)) {
            return R.error("提交参数为空!");
        }

        Teacher teacher = teacherService.getById(teacherParam.getId());
        if(Objects.isNull(teacher)){
            return R.error("该id没有对应老师");
        }

        teacherParam.setUpdateTime(new Date());
        boolean result = teacherService.updateById(teacherParam);

        if(result) {
            return R.ok();
        }
        return R.error("修改失败");
    }

//    @PostMapping("/demo")
//    public R demo(){
//        try {
//            int x = 1 /0;
//        }catch (Exception e){
//            throw new MyException(400,"除数不能为0");
//        }
//        return null;
//    }
}
