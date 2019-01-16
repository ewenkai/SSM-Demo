package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domain.Role;
import com.soldier.domain.UserInfo;
import com.soldier.service.RoleService;
import com.soldier.service.UserRoleService;
import com.soldier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询所有用户
     * @param thispage
     * @param pageSize
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findAll")
    //@RolesAllowed({"admin","vip","svip","qqvip"})
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP"})
    public ModelAndView findAll(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer pageSize
    ) throws ServletException, IOException {
        List<UserInfo> userInfos = userService.findAll(thispage, pageSize);
        PageInfo pageInfo = new PageInfo(userInfos);
        //request.getRequestDispatcher("/pages/user-list.jsp").forward(request,response);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 查询用户详情
     * @param thispage
     * @param pageSize
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findById")
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")

    public ModelAndView findById(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                         @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer pageSize,
                         String id) throws ServletException, IOException {

        UserInfo userInfo = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
//        request.setAttribute("user",userInfo);
//        request.getRequestDispatcher("/pages/user-show.jsp").forward(request,response);
    }

    /**
     * 添加用户
     * @param userInfo

     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/add")
    public String Add(UserInfo userInfo) throws ServletException, IOException {

        String uid = userService.add(userInfo);
        //request.getRequestDispatcher("/user/findAll").forward(request,response);
        return "redirect:/user/findAll";
    }

    /**
     * 查询指定用户(通过uid指定)可添加的角色
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id) throws ServletException, IOException{
        List<Role> roles = roleService.findByUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("userid",id);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
//        request.setAttribute("roles",roles);
//        request.setAttribute("userid",id);
//        request.getRequestDispatcher("/pages/user-role-add.jsp").forward(request,response);

    }

    /**
     * 给指定用户添加角色
     * @param userId
     * @param ids
     * @throws ServletException
     * @throws IOException
     */
    //@RolesAllowed("admin")
    @Secured("ROLE_ADMIN")
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser( String userId,@RequestParam("ids") String[] ids) throws ServletException, IOException {
        for (String id : ids) {
            userRoleService.add(userId,id);
        }
        return "redirect:/user/findById?id="+userId+"&thispage=1&pageSize=5";
        //request.getRequestDispatcher("/user/findById?id="+userId+"&thispage=1&pageSize=5").forward(request,response);
    }

}
