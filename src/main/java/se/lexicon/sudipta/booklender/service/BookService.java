package se.lexicon.sudipta.booklender.service;

import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findByReserved(boolean reserved);
    List<BookDto>findByAvailable(boolean available);
   public List<BookDto> findByTitle(String title)throws ObjectNotFoundException;
    BookDto findById(Integer bookId)throws ObjectNotFoundException;
    List<BookDto>findAll();
    BookDto create(BookDto dto) throws ObjectDuplicateException;
    void update(BookDto bookDto);
    void delete(Integer bookId) throws ObjectNotFoundException;




}
