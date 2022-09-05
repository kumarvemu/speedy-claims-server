package com.allstate.speedyclaimsserver.service;


import com.allstate.speedyclaimsserver.data.*;
import com.allstate.speedyclaimsserver.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BootstrapService {
    @Autowired
    InsuranceTypeService insuranceTypeService;

    @Autowired
    ClaimStatusService claimStatusService;

    @Autowired
    TaskStatusService taskStatusService;

    @Autowired
    ClaimService claimService;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    ClaimStatusRepository claimStatusRepository;

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @PostConstruct
    public void initializeData() {

        if (insuranceTypeService.countInsuranceTypes() == 0) {
            //    { id: 101, value: 1, type: "Property" },
            //    { id: 102, value: 2, type: "Motor" },
            //    { id: 103, value: 3, type: "Pet" },
            insuranceTypeRepository.save(new InsuranceType(null, 1, "Property"));
            insuranceTypeRepository.save(new InsuranceType(null, 2, "Motor"));
            insuranceTypeRepository.save(new InsuranceType(null, 3, "Pet"));
        }

        if (claimStatusService.countClaimStatuses() == 0) {
//            { id: 1, open: true, detail: "Awaiting Assessment" },
//            { id: 2, open: true, detail: "In Progress" },
//            { id: 3, open: true, detail: "Awaiting Payment" },
//            { id: 4, open: false, detail: "Accepted & Paid" },
//            { id: 5, open: false, detail: "Transferred to Main Claims" },
//            { id: 6, open: false, detail: "Rejected" }
            claimStatusRepository.save(new ClaimStatus(null, "Awaiting Assessment", true));
            claimStatusRepository.save(new ClaimStatus(null, "In Progress", true));
            claimStatusRepository.save(new ClaimStatus(null, "Awaiting Payment", true));
            claimStatusRepository.save(new ClaimStatus(null, "Accepted & Paid", false));
            claimStatusRepository.save(new ClaimStatus(null, "Transferred to Main Claims", false));
            claimStatusRepository.save(new ClaimStatus(null, "Rejected", false));
        }


        if (taskStatusService.countTaskStatuses() == 0) {
//            { id: 101, value: 1, detail: "Open" },
//            { id: 102, value: 2, detail: "Closed" }
            taskStatusRepository.save(new TaskStatus(null, 1, "Open"));
            taskStatusRepository.save(new TaskStatus(null, 2, "Closed"));
        }

//        {
//            id: 101, number: "1234567890", policy_number: "156725657625", status: 1, insurance_type: "Property", created_date: "2017-01-31",
//                claim_started_date: "2017-01-31", customer_first_name: "John", customer_surname: "Doe", estimated_claim_value: "100", claim_reason: "It broke",
//                incident_description: "Something went wrong", affected_address: "1 Main Street, Main Town", related_claim_date: "2017-01-31", any_further_details: "None",
//                amount_paid: "100", make: "Skoda", model: "Fabia", modelYear: "1990", animal_type: "horse", animal_breed: "big"
//        },
//        {
//            id: 102, number: "9234567890", policy_number: "156725657625", status: 6, insurance_type: "Motor", created_date: "2017-01-31",
//                claim_started_date: "2017-01-31", customer_first_name: "Tom", customer_surname: "Doe", estimated_claim_value: "100", claim_reason: "It broke",
//                incident_description: "Something went wrong", affected_address: "1 Main Street, Main Town", related_claim_date: "2017-01-31", any_further_details: "None",
//                amount_paid: "100", make: "Skoda", model: "Fabia", modelYear: "1990", animal_type: "horse", animal_breed: "big"
//        },
//        {
//            id: 103, number: "5555678901", policy_number: "987625657625", status: 2, insurance_type: "Motor", created_date: "2017-01-31",
//                claim_started_date: "2017-01-31", customer_first_name: "Mary", customer_surname: "Jones", estimated_claim_value: "100", claim_reason: "It broke",
//                incident_description: "Something went wrong", affected_address: "1 Main Street, Main Town", related_claim_date: "2017-01-31", any_further_details: "None",
//                amount_paid: "100", make: "Skoda", model: "Fabia", modelYear: "1990", animal_type: "", animal_breed: ""
//        },

        if (claimService.countClaims() == 0) {
            ClaimStatus claimStatus1 = claimStatusService.getClaimStatusById(1);
            InsuranceType claimInsType = insuranceTypeService.getInsuranceTypeById(1);
            TaskStatus task1status = taskStatusService.getTaskStatusById(1);

            Claim claim1 = new Claim(null, "9876256576", claimStatus1, claimInsType, LocalDate.parse("2017-01-31"),
                    LocalDate.parse("2017-01-31"),"Mary", "Jones", "100", "It broke",
                    "Something went wrong", "1 Main Street, Main Town", LocalDate.parse("2017-01-31"), "None",
                    "100", "Skoda", "Fabia", "1990", "", "", null, null);

            Note note1 = new Note(null, "Everything is good", LocalDate.now(), claim1);
            Task task1 = new Task(null, claim1, "Do something", task1status, LocalDate.parse("2017-01-31"));

            List<Note> notes = new ArrayList<>();
            notes.add(note1);
            claim1.setNotes(notes);

            List<Task> tasks = new ArrayList<>();
            tasks.add(task1);
            claim1.setTasks(tasks);

            claimRepository.save(claim1);

        }

    }

}
