package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.Note;
import com.allstate.speedyclaimsserver.dto.NewClaimDTO;

import java.util.List;
import java.util.Map;

public interface ClaimService {
    List<Claim> getAllClaims();
    int countClaims();
    List<Claim> getAllClaimsForCustomerSurname(String surname);
    List<Claim> getAllClaimsForPolicyNumber(String policyNumber);
    List<Claim> getAllOpenClaims(Boolean open);
    Claim getClaimById(Integer id);
    Claim add(NewClaimDTO creditCardTransaction);
    Claim update(Map<String, String> data, Integer id);
    List <Note> addNote(Map<String, String> noteDetail, Integer claimId);

}
