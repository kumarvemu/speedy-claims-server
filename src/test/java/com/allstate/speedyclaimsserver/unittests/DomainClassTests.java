package com.allstate.speedyclaimsserver.unittests;

 import com.allstate.speedyclaimsserver.domain.Claim;
 import com.allstate.speedyclaimsserver.domain.Task;
 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainClassTests {

    @Test
    public void testEqualityForClaim() {
        //Given we have 2 claims
        Claim claim1 = new Claim();
        Claim claim2 = new Claim();

        //When both Claims have the same ID
        claim1.setId(300);
        claim2.setId(300);

        //Then the Claims should be equal
        assertEquals(claim1, claim2);
        //fail();
    }

    @Test
    public void testEqualityForTask() {
        //Given we have 2 tasks
        Task task1 = new Task();
        Task task2 = new Task();

        //When both Tasks have the same ID
        task1.setId(1000);
        task2.setId(1000);

        //Then the Tasks should be equal
        assertEquals(task1, task2);
        // fail();
    }
}
