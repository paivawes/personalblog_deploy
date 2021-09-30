package wes.blog.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import wes.blog.model.ThemeModel;
import wes.blog.repository.ThemeRepository;

@RestController
@RequestMapping("/theme")
@CrossOrigin("*")
public class ThemeController {
    
    @Autowired
    private ThemeRepository tRepository;

    @GetMapping
    public ResponseEntity<List<ThemeModel>> getAll(){
        List<ThemeModel> objectList = tRepository.findAll();

        if(objectList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok().body(objectList);
        }
    }

    @GetMapping ("/findById/{idTheme}")
    public ResponseEntity <ThemeModel> getById(@PathVariable (value = "idTheme") Long idTheme){
        Optional<ThemeModel> objectId = tRepository.findById(idTheme);

        if(objectId.isPresent()){
            return ResponseEntity.ok().body(objectId.get());
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping ("/findByDescription/{description}")
    public ResponseEntity<List<ThemeModel>> getByTheme(@PathVariable (value = "description" )String description){
        List<ThemeModel> objectTheme = tRepository.findAllByDescriptionContainingIgnoreCase(description);

        if(objectTheme.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        else{
            return ResponseEntity.status(200).body(objectTheme);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<ThemeModel> post (@RequestBody ThemeModel theme){
        return ResponseEntity.status(HttpStatus.CREATED).body(tRepository.save(theme));

    }

    @PutMapping("/update/{idTheme}")
    public ResponseEntity<ThemeModel> put (@RequestBody ThemeModel theme){
        return ResponseEntity.status(HttpStatus.CREATED).body(tRepository.save(theme));
    }

    @DeleteMapping("/deleteById/{idTheme}")
    public void deleteById (@PathVariable (value = "idTheme") Long idTheme){
        tRepository.deleteById(idTheme);
    }

}
