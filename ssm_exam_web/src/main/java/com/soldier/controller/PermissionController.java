package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domain.Permission;
import com.soldier.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @param thispage
     * @param pageSize
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed({"admin","vip","svip","QQvip"})
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP"})
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer pageSize
                                ) throws ServletException, IOException {
        List<Permission> permissions = permissionService.findAll(thispage, pageSize);
        PageInfo pageInfo = new PageInfo(permissions);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
//        request.setAttribute("pageInfo",pageInfo);
//        request.getRequestDispatcher("/pages/permission-list.jsp").forward(request,response);
    }

    /**
     * 添加权限
     * @param permission
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/add")
    public String add(Permission permission) throws ServletException, IOException {
        String pid = permissionService.add(permission);
        return "redirect:/permission/findAll?thispage=1&pageSize=5";
        //request.getRequestDispatcher("/permission/findAll?thispage=1&pageSize=5").forward(request,response);
    }

}
