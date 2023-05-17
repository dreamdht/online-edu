package com.flyfish.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flyfish.edu.R.R;
import com.flyfish.onlineEdu.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @autohr flyfish
 * @date: 2023/5/11 18:27
 * @description: 临时登录接口
 */

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @PostMapping("/login")
    public R userLogin(@RequestBody UserInfo userInfoParam) {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return R.ok(map);
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
