package epicode.u2w3d5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstname;
    private String surname;
    private Role role;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
