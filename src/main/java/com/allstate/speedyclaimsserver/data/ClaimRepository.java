package com.allstate.speedyclaimsserver.data;

        import com.allstate.speedyclaimsserver.domain.Claim;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {
    List<Claim> findAllByCustomerSurname(String surname);
    List<Claim> findAllByPolicyNumber(String policyNumber);
    List<Claim> findAllByStatusOpen(Boolean open);
}

