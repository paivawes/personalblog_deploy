package wes.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wes.blog.model.ThemeModel;


@Repository
public interface ThemeRepository extends JpaRepository <ThemeModel, Long> {

    List<ThemeModel> findAllByDescriptionContainingIgnoreCase(String description);
    
}
