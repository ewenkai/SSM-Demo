package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domain.Permission;
import com.soldier.domain.Role;
import com.soldier.service.PermissionService;
import com.soldier.service.RolePermissionService;
import com.soldier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有角色 没有封装permission
     * @param thispage
     * @param pageSize
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize
                        ) throws ServletException, IOException {
        List<Role> roles = roleService.findAll(thispage,pageSize);
        PageInfo pageInfo = new PageInfo(roles);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
//        request.setAttribute("pageInfo",pageInfo);
//        request.getRequestDispatcher("/pages/role-list.jsp").forward(request,response);
    }


    /**
     * 添加角色
     * @param role
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/add")
    public String Add(Role role) throws ServletException, IOException {
        String rid = roleService.add(role);
        return "redirect:/role/findAll?thispage=1&pageSize=4";
        //request.getRequestDispatcher("/role/findAll?thispage=1&pageSize=4").forward(request,response);

    }

    /**
     * 查询指定角色可添加的权限
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws ServletException, IOException {
        List<Permission> permissions = permissionService.findByRole(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleid",id);
        modelAndView.addObject("permissions",permissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
//        request.setAttribute("roleid",id);
//        request.setAttribute("permissions",permissions);
//        request.getRequestDispatcher("/pages/role-permission-add.jsp").forward(request,response);
    }

    /**
     * 给指定角色添加权限
     * @param roleid
     * @param ids
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") String roleid,@RequestParam("ids") String[] ids) throws ServletException, IOException {
        for (String id : ids) {
            rolePermissionService.add(id,roleid);
        }
        return "redirect:/role/findByRid?id="+roleid;
        //request.getRequestDispatcher("/role/findByRid?id="+roleid).forward(request,response);

    }

    /**
     * 查询指定(根据rid查询)角色详情 封装permission之后的
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findByRid")
    public ModelAndView findByRid(String id) throws ServletException, IOException {
        Role role = roleService.findByRid(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.setViewName("role-show");
        return modelAndView;
//        request.setAttribute("role",role);
//        request.getRequestDispatcher("/pages/role-show.jsp").forward(request,response);

    }
}
