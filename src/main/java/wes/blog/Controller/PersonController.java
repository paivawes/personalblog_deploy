package wes.blog.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import wes.blog.Servicer.UserService;
import wes.blog.model.PersonModel;
import wes.blog.repository.PersonRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class PersonController {
    
    @Autowired
    private PersonRepository uRepository;

    @Autowired
    private UserService uService;

    @GetMapping
    public ResponseEntity<List<PersonModel>> getAll(){
        List<PersonModel> objectUser = uRepository.findAll();

        if(objectUser.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        
        else{
            return ResponseEntity.status(200).body(objectUser);
        }
    }

    @GetMapping("/findById/{idUser}")
    public ResponseEntity<PersonModel> getById(@PathVariable Long idUser){
        Optional<PersonModel> objectId = uRepository.findById(idUser);

        if(objectId.isPresent()){
            return ResponseEntity.status(200).body(objectId.get());
        }
        else{
            return ResponseEntity.status(204).build();
        }   
    }

    @GetMapping("/findUser")
    public ResponseEntity<List<PersonModel>> getByEmail(@PathVariable (value = "user") String user){
    List<PersonModel> objectUser = uRepository.findAllByUsernameContainingIgnoreCase(user);

        if(objectUser.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        else{
            return ResponseEntity.status(200).body(objectUser);
        }
    } 
    
    @PostMapping("/login")
	public ResponseEntity<PersonModel> Post(@Valid @RequestBody PersonModel user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(uService.creatUser(user));
	}
    

    @PutMapping("/update")
    public ResponseEntity<PersonModel> put (@Valid @RequestBody PersonModel user){
        return ResponseEntity.status(201).body(uRepository.save(user));
    }

    @DeleteMapping("/delete/{idUser}")
    public void deleteById(@PathVariable (value = "idUser") Long idUser){
        uRepository.deleteById(idUser);
    }

}
