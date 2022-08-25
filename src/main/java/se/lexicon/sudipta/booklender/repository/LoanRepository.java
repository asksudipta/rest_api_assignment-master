package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sudipta.booklender.model.entity.Loan;

import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Integer> {

    //Find by userId
    Optional<Loan> findByLoanTakerUserId(Integer userId);

   //Find by bookId
    Optional<Loan>findByBookBookId(Integer bookId);

    //Find by terminated status
    Optional<Loan>findByConcluded(boolean status);

}
