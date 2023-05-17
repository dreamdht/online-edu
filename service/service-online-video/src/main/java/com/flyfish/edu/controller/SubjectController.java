package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.SubjectService;
import com.flyfish.onlineEdu.model.vod.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @autohr flyfish
 * @date: 2023/5/15 20:11
 * @description:
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getNodeList/{id}")
    public R getNodeListTree(@PathVariable Long id){
        List<Subject> list = subjectService.getNodeList(id);
        return R.ok(list);
    }

    /*下载excel*/
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response){
        subjectService.downloadExcel(response);
    }

    /*导入excel*/
    @PostMapping("/importExcel")
    public R importExcelData(MultipartFile file){
        boolean result = subjectService.uploadExcel(file);
        if(result) return R.ok();
        return R.error("导入失败");
    }
}
