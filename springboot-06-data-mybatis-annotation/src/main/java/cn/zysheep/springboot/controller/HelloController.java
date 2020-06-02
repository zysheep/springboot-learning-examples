package cn.zysheep.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from department");
        return maps.get(0);
    }

}

