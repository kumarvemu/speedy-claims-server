package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.data.InsuranceTypeRepository;
import com.allstate.speedyclaimsserver.domain.InsuranceType;
import com.allstate.speedyclaimsserver.exception.InsuranceTypeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceTypeServiceImpl implements InsuranceTypeService {

    @Autowired
    InsuranceTypeRepository insuranceTypeRepository;

    Logger logger = LoggerFactory.getLogger(InsuranceTypeService.class);
    private String errorMessage;

    @Override
    public List<InsuranceType> getAllInsuranceTypes() {
        return insuranceTypeRepository.findAll();
    }

    @Override
    public int countInsuranceTypes() {
        return insuranceTypeRepository.findAll().size();
    }

    @Override
    public InsuranceType getInsuranceTypeById(Integer id) {
        Optional<InsuranceType> optionalInsuranceType = insuranceTypeRepository.findById(id);
        if (optionalInsuranceType.isPresent()) {
            return optionalInsuranceType.get();
        }
        errorMessage = "No Insurance Type with the ID " + id;
        logger.error(errorMessage);
        throw new InsuranceTypeNotFoundException(errorMessage);
    }
}

