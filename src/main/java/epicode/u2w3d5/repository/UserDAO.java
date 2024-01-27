package epicode.u2w3d5.repository;

import epicode.u2w3d5.entities.Event;
import epicode.u2w3d5.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

//    List<Event> getUserEvents(User user);


    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId")
    List<Event> getUserEventsByEventId(@Param("userId") UUID userId);

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM userEvent ue WHERE ue.user.id = :userId AND ue.event.id = :eventId")
//    void deleteUserEventByEventId(@Param("userId") UUID userId, @Param("eventId") UUID eventId);
}


// confilct with userEvents