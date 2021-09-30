package wes.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wes.blog.model.PostingModel;

@Repository
public interface PostingRepository extends JpaRepository <PostingModel, Long>{
    
    List<PostingModel> findAllByTitleContainingIgnoreCase (String title); 

}
