package se.lexicon.sudipta.booklender.service;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.LibraryUserDto;

import se.lexicon.sudipta.booklender.model.entity.LibraryUser;
import se.lexicon.sudipta.booklender.repository.LibraryUserRepository;

import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{

    LibraryUserRepository libraryUserRepository;
    ModelMapper modelMapper;

    @Autowired
    public LibraryUserServiceImpl(LibraryUserRepository libraryUserRepository, ModelMapper modelMapper) {
        this.libraryUserRepository = libraryUserRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LibraryUserDto findById(Integer userId) throws ObjectNotFoundException {
        if (userId == null) throw new IllegalArgumentException("User Id Has null value");
        LibraryUser userResult =libraryUserRepository.findById(userId).orElseThrow(
                () -> new ObjectNotFoundException("LibraryUser data not found"));

        return modelMapper.map(userResult, LibraryUserDto.class);

    }

    @Override
    public LibraryUserDto findByEmail(String email) throws ObjectNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email has null value");
        LibraryUser libraryUserResult = libraryUserRepository.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException("Email not found"));

        return modelMapper.map(libraryUserResult, LibraryUserDto.class);

    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUserList = (List<LibraryUser>) libraryUserRepository.findAll();
        List<LibraryUserDto> libraryUserDtoList = modelMapper.map(
                libraryUserList,
                new TypeToken<List<LibraryUserDto>>() {
                }.getType()
        );
        return libraryUserDtoList;

    }

    @Override
    public LibraryUserDto create(LibraryUserDto dto) throws ObjectDuplicateException {
        if (dto == null) throw new IllegalArgumentException("LibraryUser has null value");
        if (dto.getUserId() !=0) throw new IllegalIdentifierException("Library UserId must be null");

        LibraryUser convertedToEntity = modelMapper.map(dto, LibraryUser.class);
        LibraryUser createdLibraryUser = libraryUserRepository.save(convertedToEntity);
        LibraryUserDto convertToDto = modelMapper.map(createdLibraryUser, LibraryUserDto.class);
        return convertToDto;

    }

    @Override
    public void update(LibraryUserDto dto) {
        if (dto == null) throw new IllegalArgumentException("LibraryUserDto was null");
        if (dto.getUserId()== 0) throw new IllegalArgumentException("LibraryUserDto UserId must not be null");
        LibraryUser convertedToEntity = modelMapper.map(dto, LibraryUser.class);
        libraryUserRepository.save(convertedToEntity);

    }

    @Override
    public void delete(Integer userId) throws ObjectNotFoundException {
        findById(userId);
        libraryUserRepository.deleteById(userId);

    }
}
