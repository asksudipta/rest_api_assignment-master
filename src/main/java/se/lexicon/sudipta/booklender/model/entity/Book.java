package se.lexicon.sudipta.booklender.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(nullable = false)
    private  String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDays;
    private String description;

    public Book(String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDays, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDays = finePerDays;
        this.description = description;
    }
}
