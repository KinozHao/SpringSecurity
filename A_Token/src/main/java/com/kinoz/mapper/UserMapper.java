package com.kinoz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinoz.domain.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author haogu
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-03-08 16:15:45
* @Entity generator.domain.SysUser
*/

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
