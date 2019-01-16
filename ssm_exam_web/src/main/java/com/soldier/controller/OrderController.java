package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domain.Order;
import com.soldier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单 封装成员(member)
     * @param thispage
     * @param pageSize
     * @throws ServletException
     * @throws IOException
     */
    //@PermitAll
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP","ROLE_USER"})
    @RequestMapping(path = "/findAll")
    public ModelAndView findAll(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer pageSize
                        ) throws ServletException, IOException {
        List<Order> orders = orderService.findAll(thispage,pageSize);
        PageInfo pageInfo = new PageInfo(orders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
//        request.setAttribute("pageInfo",pageInfo);
//        request.getRequestDispatcher("/pages/orders-list.jsp").forward(request,response);
    }

    /**
     * 查询指定订单(详情) 封装成员(member),产品(Product),旅行者(traveller)
     * @param id
     * @throws ServletException
     * @throws IOException
     */
    //@PermitAll
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP","ROLE_USER"})
    @RequestMapping(path = "/findById")
    public ModelAndView findById(String id) throws ServletException, IOException {
        Order order = orderService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order",order);
        modelAndView.setViewName("orders-show");
        return modelAndView;
//        request.setAttribute("order",order);
//        request.getRequestDispatcher("/pages/orders-show.jsp").forward(request,response);
    }
}
