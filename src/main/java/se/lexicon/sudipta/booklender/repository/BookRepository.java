package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.sudipta.booklender.model.entity.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {


    Optional<Book> findByTitle(String title);

    void findByReservedStatus(@Param("status")boolean status);

    void findByAvailableStatus(@Param("status")boolean status);
}
