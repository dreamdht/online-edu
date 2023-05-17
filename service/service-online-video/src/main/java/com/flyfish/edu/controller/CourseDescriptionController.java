package com.flyfish.edu.controller;

import com.flyfish.edu.service.CourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autohr flyfish
 * @date: 2023/5/16 11:54
 * @description:
 */
@RestController
@RequestMapping("/courseDescription")
public class CourseDescriptionController {
    @Autowired
    private CourseDescriptionService courseDescriptionService;

}
