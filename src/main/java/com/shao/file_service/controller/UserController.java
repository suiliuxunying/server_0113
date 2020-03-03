package com.shao.file_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.shao.file_service.model.User;
import com.shao.file_service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * UserController
 */
@CrossOrigin//解决跨域问题！
@Controller
@ResponseBody
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public User getUserInfo(@RequestBody JSONObject jsonObject) {
        
        JSONObject userDate = jsonObject.getJSONObject("data");
        String type =jsonObject.getString("type");
        User user =JSONObject.toJavaObject(userDate, User.class);
        System.out.println(user.toString()+"  type:"+type);
        return userService.selectByPrimaryKey(user.getUserId());
    }
    @RequestMapping(value="/test", method=RequestMethod.POST)
    public User test(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject); 
        
        return userService.selectByPrimaryKey("1");
    }
}