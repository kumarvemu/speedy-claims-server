package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.domain.ClaimStatus;
import java.util.List;

public interface ClaimStatusService {
    List<ClaimStatus> getAllClaimStatuses();
    List<ClaimStatus> getAllOpenClaimStatuses(Boolean open);
    int countClaimStatuses();
    ClaimStatus getClaimStatusById(Integer id);
//    ClaimStatus getClaimStatusByDetail(String detail);
}