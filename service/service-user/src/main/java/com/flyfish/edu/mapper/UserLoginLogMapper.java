package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.user.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【user_login_log(用户登陆记录表)】的数据库操作Mapper
* @createDate 2023-05-11 17:56:55
* @Entity com.flyfish.edu.pojo.UserLoginLog
*/
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog> {

}




