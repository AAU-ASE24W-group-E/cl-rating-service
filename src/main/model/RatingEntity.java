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

    @Column(name = "reader_username", nullable = false, unique = true)
    public String reader_username;

    @Column(name = "owner_username", nullable = false, unique = true)
    public String owner_username;

    @Column(name = "rating", nullable = true, unique = false)
    public Integer rating;
}