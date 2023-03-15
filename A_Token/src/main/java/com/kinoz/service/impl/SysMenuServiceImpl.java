package com.kinoz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.SysMenu;
import com.kinoz.mapper.MenuMapper;
import com.kinoz.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
* @author Hao
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-03-14 15:13:04
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu>
    implements SysMenuService {

}




