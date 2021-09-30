package wes.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wes.blog.model.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

    List<PersonModel> findAllByUsernameContainingIgnoreCase(String username);

    Optional<PersonModel> findByUsername(String username);
    
}
