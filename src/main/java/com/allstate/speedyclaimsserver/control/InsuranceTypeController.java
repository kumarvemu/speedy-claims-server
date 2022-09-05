package com.allstate.speedyclaimsserver.control;

import com.allstate.speedyclaimsserver.domain.InsuranceType;
import com.allstate.speedyclaimsserver.service.InsuranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/insurance-type")
public class InsuranceTypeController {

    @Autowired
    private InsuranceTypeService insuranceTypeService;

    @GetMapping()
    public List<InsuranceType> getAll() {
        return insuranceTypeService.getAllInsuranceTypes();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public InsuranceType getById(@PathVariable(value = "id", required = true) Integer id) {
        return insuranceTypeService.getInsuranceTypeById(id);
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfInsuranceTypes() {
        Integer volume = insuranceTypeService.countInsuranceTypes();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }
}
