package com.kinoz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinoz.domain.pojo.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Hao
* @description 针对表【sys_menu】的数据库操作Mapper
* @createDate 2023-03-14 15:13:04
* @Entity generator.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long id);

}




