package wes.blog.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import wes.blog.model.PersonModel;
import wes.blog.repository.PersonRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private PersonRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<PersonModel> user = userRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
        return user.map(UserDetailsImpl::new).get();

    }

}
