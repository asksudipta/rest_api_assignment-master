package se.lexicon.sudipta.booklender.service;

import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
import se.lexicon.sudipta.booklender.model.dto.LibraryUserDto;
import se.lexicon.sudipta.booklender.model.dto.LoanDto;


import java.util.List;

public interface LoanService {

    LoanDto findById(Integer loanId) throws ObjectNotFoundException;

    List<LoanDto> findByBookId(Integer bookId) throws ObjectNotFoundException;

    List<LoanDto> findByUserId(Integer userId) throws ObjectNotFoundException;

    List<LoanDto> findByConcluded(boolean status);

    List<LoanDto> findAll();

    LoanDto create(LoanDto dto) throws ObjectDuplicateException, ObjectNotFoundException;

    void update(LoanDto dto);

    void delete(Integer loanId)throws ObjectNotFoundException;




}
