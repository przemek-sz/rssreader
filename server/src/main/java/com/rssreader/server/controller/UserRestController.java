package com.rssreader.server.controller;


import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.AllUserDto;
import com.rssreader.server.dto.JwtRequest;
import com.rssreader.server.dto.UserRegistrationDto;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {


    UserService userService;
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRestController(UserService userService, JwtTokenUtil jwtTokenUtil){
        this.userService=userService;
        this.jwtTokenUtil=jwtTokenUtil;
    }


    @RequestMapping(method = RequestMethod.PUT)
    public void addUser(@RequestBody UserRegistrationDto userDto){
        userService.addUser(userDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateUser(@RequestBody UserRegistrationDto userDto,@RequestHeader("Authorization") String token){
        userService.updateUser(userDto,jwtTokenUtil.getUsernameFromToken(token.split(" ")[1]));
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
