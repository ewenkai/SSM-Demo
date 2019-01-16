package com.soldier.controller;

import com.github.pagehelper.PageInfo;
import com.soldier.domain.Product;
import com.soldier.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品
     * @param thispage
     * @param pageSize
     * @throws ServletException
     * @throws IOException
     */
    //@PermitAll
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP","ROLE_USER"})
    @RequestMapping(path = "/findAll")
    public ModelAndView findAll(@RequestParam(name = "thispage",required = true,defaultValue = "1") Integer thispage,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5") Integer pageSize) throws ServletException, IOException {
        List<Product> products = productService.findAll(thispage,pageSize);
        PageInfo pageInfo = new PageInfo(products);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
//        request.setAttribute("pageInfo",pageInfo);
//        request.getRequestDispatcher("/pages/product-list.jsp").forward(request,response);

    }

    /**
     * 添加产品
     * @param product
     * @throws ServletException
     * @throws IOException
     */
    //@PermitAll
    @Secured({"ROLE_ADMIN","ROLE_VIP","ROLE_SVIP","ROLE_QQVIP","ROLE_USER"})
    @RequestMapping("/add")
    public String add(Product product) throws ServletException, IOException {
        productService.add(product);
        return "redirect:/product/findAll";
        //request.getRequestDispatcher("/product/findAll").forward(request,response);
    }
}
