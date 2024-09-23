package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.BonusAddRequestDTO;
import com.hrm.human.resource.management.system.dto.BonusDTO;
import com.hrm.human.resource.management.system.entity.Bonus;
import com.hrm.human.resource.management.system.repository.BonusRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BonusService {

    private final BonusRepository bonusRepository;

    @Transactional
    public Bonus addBonusType(BonusAddRequestDTO bonusRequestDTO) {
        Bonus bonus = Bonus.builder()
                .bonusAmount(bonusRequestDTO.getBonusAmount())
                .bonusTypeName(bonusRequestDTO.getBonusTypeName())
                .build();

        return bonusRepository.save(bonus);
    }

    @Transactional(readOnly = true)
    public List<BonusDTO> getAllBonuses() {
        return bonusRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BonusDTO convertToDTO(Bonus bonus) {
        return BonusDTO.builder()
                .bonusId(bonus.getBonusId())
                .bonusAmount(bonus.getBonusAmount())
                .bonusTypeName(bonus.getBonusTypeName())
                .build();
    }


}
