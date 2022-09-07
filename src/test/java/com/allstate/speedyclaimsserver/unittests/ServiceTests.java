package com.allstate.speedyclaimsserver.unittests;

import com.allstate.speedyclaimsserver.data.*;
import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.ClaimStatus;
import com.allstate.speedyclaimsserver.domain.InsuranceType;
import com.allstate.speedyclaimsserver.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class}) //exclude database
public class ServiceTests {
    @Autowired
    ClaimStatusService claimStatusService;

    @Autowired
    ClaimService claimService;

    @Autowired
    InsuranceTypeService insuranceTypeService;

    @Autowired
    TaskStatusService taskStatusService;

    @MockBean
    ClaimRepository claimRepository;

    @MockBean
    ClaimStatusRepository claimStatusRepository;

    @MockBean
    InsuranceTypeRepository insuranceTypeRepository;

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    TaskStatusRepository taskStatusRepository;

    @MockBean
    NoteRepository noteRepository;

    @MockBean
    BootstrapService bootstrapService;


    @BeforeEach
    public void runFirst() {

        ClaimStatus claimStatus = new ClaimStatus(1, "Awaiting Assessment", true);
        InsuranceType claimInsType = new InsuranceType(1, 1, "Property");

        List<Claim> claims = new ArrayList<>();

        Claim claim1 = new Claim(1, "1111111112", claimStatus, claimInsType, LocalDate.parse("2021-09-30"),
                LocalDate.parse("2021-09-30"),"Julie", "Scott", "55", "Breakdown",
                "Breakdown", "Alfred Street", LocalDate.parse("2021-09-30"), "na",
                "55", "Audi", "A3", "2016", "", "", null, null);

        Claim claim2 = new Claim(2, "1111111113", claimStatus, claimInsType, LocalDate.parse("2021-09-30"),
                LocalDate.parse("2021-09-30"),"Julie", "Scott", "55", "Breakdown",
                "Breakdown", "Alfred Street", LocalDate.parse("2021-09-30"), "na",
                "55", "Audi", "A3", "2016", "", "", null, null);

        Claim claim3 = new Claim(3, "1111111114", claimStatus, claimInsType, LocalDate.parse("2021-09-30"),
                LocalDate.parse("2021-09-30"),"Julie", "Scott", "55", "Breakdown",
                "Breakdown", "Alfred Street", LocalDate.parse("2021-09-30"), "na",
                "55", "Audi", "A3", "2016", "", "", null, null);

               claims.add(claim1);
               claims.add(claim2);
               claims.add(claim3);

        Mockito.when(claimRepository.findAll())
                .thenReturn(claims);

        Mockito.when(claimStatusRepository.findById(1))
                .thenReturn(Optional.of(claimStatus));
    }

    @Test
    public void checkThatTheCorrectClaimStatusIsBeingReturned(){
        ClaimStatus claimStatus = claimStatusService.getClaimStatusById(1);
        assertEquals(1,claimStatus.getId());
        //fail();
    }

    @Test
    public void checkThatCountClaimsIsCountingTheRightNumber() {
        int count = claimService.countClaims();
        assertEquals(3, count);
        //fail();
    }
}