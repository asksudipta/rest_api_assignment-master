package se.lexicon.sudipta.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sudipta.booklender.model.entity.LibraryUser;

import java.util.Optional;

public interface LibraryUserRepository extends CrudRepository<LibraryUser,Integer> {


    Optional<LibraryUser> findByEmail(Integer integer);
}
