package neordinaryHackathon.neordinaryHackathon.repository;

import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findByName(String name);

}
