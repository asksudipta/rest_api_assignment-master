package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sudipta.booklender.model.entity.Loan;

import java.util.List;


public interface LoanRepository extends CrudRepository<Loan, Integer> {

    //Find by userId
    List<Loan> findByLoanTakerUserId(Integer userId);

   //Find by bookId
    List<Loan> findByBookBookId(Integer bookId);

    //Find by terminated status
    List<Loan>findByConcluded(boolean status);

}
