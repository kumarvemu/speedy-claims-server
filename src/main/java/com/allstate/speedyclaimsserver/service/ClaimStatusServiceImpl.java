package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.data.ClaimStatusRepository;
import com.allstate.speedyclaimsserver.domain.ClaimStatus;
import com.allstate.speedyclaimsserver.exception.ClaimStatusNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

    @Autowired
    ClaimStatusRepository claimStatusRepository;

    Logger logger = LoggerFactory.getLogger(ClaimStatusService.class);
    private String errorMessage;

    @Override
    public List<ClaimStatus> getAllClaimStatuses() {
        return claimStatusRepository.findAll();
    }

    @Override
    public List<ClaimStatus> getAllOpenClaimStatuses(Boolean open) {
        return claimStatusRepository.findAllByOpen(open);
    }

    @Override
    public int countClaimStatuses() {
        return claimStatusRepository.findAll().size();
    }

    @Override
    public ClaimStatus getClaimStatusById(Integer id) {
        Optional<ClaimStatus> optionalClaimStatus = claimStatusRepository.findById(id);
        if (optionalClaimStatus.isPresent()) {
            return optionalClaimStatus.get();
        }
        errorMessage = "No Claim Status with the ID " + id;
        logger.error(errorMessage);
        throw new ClaimStatusNotFoundException(errorMessage);
    }
}
