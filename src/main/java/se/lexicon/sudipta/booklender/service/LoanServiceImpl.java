package se.lexicon.sudipta.booklender.service;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.BookDto;
import se.lexicon.sudipta.booklender.model.dto.LibraryUserDto;
import se.lexicon.sudipta.booklender.model.dto.LoanDto;
import se.lexicon.sudipta.booklender.model.entity.Book;
import se.lexicon.sudipta.booklender.model.entity.LibraryUser;
import se.lexicon.sudipta.booklender.model.entity.Loan;
import se.lexicon.sudipta.booklender.repository.BookRepository;
import se.lexicon.sudipta.booklender.repository.LibraryUserRepository;
import se.lexicon.sudipta.booklender.repository.LoanRepository;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    //Inject Repository through dependency Injection
    LibraryUserRepository libraryUserRepository;
    BookRepository bookRepository;
    LoanRepository loanRepository;
    ModelMapper modelMapper;


    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, ModelMapper modelMapper,
                           LibraryUserRepository libraryUserRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
    }

    @Override
    public LoanDto findById(Integer loanId) throws ObjectNotFoundException {

        if (loanId == null) throw new IllegalArgumentException("Loan Id Has null value");
        Loan loanResult = loanRepository.findById(loanId).orElseThrow(
                () -> new ObjectNotFoundException("Loan data not found"));

        return modelMapper.map(loanResult, LoanDto.class);

    }

    @Override
    public List<LoanDto> findByBookId(Integer bookId) throws ObjectNotFoundException {
        //check
        if (bookId == null) throw new IllegalArgumentException("Book id is null");
        //validate
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new ObjectNotFoundException("Book id not found"));
        //Create a list of loan
        List<Loan> loanList = loanRepository.findByBookBookId(bookId);
        //Convert it
        List<LoanDto> convertedDto = modelMapper.map(loanList,
                new TypeToken<List<LoanDto>>() {
                }.getType());
        return convertedDto;

    }


    @Override
    public List<LoanDto> findByUserId(Integer userId) throws ObjectNotFoundException {
        //check
        if (userId == null) throw new IllegalArgumentException("User Id is null");
        //validate
        LibraryUser user = libraryUserRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Library User not found"));
        //Create a list of loan
        List<Loan> loanList = loanRepository.findByLoanTakerUserId(userId);
        //Convert it to loanDto
        List<LoanDto> convertedToDto = modelMapper.map(loanList,
                new TypeToken<List<LoanDto>>() {
                }.getType());

        return convertedToDto;

    }

    @Override
    public List<LoanDto> findByConcluded(boolean status) {

        if (status == false) throw new IllegalArgumentException("Loan status is not concluded");
        List<Loan> isConcluded = loanRepository.findByConcluded(true);
        List<LoanDto> convertDto = modelMapper.map(isConcluded, new TypeToken<List<LoanDto>>() {
        }.getType());

        return convertDto;
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loanList = (List<Loan>) loanRepository.findAll();
        List<LoanDto> loanDtoList = modelMapper.map(
                loanList,
                new TypeToken<List<LoanDto>>() {
                }.getType()
        );
        return loanDtoList;

    }

    @Override
    public LoanDto create(LoanDto dto) throws ObjectNotFoundException {
        if (dto == null) throw new IllegalArgumentException("Loan has null value");

        if (dto.getLoanId() != 0) throw new IllegalIdentifierException("Loan loanId must be null");
        if (dto.isConcluded()) throw new IllegalArgumentException("Loan concluded must be  false or null");
        if (dto.getLoanDate() == null) throw new IllegalArgumentException("Loan date has null value");

        //Checking loanTaker form LibraryUserDto has any value or not!.
        if (dto.getLoanTaker() == null || dto.getLoanTaker().size() == 0)
            throw new IllegalArgumentException("Loan taker List was null");

        //Check books  booksList from BookDto has any value or not!;
        if (dto.getBooks() == null || dto.getBooks().size() == 0)
            throw new IllegalArgumentException("Book List was null");

        //Check books are valid or not!
        for (BookDto bookDto : dto.getBooks()) {
            bookRepository.findById(bookDto.getBookId()).orElseThrow(
                    () -> new ObjectNotFoundException("Book is not valid"));
        }
        //Check Library user  is valid or not!
        for (LibraryUserDto libraryUserDto : dto.getLoanTaker()) {
            bookRepository.findById(libraryUserDto.getUserId()).orElseThrow(
                    () -> new ObjectNotFoundException("Library User not valid"));
        }
        //Convert dto to entity and return the value
        Loan convertedToEntity = modelMapper.map(dto, Loan.class);
        Loan createdLoan = loanRepository.save(convertedToEntity);
        LoanDto convertLoanToDto = modelMapper.map(createdLoan, LoanDto.class);
        return convertLoanToDto;

    }

    @Override
    public void update(LoanDto dto) {
        if (dto == null) throw new IllegalArgumentException("LoanDto was null");
        if (dto.getLoanId() == 0) throw new IllegalArgumentException("LoanId must not be null");
        Loan convertedToEntity = modelMapper.map(dto, Loan.class);
        loanRepository.save(convertedToEntity);


    }

    @Override
    public void delete(Integer loanId) throws ObjectNotFoundException {

        findById(loanId);
        loanRepository.deleteById(loanId);
    }
}
