package com.allstate.speedyclaimsserver.data;


import com.allstate.speedyclaimsserver.domain.Claim;
import com.allstate.speedyclaimsserver.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
}
