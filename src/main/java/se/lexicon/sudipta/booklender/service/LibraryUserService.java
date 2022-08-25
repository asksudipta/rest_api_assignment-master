package se.lexicon.sudipta.booklender.service;

import se.lexicon.sudipta.booklender.exception.ObjectDuplicateException;
import se.lexicon.sudipta.booklender.exception.ObjectNotFoundException;
import se.lexicon.sudipta.booklender.model.dto.LibraryUserDto;


import java.util.List;

public interface LibraryUserService {

    LibraryUserDto findById(Integer userId)throws ObjectNotFoundException;

    LibraryUserDto findByEmail(String email)throws ObjectNotFoundException;

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto dto)throws ObjectDuplicateException;

    void update(LibraryUserDto dto);

    void delete(Integer userId)throws ObjectNotFoundException;


}
