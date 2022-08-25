package se.lexicon.sudipta.booklender.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {

    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDays;
    private String description;
}
