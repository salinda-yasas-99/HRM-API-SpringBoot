package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.PieChartDTO;
import com.hrm.human.resource.management.system.dto.PositionPieChartDTO;
import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.Position;
import com.hrm.human.resource.management.system.entity.Role;
import com.hrm.human.resource.management.system.repository.DepartmentRepository;
import com.hrm.human.resource.management.system.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public PieChartDTO getEmployeeCountByDepartment() {
        List<Department> departments = departmentRepository.findAll();

        List<String> departmentList = departments.stream()
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());

        List<Integer> employeeCount = departments.stream()
                .map(department -> (int) department.getUsers().stream()
                        .filter(user -> user.getRole() == Role.EMPLOYEE)
                        .count())
                .collect(Collectors.toList());

        return PieChartDTO.builder()
                .departmentList(departmentList)
                .employeeCount(employeeCount)
                .build();
    }

    public PositionPieChartDTO getEmployeeCountByPosition() {
        List<Position> positions = positionRepository.findAll();

        List<String> positionList = positions.stream()
                .map(Position::getPositionName)
                .collect(Collectors.toList());

        List<Integer> employeeCount = positions.stream()
                .map(position -> (int) position.getUsers().stream()
                        .filter(user -> user.getRole() == Role.EMPLOYEE)
                        .count())
                .collect(Collectors.toList());

        return PositionPieChartDTO.builder()
                .positionList(positionList)
                .employeeCount(employeeCount)
                .build();
    }
}
