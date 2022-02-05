package com.security.demo.dashboard.controller;

import com.security.demo.dashboard.dto.DashboardPostDto;
import com.security.demo.dashboard.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("dashboard")
    public String getDashboard(Model model){
        // service를 통해 model을 모두 불러온다.
        model.addAttribute("posts" , dashboardService.findAllDesc());
        return "/dashboard/dashboard";
    }
}
