package com.shao.file_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.shao.file_service.interceptor.UserLoginToken;
import com.shao.file_service.model.User;
import com.shao.file_service.service.TokenService;
import com.shao.file_service.service.UserService;
import com.shao.file_service.utils.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    TokenService tokenService;
    //登录
    @PostMapping("/login")
    public Object login(@RequestBody final User user) {
        final JSONObject jsonObject = new JSONObject();
        System.out.print(user);
        final User userForBase = userService.selectByPrimaryKey(user.getUserId());
        if (userForBase == null) {
            jsonObject.put("code", Constant.CODE_Other);
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("code", Constant.CODE_Other);
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                final String token = tokenService.getToken(userForBase);
                jsonObject.put("code", Constant.CODE_Correct);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User getUserInfo(@RequestBody final JSONObject jsonObject) {

        final JSONObject userDate = jsonObject.getJSONObject("data");
        final String type = jsonObject.getString("type");
        final User user = JSONObject.toJavaObject(userDate, User.class);
        System.out.println(user.toString() + "  type:" + type);
        return userService.selectByPrimaryKey(user.getUserId());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public User test(@RequestBody final JSONObject jsonObject) {
        System.out.println(jsonObject); 
        
        return userService.selectByPrimaryKey("1");
    }
}