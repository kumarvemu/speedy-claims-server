package com.allstate.speedyclaimsserver.dto;


import com.allstate.speedyclaimsserver.domain.*;
//import com.allstate.speedyclaimsserver.exception.InvalidInsuranceTypeException;
//import com.allstate.speedyclaimsserver.service.ClaimStatusService;
//import com.allstate.speedyclaimsserver.service.InsuranceTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

public class NewClaimDTO {
    private String policyNumber;
    private String insuranceType;
    private LocalDate claimStartedDate;
    private String customerFirstName;
    private String customerSurname;
    private String estimatedClaimValue;
    private String claimReason;
    private String incidentDescription;
    private String affectedAddress;
    private String make;
    private String model;
    private String modelYear;
    private String animalType;
    private String animalBreed;
    private LocalDate relatedIncidentDate;
    private String anyFurtherDetails;

    public NewClaimDTO() {
    }

    public Claim toClaim() {
        Claim claim = new Claim(null, policyNumber, null, null, LocalDate.now(), claimStartedDate, customerFirstName, customerSurname, estimatedClaimValue,
                claimReason, incidentDescription, null, relatedIncidentDate, anyFurtherDetails, null, null, null, null, null, null, null, null);
        return claim;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getClaimStartedDate() {
        return claimStartedDate;
    }

    public void setClaimStartedDate(LocalDate claimStartedDate) {
        this.claimStartedDate = claimStartedDate;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getEstimatedClaimValue() {
        return estimatedClaimValue;
    }

    public void setEstimatedClaimValue(String estimatedClaimValue) {
        this.estimatedClaimValue = estimatedClaimValue;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getAffectedAddress() {
        return affectedAddress;
    }

    public void setAffectedAddress(String affectedAddress) {
        this.affectedAddress = affectedAddress;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public void setAnimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public LocalDate getRelatedIncidentDate() {
        return relatedIncidentDate;
    }

    public void setRelatedIncidentDate(LocalDate relatedIncidentDate) {
        this.relatedIncidentDate = relatedIncidentDate;
    }

    public String getAnyFurtherDetails() {
        return anyFurtherDetails;
    }

    public void setAnyFurtherDetails(String anyFurtherDetails) {
        this.anyFurtherDetails = anyFurtherDetails;
    }

    @Override
    public String toString() {
        return "NewClaimDTO{" +
                " policyNumber='" + policyNumber + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", claimStartedDate=" + claimStartedDate +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", estimatedClaimValue='" + estimatedClaimValue + '\'' +
                ", claimReason='" + claimReason + '\'' +
                ", incidentDescription='" + incidentDescription + '\'' +
                ", affectedAddress='" + affectedAddress + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", animalType='" + animalType + '\'' +
                ", animalBreed='" + animalBreed + '\'' +
                ", relatedIncidentDate=" + relatedIncidentDate +
                ", anyFurtherDetails='" + anyFurtherDetails + '\'' +
                '}';
    }
}

