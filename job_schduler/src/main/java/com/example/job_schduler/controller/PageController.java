package com.example.job_schduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/to_delete")
    public ModelAndView deletePage() {
        return new ModelAndView("page/deletejob.html");
    }
}
