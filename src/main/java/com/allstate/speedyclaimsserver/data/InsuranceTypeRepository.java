package com.allstate.speedyclaimsserver.data;

        import com.allstate.speedyclaimsserver.domain.Claim;
        import com.allstate.speedyclaimsserver.domain.InsuranceType;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceTypeRepository extends JpaRepository<InsuranceType,Integer> {
}

