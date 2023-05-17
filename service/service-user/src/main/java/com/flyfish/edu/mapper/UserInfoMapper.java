package com.flyfish.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyfish.onlineEdu.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【user_info(用户表)】的数据库操作Mapper
* @createDate 2023-05-11 17:56:48
* @Entity com.flyfish.edu.pojo.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




