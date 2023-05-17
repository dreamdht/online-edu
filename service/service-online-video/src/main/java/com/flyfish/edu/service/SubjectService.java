package com.flyfish.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flyfish.onlineEdu.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author 会飞的鱼
* @description 针对表【subject(课程科目)】的数据库操作Service
* @createDate 2023-05-15 20:09:16
*/
public interface SubjectService extends IService<Subject> {

    List<Subject> getNodeList(Long id);

    void downloadExcel(HttpServletResponse response);

    boolean uploadExcel(MultipartFile file);
}
