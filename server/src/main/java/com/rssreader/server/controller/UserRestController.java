package com.rssreader.server.controller;


import com.rssreader.server.dto.AllUserDto;
import com.rssreader.server.dto.UserRegistrationDto;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {


    UserService userService;

    @Autowired
    UserRestController(UserService userService){
        this.userService=userService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody UserRegistrationDto userDto){
        userService.addUser(userDto);
    }

    public void updateUser(){

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AllUserDto> getAllUsers(){
        System.out.println("userService.getAllUsers()");
        return userService.getAllUsers();
    }

    //public User getUserByUserName(String userName){

    // return userService.getByUsername(userName);
    // }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void removeUser(@PathVariable Long id){
        System.out.println(id);
        userService.removeUser(userService.getByid(id));
    }


}
