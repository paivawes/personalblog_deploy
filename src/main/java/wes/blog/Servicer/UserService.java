package wes.blog.Servicer;


import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wes.blog.model.UserLogin;
import wes.blog.model.PersonModel;
import wes.blog.repository.PersonRepository;

@Service
public class UserService {

    @Autowired
    private PersonRepository userRepository;

    public PersonModel creatUser(PersonModel user){
    	Optional<PersonModel> optionalObject = userRepository.findByUsername(user.getUsername());
    	
    	if (optionalObject.isPresent()) {
			return null;
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			String passwordEncoder = encoder.encode(user.getPassword());
			user.setPassword(passwordEncoder);
	        
			return userRepository.save(user);
		}
        
    }

    public Optional<UserLogin> Log(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<PersonModel> userM = userRepository.findByUsername(user.get().getUser());

		if (userM.isPresent()) {
			if (encoder.matches(user.get().getPassword(), userM.get().getPassword())) {

				String auth = user.get().getUser() + ":" + user.get().getPassword();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);				
				user.get().setName(userM.get().getName());
				user.get().setPassword(userM.get().getPassword());
				user.get().setId(userM.get().getIdUser());
				
				return user;

			}
		}
		return null;
	} 

    
}
