package epicode.u2w3d5.repository;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository <Event, UUID> {
    Optional<Event> findByTitle(String title);

    List<Event> findEventsByUsers(User user);

    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId")
    List<Event> userEvents(@Param("userId") UUID userId);

}
