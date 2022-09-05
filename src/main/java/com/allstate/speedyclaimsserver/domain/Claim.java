package com.allstate.speedyclaimsserver.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="claim_master")
public class Claim {

    //id: 101, number: "1234567890", policy_number: "156725657625", status: 1, insurance_type: "Property", created_date: "2017-01-31",
    //        claim_started_date: "2017-01-31", customer_first_name: "John", customer_surname: "Doe", estimated_claim_value: "100", claim_reason: "It broke",
    //        incident_description: "Something went wrong", affected_address: "1 Main Street, Main Town", related_claim_date: "2017-01-31", any_further_details: "None",
    //        amount_paid: "100", make: "Skoda", model: "Fabia", modelYear: "1990", animal_type: "horse", animal_breed: "big"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="policy_number")
    private String policyNumber;

    @OneToOne
    private ClaimStatus status;

    //@Column(name="insurance_type")
    @OneToOne
    private InsuranceType insuranceType;

    @Column(name="created_date")
    private LocalDate createdDate;

    @Column(name="claim_started_date")
    private LocalDate claimStartedDate;

    @Column(name="customer_first_name")
    private String customerFirstName;

    @Column(name="customer_surname")
    private String customerSurname;

    @Column(name="estimated_claim_value")
    private String estimatedClaimValue;

    @Column(name="claim_reason")
    private String claimReason;

    @Column(name="incident_description")
    private String incidentDescription;

    @Column(name="affected_address")
    private String affectedAddress;

    @Column(name="related_incident_date")
    private LocalDate relatedIncidentDate;

    @Column(name="any_further_details")
    private String anyFurtherDetails;

    @Column(name="amount_paid")
    private String amountPaid;

    @Column(name="make")
    private String make;

    @Column(name="model")
    private String model;

    @Column(name="model_year")
    private String modelYear;

    @Column(name="animal_type")
    private String animalType;

    @Column(name="animal_breed")
    private String animalBreed;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Claim() {
    }

    public Claim(Integer id, String policyNumber, ClaimStatus status, InsuranceType insuranceType, LocalDate createdDate, LocalDate claimStartedDate, String customerFirstName, String customerSurname, String estimatedClaimValue, String claimReason, String incidentDescription, String affectedAddress, LocalDate relatedClaimDate, String anyFurtherDetails, String amountPaid, String make, String model, String modelYear, String animalType, String animalBreed, List<Note> notes, List<Task> tasks) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.status = status;
        this.insuranceType = insuranceType;
        this.createdDate = createdDate;
        this.claimStartedDate = claimStartedDate;
        this.customerFirstName = customerFirstName;
        this.customerSurname = customerSurname;
        this.estimatedClaimValue = estimatedClaimValue;
        this.claimReason = claimReason;
        this.incidentDescription = incidentDescription;
        this.affectedAddress = affectedAddress;
        this.relatedIncidentDate = relatedClaimDate;
        this.anyFurtherDetails = anyFurtherDetails;
        this.amountPaid = amountPaid;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.animalType = animalType;
        this.animalBreed = animalBreed;
        this.notes = notes;
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return Objects.equals(id, claim.id) && Objects.equals(policyNumber, claim.policyNumber) && Objects.equals(status, claim.status) && Objects.equals(insuranceType, claim.insuranceType) && Objects.equals(createdDate, claim.createdDate) && Objects.equals(claimStartedDate, claim.claimStartedDate) && Objects.equals(customerFirstName, claim.customerFirstName) && Objects.equals(customerSurname, claim.customerSurname) && Objects.equals(estimatedClaimValue, claim.estimatedClaimValue) && Objects.equals(claimReason, claim.claimReason) && Objects.equals(incidentDescription, claim.incidentDescription) && Objects.equals(affectedAddress, claim.affectedAddress) && Objects.equals(relatedIncidentDate, claim.relatedIncidentDate) && Objects.equals(anyFurtherDetails, claim.anyFurtherDetails) && Objects.equals(amountPaid, claim.amountPaid) && Objects.equals(make, claim.make) && Objects.equals(model, claim.model) && Objects.equals(modelYear, claim.modelYear) && Objects.equals(animalType, claim.animalType) && Objects.equals(animalBreed, claim.animalBreed) &&  Objects.equals(notes, claim.notes) && Objects.equals(tasks, claim.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policyNumber, status, insuranceType, createdDate, claimStartedDate, customerFirstName, customerSurname, estimatedClaimValue, claimReason, incidentDescription, affectedAddress, relatedIncidentDate, anyFurtherDetails, amountPaid, make, model, modelYear, animalType, animalBreed, notes, tasks);
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", policyNumber='" + policyNumber + '\'' +
                ", status=" + status +
                ", insuranceType=" + insuranceType +
                ", createdDate=" + createdDate +
                ", claimStartedDate=" + claimStartedDate +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", estimatedClaimValue='" + estimatedClaimValue + '\'' +
                ", claimReason='" + claimReason + '\'' +
                ", incidentDescription='" + incidentDescription + '\'' +
                ", affectedAddress='" + affectedAddress + '\'' +
                ", relatedClaimDate=" + relatedIncidentDate +
                ", anyFurtherDetails='" + anyFurtherDetails + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", animalType='" + animalType + '\'' +
                ", animalBreed='" + animalBreed + '\'' +
                ", notes=" + notes +
                ", tasks=" + tasks +
                '}';
    }
}
