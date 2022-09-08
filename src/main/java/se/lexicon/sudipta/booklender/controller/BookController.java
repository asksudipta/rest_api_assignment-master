package se.lexicon.sudipta.booklender.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
;
import se.lexicon.sudipta.booklender.service.BookService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/bookLender/book")
@Validated
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("api/bookLender/book/")
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookDto dto) throws ObjectDuplicateException {

        System.out.println("bookDto" + dto);
        BookDto createBook = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook); //code 201 for created
    }

    @PutMapping("api/bookLender/book/update")
    public ResponseEntity<Void> update(@RequestBody @Valid BookDto dto) {
        System.out.println("dto" + dto);
        bookService.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //code- 204 for updated
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable("bookId") Integer bookId) throws ObjectNotFoundException {
        System.out.println("bookId" + bookId);
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();//code 204 for delete

    }
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable ("bookId")Integer bookId) throws ObjectNotFoundException {
        System.out.println("bookId"+ bookId);
        BookDto bookDto = bookService.findById(bookId);
        return ResponseEntity.ok(bookDto);

    }

    @GetMapping("/find")
    public ResponseEntity<List<BookDto>> find(
            @RequestParam(name = "findAll", defaultValue = "false") final String findAll,
            @RequestParam(name = "title", defaultValue = "All") final String title,
            @RequestParam(name = "reserved", defaultValue = "All") final String reserved,
            @RequestParam(name = "available", defaultValue = "All") final String available
    ) throws ObjectNotFoundException {


        if (findAll.equals("True")) {
            List<BookDto> bookDtoList = bookService.findAll();
            return ResponseEntity.ok().body(bookDtoList);

        } else if (!title.equals("All")) {
            System.out.println("title" + title);
            List<BookDto> bookDtoList = bookService.findByTitle(title);
            return ResponseEntity.ok().body(bookDtoList);
        }
        else if (!Objects.equals(reserved, "All")) {
            System.out.println("reserved" + reserved);
            List<BookDto> bookDtoList = bookService.findByReserved(Boolean.parseBoolean(reserved));
            return ResponseEntity.ok().body(bookDtoList);

        } else if (!Objects.equals(available, "All")) {
            System.out.println("available" + available);
            List<BookDto> bookDtoList = bookService.findByAvailable(Boolean.parseBoolean(available));
            return ResponseEntity.ok().body(bookDtoList);

        }
        return ResponseEntity.badRequest().build();

    }


    /*
    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> findAll(){
        System.out.println("### FindAll has been executed!");
        List<BookDto> bookDtoList = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookDtoList);//code- 200 for return
    }


    @GetMapping("/{title}")
    public ResponseEntity<List<BookDto>> findByTitle(@PathVariable ("title")String title) throws ObjectNotFoundException {
        System.out.println("title"+ title);
        List<BookDto> bookDtoTitleList=bookService.findByTitle(title);
        return ResponseEntity.ok().body(bookDtoTitleList);
    }

    //http://localhost:8080/api/booklender/book/paramValue
    @GetMapping("/{available}")
    public ResponseEntity<List<BookDto>> findByAvailable(@PathVariable ("available")boolean available){
        System.out.println("available"+available);
        List<BookDto> bookListByAvailableStatus=bookService.findByAvailable(available);
        return ResponseEntity.ok().body(bookListByAvailableStatus);
    }

    //http://localhost:8080/api/booklender/book/paramValue
    @GetMapping("/{reserved}")
    public ResponseEntity<List<BookDto>> findByReserved(@PathVariable ("reserved") boolean reserved){

        System.out.println("reserved"+ reserved);
        List<BookDto> bookListByReservedStatus=bookService.findByReserved(reserved);
        return ResponseEntity.ok().body(bookListByReservedStatus);
    }
*/
}
