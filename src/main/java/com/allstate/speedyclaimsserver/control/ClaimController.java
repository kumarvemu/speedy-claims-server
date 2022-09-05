package com.allstate.speedyclaimsserver.control;
import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.Note;
import com.allstate.speedyclaimsserver.domain.Task;
import com.allstate.speedyclaimsserver.dto.NewClaimDTO;
import com.allstate.speedyclaimsserver.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping()
    public List<Claim> getAll(@RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "policyNumber", required = false) String policyNumber,
                              @RequestParam(value = "open", required = false) String open) {
        if (surname != null) {
            return claimService.getAllClaimsForCustomerSurname(surname);
        }
        if (policyNumber != null) {
            return claimService.getAllClaimsForPolicyNumber(policyNumber);
        }
        if (open != null) {
            return claimService.getAllOpenClaims(Boolean.parseBoolean(open));
        }
        return claimService.getAllClaims();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Claim getById(@PathVariable(value = "id", required = true) Integer id) {
        return claimService.getClaimById(id);
    }

    @GetMapping("/volume")
    public Map<String, String> getNumberOfClaims() {
        Integer volume = claimService.countClaims();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }

    @PostMapping()
    public Claim addClaim(@RequestBody NewClaimDTO newClaim) {
        System.out.println(newClaim.toString());
        return claimService.add(newClaim);
    }

    @PutMapping(value = "/{id}")
    public Claim updateClaim(@RequestBody Map<String, String> data, @PathVariable(value = "id", required = true) Integer id) {
        return claimService.update(data, id);
    }

    //Notes
    @GetMapping(value = "/{id}/note", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Note> getNotesByClaimId(@PathVariable(value = "id", required = true) Integer id) {
        return claimService.getClaimById(id).getNotes();
    }

    //TODO - need to check how to return and update one note.
    @PostMapping(value = "/{claim-id}/note")
    public List<Note> addClaimNote(@RequestBody Map<String, String> noteDetail, @PathVariable(value = "claim-id", required = true) Integer claimId) {
        return claimService.addNote(noteDetail, claimId);
    }

    //Tasks
    @GetMapping(value = "/{id}/task", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Task> getTasksByClaimId(@PathVariable(value = "id", required = true) Integer id) {
        return claimService.getClaimById(id).getTasks();
    }


}

