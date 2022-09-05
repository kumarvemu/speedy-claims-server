package com.allstate.speedyclaimsserver.data;

        import com.allstate.speedyclaimsserver.domain.ClaimStatus;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus,Integer> {
    List<ClaimStatus> findAllByOpen(Boolean open);
}
