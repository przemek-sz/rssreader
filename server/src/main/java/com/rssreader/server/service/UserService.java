package com.rssreader.server.service;



import com.rssreader.server.dto.AllUserDto;
import com.rssreader.server.dto.UserDto;
import com.rssreader.server.dto.UserRegistrationDto;
import com.rssreader.server.dto.UserUpdateDto;
import com.rssreader.server.model.User;

import java.util.List;


public interface UserService {

    public void addUser(UserRegistrationDto user);
    public User updateUser(UserUpdateDto userDto, String username);
    public void removeUser(User user);
    public List<AllUserDto> getAllUsers();
    public User getByUsername(String username);
    public User getByid(Long id);
}
