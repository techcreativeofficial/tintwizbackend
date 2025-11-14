package com.example.TintWiz.controller;

import com.example.TintWiz.dto.AuthResponse;
import com.example.TintWiz.dto.ChangePassword;
import com.example.TintWiz.dto.ChangeUsername;
import com.example.TintWiz.dto.UserDto;
import com.example.TintWiz.exception.AllException;
import com.example.TintWiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/createaccount")
    public ResponseEntity<Object> createAccount(@RequestBody(required = true) UserDto userDto) {
        try {
            return userService.createAccount(userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "/edituser/{id}")
    public ResponseEntity<Object>editUser(@PathVariable("id")Long id,@RequestBody UserDto userDto) {
        try {
            return userService.editUser(id,userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(path = "/hapususer/{id}")
    public ResponseEntity<Object>hapusUser(@PathVariable("id")Long id) {
        try {
            return userService.hapusUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object>login(@RequestBody AuthResponse authResponse){
        try {
            return userService.login(authResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public UserDto fetchUserByIduser(@PathVariable("id") Long id) throws AllException {
        return userService.fetchUserDtoByIduser(id);
    }

   @PostMapping("/changepassword")
   public ResponseEntity<Object>changePassword(@RequestBody ChangePassword changePassword){
       try {
           return userService.changePasswords(changePassword);
       } catch (Exception ex) {
           ex.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

    @PostMapping("/changeusername")
    public ResponseEntity<Object>changeUser(@RequestBody ChangeUsername changeUsername){
        try {
            return userService.changeUsername(changeUsername);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByUsername/{username}")
    public UserDto fetchUserByUsername(@PathVariable("username") String username) throws AllException {
        return userService.fetchUserDtoByUsername(username);
    }


    @GetMapping("/showall")
    @ResponseBody
    public ResponseEntity<Page<UserDto>> showAllAndPaginationUser(
            @RequestParam(required = false) String role, // => optional
            @RequestParam(required = false) String username, // => optional
            @RequestParam(defaultValue = "desc") String order, // => optional
            @RequestParam(name = "page", defaultValue = "1") int offset, // => optional
            @RequestParam(name = "limit", defaultValue = "10") int pageSize // => optional
    ) {
        Page<UserDto> userDtoPage = userService.showAllAndPaginationUser(role,username, order, offset, pageSize);
        return ResponseEntity.ok(userDtoPage);
    }


}
