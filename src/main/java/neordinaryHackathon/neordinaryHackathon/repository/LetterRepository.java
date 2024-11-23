package neordinaryHackathon.neordinaryHackathon.repository;

import neordinaryHackathon.neordinaryHackathon.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
