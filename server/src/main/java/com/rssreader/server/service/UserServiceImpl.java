package com.rssreader.server.service;


import com.rssreader.server.dto.AllUserDto;
import com.rssreader.server.dto.UserRegistrationDto;
import com.rssreader.server.model.User;
import com.rssreader.server.repository.UserRepository;
import com.rssreader.server.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    BaseConverter<User, AllUserDto> baseConverter;
    BaseConverter<UserRegistrationDto,User> registrationBaseConverter;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, BaseConverter<User, AllUserDto> baseConverter, BaseConverter<UserRegistrationDto,User> registrationBaseConverter){
        this.userRepository=userRepository;
        this.userRoleRepository=userRoleRepository;
        this.baseConverter=baseConverter;
        this.registrationBaseConverter=registrationBaseConverter;
    }

    @Override
    public void addUser(UserRegistrationDto userDto) {

        String DEFAULT_ROLE="ROLE_USER";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user=registrationBaseConverter.convert(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.getRoles().add(userRoleRepository.getByRole(DEFAULT_ROLE));

        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<AllUserDto> getAllUsers() {

        List<AllUserDto> usersDto=baseConverter.convertAll(userRepository.findAll());
        return usersDto;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByusername(username);
    }

    @Override
    public User getByid(Long id) {
        return userRepository.getByid(id);
    }
}
