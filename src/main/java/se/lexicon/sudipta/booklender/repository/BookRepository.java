package se.lexicon.sudipta.booklender.repository;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.sudipta.booklender.model.entity.Book;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByTitle(String title);

    //Find by reserved status
    List<Book> findByReserved(boolean status);
    //Find by available status
    List<Book> findByAvailable(boolean status);

}
