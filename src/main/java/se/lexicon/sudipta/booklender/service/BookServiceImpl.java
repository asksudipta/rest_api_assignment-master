package se.lexicon.sudipta.booklender.service;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
import se.lexicon.sudipta.booklender.model.entity.Book;
import se.lexicon.sudipta.booklender.repository.BookRepository;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    //Inject Dependency Injection

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        if (!reserved) throw new IllegalArgumentException("Book is not reserved");
        List<Book> isReserved = bookRepository.findByReserved(true);
        List<BookDto> bookDtoReservedList = modelMapper.map(isReserved,
                new TypeToken<List<BookDto>>() {
                }.getType()
        );

        //Todo implement method body,Convert Optional to List.
        return bookDtoReservedList;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        //Todo implement method body, Convert Optional to List.

        if (!available) throw new IllegalArgumentException("Book is not available");
        List<Book> isAvailable = bookRepository.findByAvailable(true);
        List<BookDto> bookDtoAvailableList = modelMapper.map(isAvailable,
                new TypeToken<List<BookDto>>() {
                }.getType()
        );
        return bookDtoAvailableList;
    }

    @Override
    public List<BookDto> findByTitle(String title) throws ObjectNotFoundException {
        if (title == null) throw new IllegalArgumentException("Title has null value");
        List<Book> bookTitleList=bookRepository.findByTitle(title);
        List<BookDto> bookDtoListOfTitle=modelMapper.map(bookTitleList,
                new TypeToken<List<BookDto>>(){

                }.getType()
        );
        return bookDtoListOfTitle;
    }

    @Override
    public BookDto findById(Integer bookId) throws ObjectNotFoundException {
        if (bookId == null) throw new IllegalArgumentException("Book Id Has null value");
        Book result = bookRepository.findById(bookId).orElseThrow(
                () -> new ObjectNotFoundException("Book data not found"));

        return modelMapper.map(result, BookDto.class);

    }

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<BookDto> bookDtoList = modelMapper.map(
                bookList,
                new TypeToken<List<BookDto>>() {
                }.getType()
        );
        return bookDtoList;

    }

    @Override
    public BookDto create(BookDto dto)  {

        if (dto == null) throw new IllegalArgumentException("BookDto has null value");
        if (dto.getBookId() != 0) throw new IllegalIdentifierException("BookDto book Id must be null");

        Book convertedToEntity = modelMapper.map(dto, Book.class);
        Book createdBook = bookRepository.save(convertedToEntity);
        BookDto convertedToDto = modelMapper.map(createdBook, BookDto.class);
        return convertedToDto;

    }

    @Override
    public void update(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("BookDto was null");
        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("BookDto.Id must not be null");
        Book convertedToEntity = modelMapper.map(bookDto, Book.class);
        bookRepository.save(convertedToEntity);

    }

    @Override
    public void delete(Integer bookId) throws ObjectNotFoundException {
        findById(bookId);
        bookRepository.deleteById(bookId);


    }
}
