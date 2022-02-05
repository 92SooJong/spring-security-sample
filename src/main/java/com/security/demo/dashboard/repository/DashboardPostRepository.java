package com.security.demo.dashboard.repository;

import com.security.demo.dashboard.entity.DashboardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardPostRepository extends JpaRepository<DashboardPostRepository,Long> {

    @Query("select dp from DashboardPost dp order by dp.id")
    List<DashboardPost> findAllDesc();

}
