package com.mw.education.controller;

import com.mw.education.dao.DeanMapper;
import com.mw.education.dao.StudentMapper;
import com.mw.education.dao.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DeanMapper deanMapper;

    public static class LoginData {
        private String code;
        private String password;
        private String type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * 登录接口
     * @param loginData 包含用户登录信息的对象
     * @param session HttpSession对象
     * @return AjaxResult 包含登录结果的对象
     */
    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult login(@RequestBody LoginData loginData, HttpSession session) {
        System.out.println("Login attempt with code: " + loginData.getCode() + ", type: " + loginData.getType());

        int count = 0;
        String userType = loginData.getType();

        if ("dean".equals(userType)) {
            count = deanMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            System.out.println("Dean login count: " + count);
        } else if ("teacher".equals(userType)) {
            count = teacherMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            System.out.println("Teacher login count: " + count);
        } else if ("student".equals(userType)) {
            count = studentMapper.countByCodeAndPassword(loginData.getCode(), loginData.getPassword());
            System.out.println("Student login count: " + count);
        } else {
            System.out.println("Invalid login type");
            return AjaxResult.error().code(400).msg("Invalid login type");
        }

        if (count == 0) {
            System.out.println("Invalid code or password");
            return AjaxResult.error().code(401).msg("Invalid code or password");
        } else {
            System.out.println("Login successful");
            session.setAttribute("user-code", loginData.getCode());
            session.setAttribute("user-type", userType);
            return AjaxResult.success().msg("Login successful");
        }
    }

    /**
     * 登出接口
     * @param session HttpSession对象
     * @return AjaxResult 包含登出结果的对象
     */
    @GetMapping("/logout")
    public AjaxResult logout(HttpSession session) {
        session.invalidate();
        return AjaxResult.success().msg("Logout successful");
    }

    /**
     * 获取登录信息接口
     * @param session HttpSession对象
     * @return AjaxResult 包含登录信息的对象
     */
    @GetMapping("/login-info")
    public AjaxResult loginInfo(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user-code", session.getAttribute("user-code"));
        map.put("user-type", session.getAttribute("user-type"));
        return AjaxResult.success().msg("Login info, may be null").data(map);
    }
}
