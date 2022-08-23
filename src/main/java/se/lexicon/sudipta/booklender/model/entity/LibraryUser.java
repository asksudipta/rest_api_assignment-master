package se.lexicon.sudipta.booklender.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class LibraryUser {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false)
    private LocalDate regDate;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    public LibraryUser(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        setName(name);
        setEmail(email);
    }




}
