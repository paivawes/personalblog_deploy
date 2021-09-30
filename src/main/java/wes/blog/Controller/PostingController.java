package wes.blog.Controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wes.blog.model.PostingModel;
import wes.blog.repository.PostingRepository;

@RequestMapping("/posting")
@RestController
@CrossOrigin("*")
public class PostingController {

    @Autowired
    private  PostingRepository pRepository;

    @GetMapping
    public ResponseEntity<List<PostingModel>> getAll(){
        List<PostingModel> objectList = pRepository.findAll();

        if(objectList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
        return ResponseEntity.ok().body(objectList);
        }
    }

    @GetMapping("/findById/{idPosting}")
    public ResponseEntity <PostingModel> getById(@PathVariable Long idPosting){
        Optional<PostingModel> objectId = pRepository.findById(idPosting);
        
        if(objectId.isPresent()){
        return ResponseEntity.ok().body(objectId.get());
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/findTitle/{title}")
    public ResponseEntity<List<PostingModel>> getByTitle(@PathVariable String title){
        List<PostingModel> objectTitle = pRepository.findAllByTitleContainingIgnoreCase(title);
        
        if(objectTitle.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
        return ResponseEntity.ok().body(objectTitle);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PostingModel> post(@RequestBody PostingModel posting){
        return ResponseEntity.status(201).body(pRepository.save(posting));
    }

    @PutMapping("/update/{idPosting}")
    public ResponseEntity<PostingModel> put(@RequestBody PostingModel posting){
        return ResponseEntity.status(201).body(pRepository.save(posting));
    }

    @DeleteMapping("/deleteById/{idPosting}")
    public void deleteById(@PathVariable (value =  "idPosting") Long idPosting){
        pRepository.deleteById(idPosting);
    }
}
