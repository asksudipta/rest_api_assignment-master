package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.sudipta.booklender.model.entity.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByReservedStatus(boolean status);
    Optional<Book> findByAvailableStatus(boolean status);

}
