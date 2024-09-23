package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.PieChartDTO;
import com.hrm.human.resource.management.system.dto.PositionPieChartDTO;
import com.hrm.human.resource.management.system.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Tag(name ="Dashboard Endpoints")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/department/employee-count")
    @Operation(
            description = "Get employee count",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse (
                        description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse (
                            description = "Unathrized /token invalid",
                            responseCode = "403"
                    )
            }

    )
    public PieChartDTO getEmployeeCountByDepartment() {
        return dashboardService.getEmployeeCountByDepartment();
    }

    @GetMapping("/position/employee-count")
    public PositionPieChartDTO getEmployeeCountByPosition() {
        return dashboardService.getEmployeeCountByPosition();
    }
}
