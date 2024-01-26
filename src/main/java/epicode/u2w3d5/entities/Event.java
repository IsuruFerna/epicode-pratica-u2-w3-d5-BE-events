package epicode.u2w3d5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String location;
    private LocalDate date;
    private int maxOccupation;
    private String image;

    @OneToMany(mappedBy = "events")
    private List<User> users;
}
