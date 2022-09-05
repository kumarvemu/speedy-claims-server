package com.allstate.speedyclaimsserver.control;

        import com.allstate.speedyclaimsserver.domain.ClaimStatus;
        import com.allstate.speedyclaimsserver.service.ClaimStatusService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.*;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/claim-status")
public class ClaimStatusController {

    @Autowired
    private ClaimStatusService claimStatusService;

    @GetMapping()
    public List<ClaimStatus> getAll() {
        return claimStatusService.getAllClaimStatuses();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ClaimStatus getById(@PathVariable(value = "id", required = true) Integer id) {
        return claimStatusService.getClaimStatusById(id);
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfClaimStatuses() {
        Integer volume = claimStatusService.countClaimStatuses();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }
}
