package com.rssreader.server.controller;


import com.rssreader.server.config.security.JwtTokenUtil;
import com.rssreader.server.dto.*;
import com.rssreader.server.model.User;
import com.rssreader.server.repository.UserRepository;
import com.rssreader.server.service.CustomUserDetailsService;
import com.rssreader.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserRestController {


    UserService userService;
    JwtTokenUtil jwtTokenUtil;
    CustomUserDetailsService userDetailsService;
    UserRepository userRepository;

    @Autowired
    UserRestController(UserService userService, JwtTokenUtil jwtTokenUtil, CustomUserDetailsService userDetailsService, UserRepository userRepository){
        this.userService=userService;
        this.jwtTokenUtil=jwtTokenUtil;
        this.userDetailsService=userDetailsService;
        this.userRepository=userRepository;
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> addUser(@RequestBody UserRegistrationDto userDto){
        List<Integer> errors= new ArrayList<>();

        if(userService.getByUsername(userDto.getUsername())!=null){
            errors.add(1);
        }

        if(userRepository.getByEmail(userDto.getEmail())!=null){
            errors.add(2);
        }

        if(errors.size()==0){
            userService.addUser(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userDto, @RequestHeader("Authorization") String token){
        User user = userService.updateUser(userDto,jwtTokenUtil.getUsernameFromToken(token.split(" ")[1]));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String newToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(
                new JwtResponse(newToken,userDetails.getUsername(),
                        userDetails.getAuthorities().stream().map((a)->a.getAuthority()).collect(Collectors.toList()),
                        jwtTokenUtil.getExpirationDateFromToken(newToken))
        );
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
