package pl.danielpiskorz.mysqlauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.danielpiskorz.mysqlauth.model.CustomUserDetails;
import pl.danielpiskorz.mysqlauth.model.User;
import pl.danielpiskorz.mysqlauth.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository usersRepository;
	
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = usersRepository.findByName(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }
}
