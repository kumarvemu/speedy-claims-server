package com.allstate.speedyclaimsserver.integrationTests;


import static org.mockito.Mockito.*;
import com.allstate.speedyclaimsserver.data.*;
import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.ClaimStatus;
import com.allstate.speedyclaimsserver.domain.InsuranceType;
import com.allstate.speedyclaimsserver.dto.NewClaimDTO;
import com.allstate.speedyclaimsserver.service.BootstrapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class}) //exclude database
@AutoConfigureMockMvc
public class ClaimsTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ClaimRepository claimRepository;

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

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        ClaimStatus claimStatus = new ClaimStatus(1, "Awaiting Assessment", true);

        Mockito.when(claimStatusRepository.findById(1))
                .thenReturn(Optional.of(claimStatus));

        InsuranceType claimInsType = new InsuranceType(1, 1, "Property");

        Mockito.when(insuranceTypeRepository.findById(1))
                .thenReturn(Optional.of(claimInsType));
    }

   @Test
    public void checkNewClaimsAreAddedToDatabase() throws Exception {
        NewClaimDTO newClaim = new NewClaimDTO();
        newClaim.setPolicyNumber("1111111116");
        newClaim.setClaimReason("brokedown");
        newClaim.setClaimStartedDate(LocalDate.parse("2021-09-30"));
        newClaim.setAffectedAddress("1 AlfredStreet");
        newClaim.setCustomerFirstName("ciara");
        newClaim.setCustomerSurname("Luke");
        newClaim.setIncidentDescription("Theft");
        newClaim.setInsuranceType("1");
        newClaim.setEstimatedClaimValue("200.44");

        ObjectMapper objectMapper = mapperBuilder.build();
        String json = objectMapper.writeValueAsString(newClaim);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        Claim databaseResponse = newClaim.toClaim();
        databaseResponse.setId(1);

        Mockito.when(claimRepository.save(any())).thenReturn(databaseResponse);

        ResultActions result = mockMvc.perform(request)
                .andExpect(status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(databaseResponse);
        JsonContentAssert jsonAssert = new JsonContentAssert(Claim.class, expectedJson);
        jsonAssert.isEqualToJson(responseJson);

        ArgumentCaptor<Claim> capturedTransaction =
                ArgumentCaptor.forClass(Claim.class);

        Mockito.verify(claimRepository)
                .save(capturedTransaction.capture());

        Claim currentClaim = capturedTransaction.getValue();

        assertEquals("ciara", currentClaim.getCustomerFirstName());
    }
}
