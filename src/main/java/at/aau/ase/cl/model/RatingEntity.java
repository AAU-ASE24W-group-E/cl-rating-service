package at.aau.ase.cl.model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ratings")
public class RatingEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name = "rating_username", nullable = false, unique = true)
    public String rating_username;

    @Column(name = "rated_username", nullable = false, unique = true)
    public String rated_username;

    @Column(name = "rating", nullable = false, unique = false)
    public Integer rating;
}