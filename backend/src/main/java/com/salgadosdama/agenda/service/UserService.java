package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.User;
import com.salgadosdama.agenda.models.repository.UserRepository;
import com.salgadosdama.agenda.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User create(User user) {
    String hashedPassword = new BCryptPasswordEncoder()
            .encode(user.getPassword());

    user.setPassword(hashedPassword);

    return userRepository.save(user);
  }

  public List<User> findAll(){
    return userRepository.findAll();
  }

  public User findById(Long id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  public User deleteById(Long id) throws UserNotFoundException{
    User user = findById(id);

    userRepository.deleteById(id);

    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
