package se.lexicon.sudipta.booklender.model.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {

    private long loanId;
    private LocalDate loanDate;
    private boolean concluded;
    List<LibraryUserDto> loanTaker;
    List<BookDto> books;

}
