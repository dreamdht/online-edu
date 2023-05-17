package com.flyfish.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flyfish.edu.R.R;
import com.flyfish.edu.service.UserInfoService;
import com.flyfish.onlineEdu.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @autohr flyfish
 * @date: 2023/5/11 18:00
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public R userLogin(@RequestBody UserInfo userInfoParam) {
        if (Objects.isNull(userInfoParam)
                || StringUtils.isEmpty(userInfoParam.getName())
                || StringUtils.isEmpty(userInfoParam.getPassword())
        ) {
            return R.error("提交信息不能为空");
        }

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getName,userInfoParam.getName());
        queryWrapper.eq(UserInfo::getPassword,userInfoParam.getPassword());

        UserInfo user = userInfoService.getOne(queryWrapper);
        if(Objects.isNull(user)){
            return R.error("用户名或密码错误!");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");

        return R.ok(user,map);
    }

    @GetMapping("/info")
    public R userInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return R.ok(map);
    }
}
