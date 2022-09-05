package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.domain.InsuranceType;
import java.util.List;

public interface InsuranceTypeService {
    List<InsuranceType> getAllInsuranceTypes();
    int countInsuranceTypes();
    InsuranceType getInsuranceTypeById(Integer id);
//    InsuranceType getInsuranceTypeByDetail(String detail);
}

