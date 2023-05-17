package com.flyfish.edu.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.flyfish.edu.listener.ExcelReadListener;
import com.flyfish.edu.service.SubjectService;
import com.flyfish.edu.mapper.SubjectMapper;
import com.flyfish.onlineEdu.model.vod.Subject;
import com.flyfish.onlineEdu.vo.vod.SubjectEeVo;
import com.flyfish.onlineEdu.vo.vod.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 会飞的鱼
 * @description 针对表【subject(课程科目)】的数据库操作Service实现
 * @createDate 2023-05-15 20:09:16
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
        implements SubjectService {

    public boolean hasChildren(Long id) {
        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Subject::getParentId, id);
        Long count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public List<Subject> getNodeList(Long id) {
        LambdaQueryWrapper<Subject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Subject::getParentId, id);
        List<Subject> list = baseMapper.selectList(queryWrapper);

        for (Subject subject : list) {
            boolean temp = hasChildren(subject.getId());
            subject.setHasChildren(temp);
        }
        return list;
    }

    /*下载Excel*/
    @Override
    public void downloadExcel(HttpServletResponse response) {
        // 设置响应信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        try {
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + fileName + ".xlsx");

            // 查询所有数据
            List<Subject> subjectList = baseMapper.selectList(null);
            // 封装到VO对象列表
            List<SubjectEeVo> voList = new ArrayList<>(subjectList.size());

            // bean内容拷贝
            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject, subjectEeVo);
                voList.add(subjectEeVo);
            }

            // 执行导出
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类列表")
                    .doWrite(voList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*导入Excel表到数据库*/
    public boolean uploadExcel(MultipartFile file) {
        try {
            EasyExcel.read(
                    file.getInputStream(),
                    SubjectEeVo.class,
                    new ExcelReadListener()
            )
                    .sheet()
                    .doRead()
            ;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}




