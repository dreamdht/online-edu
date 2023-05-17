package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.UserInfoService;
import com.flyfish.edu.mapper.UserInfoMapper;
import com.flyfish.onlineEdu.model.user.UserInfo;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【user_info(用户表)】的数据库操作Service实现
* @createDate 2023-05-11 17:56:48
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




