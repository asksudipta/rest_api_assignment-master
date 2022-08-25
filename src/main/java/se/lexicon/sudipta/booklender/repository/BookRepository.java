package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.sudipta.booklender.model.entity.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

    //Find by reserved status
    Optional<Book> findByReserved(boolean status);
    //Find by available status
    Optional<Book> findByAvailable(boolean status);

}
