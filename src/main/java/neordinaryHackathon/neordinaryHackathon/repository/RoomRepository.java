package neordinaryHackathon.neordinaryHackathon.repository;

import neordinaryHackathon.neordinaryHackathon.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
