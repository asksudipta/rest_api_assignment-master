package se.lexicon.sudipta.booklender.service;

import org.modelmapper.ModelMapper;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
import se.lexicon.sudipta.booklender.repository.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService{

    //Inject Dependency Injection

    BookRepository bookRepository;
    ModelMapper Modelmapper;



    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return null;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public List<BookDto> findByTitle(String title) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public BookDto findById(Integer bookId) throws ObjectNotFoundException {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto create(BookDto dto) throws ObjectDuplicateException {
        return null;
    }

    @Override
    public void update(BookDto bookDto) {

    }

    @Override
    public void delete(Integer bookId) throws ObjectNotFoundException {

    }
}
