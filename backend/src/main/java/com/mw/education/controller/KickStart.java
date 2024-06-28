package com.mw.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器类，用于处理HTTP请求并返回响应。
 */
@RestController
public class KickStart {

    /**
     * 处理根路径和/index路径的GET请求。
     * 返回当前时间戳和一个映射，以便演示JSON的生成。
     *
     * @return 包含当前时间的映射对象
     */
    @GetMapping(path = {"/", "/index"})
    @ResponseBody
    public Map testJson() {
        // 创建一个映射对象，用于存储要返回的数据
        Map map = new HashMap();
        // 将当前时间放入映射中
        map.put("time", LocalDateTime.now());
        // 返回填充好的映射对象
        return map;
    }
}
