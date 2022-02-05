package com.security.demo.dashboard.service;

import com.security.demo.dashboard.dto.DashboardPostDto;
import com.security.demo.dashboard.entity.DashboardPost;
import com.security.demo.dashboard.repository.DashboardPostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final DashboardPostRepository dashboardPostRepository;

    public DashboardService(DashboardPostRepository dashboardPostRepository) {
        this.dashboardPostRepository = dashboardPostRepository;
    }

    @Transactional
    public List<DashboardPostDto> findAllDesc(){
        return dashboardPostRepository.findAllDesc().stream()
                .map(DashboardPostDto::new)
                .collect(Collectors.toList());
    }


}
