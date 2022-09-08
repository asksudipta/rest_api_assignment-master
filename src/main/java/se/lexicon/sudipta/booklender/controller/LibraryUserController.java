package se.lexicon.sudipta.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.LibraryUserDto;
import se.lexicon.sudipta.booklender.service.LibraryUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/bookLender/libraryUser")
@Validated
public class LibraryUserController {

    LibraryUserService libraryUserService;

    @Autowired
    public LibraryUserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;

    }

    // Find By id
    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("userId") Integer userId) throws ObjectNotFoundException {

        System.out.println("userId" + userId);
        LibraryUserDto libraryUserServiceById = libraryUserService.findById(userId);
        return ResponseEntity.ok().body(libraryUserServiceById);
    }

    // Find By Email
    @GetMapping("/{email}")
    public ResponseEntity<LibraryUserDto> findByEmail(@PathVariable("email") String email) throws ObjectNotFoundException {
        System.out.println("email" + email);
        LibraryUserDto libraryUserByEmail = libraryUserService.findByEmail(email);
        return ResponseEntity.ok(libraryUserByEmail);
    }

    //FindAll
    @GetMapping("/all")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        return ResponseEntity.ok().body(libraryUserService.findAll());
    }
    //postMapping/ Create

    @PostMapping("/")
    public ResponseEntity<LibraryUserDto> create(@RequestBody @Valid LibraryUserDto dto) throws ObjectDuplicateException {
        System.out.println("*****Create method executed*******" + dto);
        LibraryUserDto createLibraryUser = libraryUserService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createLibraryUser); //code- 201 for creatred
    }

    //Put mapping /Update
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid LibraryUserDto dto) {
        System.out.println("dto" + dto);
        libraryUserService.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //code- 204 for updated
    }

}
