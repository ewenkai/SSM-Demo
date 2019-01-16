package com.soldier.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soldier.dao.UserDao;
import com.soldier.domain.Role;
import com.soldier.domain.UserInfo;
import com.soldier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //注入加密类
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByName(username);
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authority = getAuthority(roles);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true, true,
                authority);
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authoritys=new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    /**
     * 分页查询所有用户
     * @param thispage
     * @param pageSize
     * @return
     */
    @Override
    public List<UserInfo> findAll(Integer thispage, Integer pageSize) {
        PageHelper.startPage(thispage,pageSize);
        List<UserInfo> userInfos = userDao.findAll();


        return userInfos;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @Override
    public String add(UserInfo userInfo) {

        String password = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(password);
        userDao.add(userInfo);
        String uid = userInfo.getId();
        return uid;
    }

    /**
     * 通过uid查询userInfo,并封装roles集合
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id){
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }



}
