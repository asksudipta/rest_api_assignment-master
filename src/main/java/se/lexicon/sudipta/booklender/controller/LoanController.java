package se.lexicon.sudipta.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
import se.lexicon.sudipta.booklender.model.dto.LoanDto;
import se.lexicon.sudipta.booklender.model.entity.Loan;
import se.lexicon.sudipta.booklender.service.LoanService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/bookLender/loan")
@Validated
public class LoanController {

    LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    //Create
    @PostMapping("/")
    public ResponseEntity<LoanDto> CreateLoan(@RequestBody @Valid LoanDto dto) throws ObjectNotFoundException, ObjectDuplicateException {
        System.out.println("loanDto" + dto);
        LoanDto loanDto = loanService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanDto);
    }

    //Update
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid LoanDto dto) {
        System.out.println("dto" + dto);
        loanService.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //code- 204 for updated
    }
    //Delete
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> delete(@PathVariable("loanId") Integer loanId) throws ObjectNotFoundException {
        System.out.println("loanId" + loanId);
        loanService.delete(loanId);
        return ResponseEntity.noContent().build();//code 204 for delete
    }

    //FindAll
    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll(){
        System.out.println("### FindAllLoan has been executed!");
        List<LoanDto> loanDtoList = loanService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(loanDtoList);//code- 200 for return
    }

    //FindByConcluded
    @GetMapping("/{concluded}")
    public ResponseEntity<List<LoanDto>> findByConcluded(@PathVariable ("concluded") boolean concluded){

        System.out.println("concluded"+ concluded);
        List<LoanDto> LoanDtoConcludedList=loanService.findByConcluded(concluded);
        return ResponseEntity.ok().body(LoanDtoConcludedList);
    }
    //FindByUserId
    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanDto>> findByUserId(@PathVariable ("userId")Integer userId) throws ObjectNotFoundException {
        System.out.println("userId" + userId);
        List<LoanDto> loanDtoUserIdList = loanService.findByUserId(userId);
        return ResponseEntity.ok().body(loanDtoUserIdList);
    }
    //FindByBookId
    @GetMapping("/{bookId}")
    public ResponseEntity<List<LoanDto>>findByBookId(@PathVariable("bookId") Integer bookId) throws ObjectNotFoundException {
        System.out.println("bookId"+ bookId);
        List<LoanDto> loanDtoBookIdList=loanService.findByBookId(bookId);
        return ResponseEntity.ok().body(loanDtoBookIdList);

    }

    //FindById
    public ResponseEntity<LoanDto> findById (@PathVariable("loanId") Integer loanId) throws ObjectNotFoundException {
        System.out.println("loanId"+ loanId);
        LoanDto loanDtoListById=loanService.findById(loanId);
        return ResponseEntity.ok().body(loanDtoListById);

    }


}
