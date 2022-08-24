package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.sudipta.booklender.model.entity.Loan;

import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Integer> {


    Optional<Loan> findByUserId(Integer userId);

    Optional<Loan> findByBookId(Integer bookId);

    void findByConcludedStatus(@Param("status")boolean status);
}
