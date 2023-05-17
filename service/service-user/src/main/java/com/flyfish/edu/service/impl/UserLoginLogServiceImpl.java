package com.flyfish.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyfish.edu.service.UserLoginLogService;
import com.flyfish.edu.mapper.UserLoginLogMapper;
import com.flyfish.onlineEdu.model.user.UserLoginLog;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【user_login_log(用户登陆记录表)】的数据库操作Service实现
* @createDate 2023-05-11 17:56:55
*/
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog>
    implements UserLoginLogService{

}




