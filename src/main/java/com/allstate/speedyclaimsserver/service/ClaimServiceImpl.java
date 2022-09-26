package com.allstate.speedyclaimsserver.service;

import com.allstate.speedyclaimsserver.data.ClaimRepository;
import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.Note;
import com.allstate.speedyclaimsserver.dto.NewClaimDTO;
import com.allstate.speedyclaimsserver.exception.ClaimNotFoundException;
import com.allstate.speedyclaimsserver.exception.InvalidInsuranceTypeException;
import com.allstate.speedyclaimsserver.exception.InvalidNewClaimException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    InsuranceTypeService insuranceTypeService;

    @Autowired
    ClaimStatusService claimStatusService;

    Logger logger = LoggerFactory.getLogger(ClaimService.class);
    private String errorMessage;

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public int countClaims() {
        return claimRepository.findAll().size();
    }

    @Override
    public List<Claim> getAllClaimsForCustomerSurname(String surname) {
        return claimRepository.findAllByCustomerSurname(surname);
    }

    @Override
    public List<Claim> getAllClaimsForPolicyNumber(String policyNumber) {
        return claimRepository.findAllByPolicyNumber(policyNumber);
    }

    @Override
    public List<Claim> getAllOpenClaims(Boolean open) {
        return claimRepository.findAllByStatusOpen(open);
    }

    @Override
    public Claim getClaimById(Integer id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            return optionalClaim.get();
        }
        errorMessage = "No Claim with the ID " + id;
        logger.info(errorMessage);
        throw new ClaimNotFoundException(errorMessage);
    }

    @Override
    public Claim add(NewClaimDTO newClaimDTO) {
        Claim claim = newClaimDTO.toClaim();
        if (claim.getPolicyNumber() == null || newClaimDTO.getInsuranceType() == null || claim.getCustomerFirstName() == null
                || claim.getCustomerSurname() == null || claim.getClaimStartedDate() == null || claim.getEstimatedClaimValue() == null
                || claim.getClaimReason() == null || claim.getIncidentDescription() == null) {
            errorMessage = "Policy Number, Insurance Type, Customer First name and Surname, Claim Started Date, " +
                    "Estimate Claim Value, Claim Reason and Incident Description are all must be provided.";
            logger.error(errorMessage);
            throw new InvalidNewClaimException(errorMessage);
        } else {
            //Find status - use 1st status for new Claim
            claim.setStatus(claimStatusService.getClaimStatusById(1));

            //Find insurance type
            claim.setInsuranceType(insuranceTypeService.getInsuranceTypeById(Integer.parseInt(newClaimDTO.getInsuranceType())));

            //Check insurance type and set fields depending on type
            switch (claim.getInsuranceType().getDetail()) {
                case "Property":
                    claim.setAffectedAddress(newClaimDTO.getAffectedAddress());
                    break;
                case "Motor":
                    claim.setMake(newClaimDTO.getMake());
                    claim.setModel(newClaimDTO.getModel());
                    claim.setModelYear(newClaimDTO.getModelYear());
                    break;
                case "Pet":
                    claim.setAnimalBreed(newClaimDTO.getAnimalBreed());
                    claim.setAnimalType(newClaimDTO.getAnimalType());
                    break;
                default:
                    //invalid insurance Type found exception
                    throw new InvalidInsuranceTypeException("Invalid Insurance Type '" + claim.getInsuranceType().getDetail() + "' selected when creating a new claim");
            }


            if (claim.getInsuranceType().getDetail() == "Property" && claim.getAffectedAddress() == null) {
                errorMessage = "Affected address must be provided for a Property claim.";
                logger.error(errorMessage);
                throw new InvalidNewClaimException(errorMessage);
            }
            if (claim.getInsuranceType().getDetail() == "Motor" && (claim.getMake() == null || claim.getModel() == null || claim.getModelYear() == null)) {
                errorMessage = "Make, Model and Year must be provided for a Motor claim.";
                logger.error(errorMessage);
                throw new InvalidNewClaimException(errorMessage);
            }
            if (claim.getInsuranceType().getDetail() == "Pet" && (claim.getAnimalType() == null || claim.getAnimalBreed() == null)) {
                errorMessage = "Animal Type and Breed must be provided for a Pet claim.";
                logger.error(errorMessage);
                throw new InvalidNewClaimException(errorMessage);
            }
        }
        try {
            return claimRepository.save(claim);
        } catch (Exception e) {
            errorMessage = "Failure when adding Claim " + e;
            logger.error(errorMessage);
            throw new InvalidNewClaimException(errorMessage);
        }
    }

    @Override
    public Claim update(Map<String, String> data, Integer id) {
        Claim claim = getClaimById(id);

        if (data.containsKey("policyNumber")) claim.setPolicyNumber(data.get("policyNumber"));
        if (data.containsKey("status")) {
            claim.setStatus(claimStatusService.getClaimStatusById(Integer.parseInt(data.get("status"))));
        }
        if (data.containsKey("insuranceType")) {
            claim.setInsuranceType(insuranceTypeService.getInsuranceTypeById(Integer.parseInt(data.get("insuranceType"))));
        }
        if (data.containsKey("claimStartedDate"))
            claim.setClaimStartedDate(LocalDate.parse(data.get("claimStartedDate")));
        if (data.containsKey("customerFirstName")) claim.setCustomerFirstName(data.get("customerFirstName"));
        if (data.containsKey("customerSurname")) claim.setCustomerSurname(data.get("customerSurname"));
        if (data.containsKey("estimatedClaimValue")) claim.setEstimatedClaimValue(data.get("estimatedClaimValue"));
        if (data.containsKey("claimReason")) claim.setClaimReason(data.get("claimReason"));
        if (data.containsKey("incidentDescription")) claim.setIncidentDescription(data.get("incidentDescription"));
        if (data.containsKey("affectedAddress")) claim.setAffectedAddress(data.get("affectedAddress"));
        if (data.containsKey("relatedIncidentDate"))
            claim.setRelatedIncidentDate(LocalDate.parse(data.get("relatedIncidentDate")));
        if (data.containsKey("anyFurtherDetails")) claim.setAnyFurtherDetails(data.get("anyFurtherDetails"));
        if (data.containsKey("amountPaid")) claim.setAmountPaid(data.get("amountPaid"));
        if (data.containsKey("make")) claim.setMake(data.get("make"));
        if (data.containsKey("model")) claim.setModel(data.get("model"));
        if (data.containsKey("modelYear")) claim.setModelYear(data.get("modelYear"));
        if (data.containsKey("animalType")) claim.setAnimalType(data.get("animalType"));
        if (data.containsKey("animalBreed")) claim.setAnimalBreed(data.get("animalBreed"));

        return claimRepository.save(claim);
    }

    //TODO! Check how to return last record
    @Override
    public List<Note> addNote(Map<String, String> noteDetail, Integer claimId) {
        Claim currentClaim = getClaimById(claimId);

        Note newNote = new Note(null, noteDetail.get("detail"), LocalDate.now(), currentClaim);
        currentClaim.getNotes().add(newNote);
        System.out.println("Note Detail" + noteDetail.get("detail"));
        System.out.println("Note Detail get " + noteDetail.get("detail"));
        System.out.println("Claim ID " + claimId);
        Claim updatedClaim = claimRepository.save(currentClaim);
        //System.out.println("Get Notes" + updatedClaim);
        return updatedClaim.getNotes();
    }


}
