package com.welisit.eduservice.controller;

import com.welisit.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author welisit
 * @Description TODO
 * @create 2020-06-17 0:47
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R getInfo() {
        return R.ok().data("roles", "major").data("name", "admin").data("avatar", "https://s1.ax1x.com/2020/06/17/NAAGh8.jpg");
    }
}
