package com.jdk.analytics_service.controller;

import com.jdk.analytics_service.dto.ChartRequest;
import com.jdk.analytics_service.dto.ChartResponse;
import com.jdk.analytics_service.service.ChartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    // 🔹 Generate chart
    @PostMapping("/generate")
    public ChartResponse generateChart(@RequestBody ChartRequest request){

        return chartService.generateChart(request);
    }

    // 🔹 Get dataset columns for frontend
    @GetMapping("/columns/{datasetId}")
    public List<String> getColumns(@PathVariable Long datasetId){

        return chartService.getColumns(datasetId);
    }
    @PostMapping("/dashboard")
    public List<ChartResponse> generateDashboard(
            @RequestBody List<ChartRequest> requests){

        return chartService.generateDashboard(requests);
    }
}