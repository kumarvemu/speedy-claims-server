package com.allstate.speedyclaimsserver.unittests;


import com.allstate.speedyclaimsserver.control.ClaimController;
import com.allstate.speedyclaimsserver.data.*;
import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.service.BootstrapService;
import com.allstate.speedyclaimsserver.service.ClaimService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class ControllerTests {

@Autowired
    ClaimController claimController;

@MockBean
    ClaimService claimService;

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
    public void runFirst(){
        Mockito.when(claimService.countClaims())
                .thenReturn(12);

        Mockito.when(claimService.getClaimById(8))
                .thenReturn(new Claim());

        List<Claim> myClaimList = new ArrayList<Claim>();
        Mockito.when(claimService.getAllClaimsForCustomerSurname("mytestname"))
                .thenReturn(myClaimList);

        List<Claim> myPolicyList = null;
        Mockito.when(claimService.getAllClaimsForPolicyNumber("1234567890"))
            .thenReturn(myPolicyList);


}

    @Test
    public void checkThatNumberOfTransactionsIsAMapWithAKeyOfVolume(){
        Map<String, String> result = claimController.getNumberOfClaims();
        assertEquals("12", result.get("volume"));
    }

    @Test
    public void checkThatGetClaimBySurnameReturnsAClaims(){
        List<Claim> myPolicyList= claimController.getAll("mytestname",null,null);
        assertNotEquals(null, myPolicyList);
    }

    @Test
    public void checkThatGetClaimByPolicyNumnerReturnsAsNull(){
        List<Claim> myClaimList= claimController.getAll(null,"1234567890",null);
        assertEquals(null, myClaimList);
    }

    @Test
    public void checkThatGetClaimByIdReturnsAClaim(){
        Claim claim = claimController.getById(8);
        assertNotEquals(null, claim);
    }
}


