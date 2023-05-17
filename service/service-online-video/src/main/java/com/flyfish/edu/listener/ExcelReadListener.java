package com.flyfish.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.flyfish.edu.mapper.SubjectMapper;
import com.flyfish.onlineEdu.model.vod.Subject;
import com.flyfish.onlineEdu.vo.vod.SubjectEeVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @autohr flyfish
 * @date: 2023/5/16 10:20
 * @description:
 */
@Component
public class ExcelReadListener extends AnalysisEventListener<SubjectEeVo> {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void invoke(SubjectEeVo data, AnalysisContext context) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(data,subject);
        subjectMapper.insert(subject);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
