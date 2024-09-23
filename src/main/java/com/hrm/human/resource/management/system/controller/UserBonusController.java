package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.AllUserBonusAddDTO;
import com.hrm.human.resource.management.system.dto.UserBonusAddRequestDTO;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.UserBonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-bonus")
@RequiredArgsConstructor
public class UserBonusController {

    private final UserBonusService userBonusService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addUserBonus(@RequestBody UserBonusAddRequestDTO userBonusRequestDTO) {
        ResponseMessage response = userBonusService.addUserBonus(userBonusRequestDTO);

        if (response.getMessage().contains("successfully")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseMessage> addUserBonusForAllEmployees(@RequestBody AllUserBonusAddDTO allUserBonusAddDTO) {
        ResponseMessage response = userBonusService.addUserBonusForAllEmployees(allUserBonusAddDTO);

        if (response.getMessage().contains("successfully")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
