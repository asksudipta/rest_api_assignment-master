package se.lexicon.sudipta.booklender.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class LibraryUserDto {

    private int userId;
    private LocalDate regDate;
    private String name;
    private String email;

}
