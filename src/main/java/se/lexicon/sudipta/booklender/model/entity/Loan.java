package se.lexicon.sudipta.booklender.model.entity;

import lombok.*;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    @ManyToOne
    private LibraryUser loanTaker;
    @ManyToOne
    private Book book;
    private LocalDate loanDate;
    private boolean concluded;

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean concluded) {
        this.loanDate = loanDate;
        setLoanTaker(loanTaker);
        setBook(book);
        setConcluded(concluded);
    }

    public Loan(long loanId) {
        this.loanId = loanId;
    }

    public void setBook(Book book){

        if(book==null)throw new IllegalArgumentException("Book has null value");

        if(!book.isAvailable())throw new IllegalIdentifierException("Book is not available");
         book.setAvailable(false);
         this.book=book;
    }
    public void setLoanTaker(LibraryUser loanTaker) {
        if(loanTaker==null)throw new IllegalArgumentException("Loantaker has null value");
        this.loanTaker = loanTaker;
    }


    public boolean isOverDue(){
        LocalDate dueDate = loanDate.plusDays(book.getMaxLoanDays());
        return LocalDate.now().isAfter(dueDate);
    }

    public BigDecimal getFine(){

        Period period = Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());
        int daysOverdue = period.getDays();
        BigDecimal fine = BigDecimal.ZERO;
        if (daysOverdue > 0)
            fine = BigDecimal.valueOf(daysOverdue * book.getFinePerDays().longValue());
        return fine;
    }

    public boolean extendLoan(int days) {
        if (book.isReserved() || isOverDue()) return false;
        if (days > book.getMaxLoanDays()) return false;

        setLoanDate(getLoanDate().plusDays(days));
        return true;
    }
}
