package com.shakuro.restcrudapp.service.serviceImpl;

import com.shakuro.restcrudapp.dao.UserRepository;
import com.shakuro.restcrudapp.entity.User;
import com.shakuro.restcrudapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new MyUserDetails(user.get());
    }
}
