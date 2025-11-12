package com.example.TintWiz.service;

import com.example.TintWiz.dto.ChangePassword;
import com.example.TintWiz.dto.ChangeUsername;
import com.example.TintWiz.dto.UserDto;
import com.example.TintWiz.entity.User;
import com.example.TintWiz.exception.AllException;
import com.example.TintWiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public long getTotalUsers() {
        log.info("Inside getTotalUsers");
        return userRepository.count();
    }

    public ResponseEntity<Object> createAccount(UserDto userDto) {
        try {
            log.info("Inside Create Account {}", userDto);
            User user = new User();

            user.setUsername(userDto.getUsername());
            String rawPassword = userDto.getPassword();
//            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(user.getPassword());
            user.setRole(userDto.getRole());
            user.setStatus(true);
            user.setCreated(Instant.now());

            userRepository.save(user);

            return ResponseEntity.ok("Account created successfully");
        } catch (Exception e) {
            log.error("Error creating account", e);
            return ResponseEntity.status(500).body("Error creating account");
        }
    }

    public ResponseEntity<Object> editUser(Long id, UserDto userDto) {
        try {
            log.info("Inside edit user");
            Optional<User> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for ID: " + id);
            }
            User user = optionalUser.get();

            user.setUsername(userDto.getUsername());

            String rawPassword = userDto.getPassword();
            if (rawPassword == null || rawPassword.isEmpty()) {
                rawPassword = user.getPassword();
            } else {
//                String encodedPassword = passwordEncoder.encode(rawPassword);
                user.setPassword(userDto.getPassword());
            }

            user.setRole(userDto.getRole());
            user.setStatus(userDto.getStatus());;

            userRepository.save(user);
            return ResponseEntity.ok("User edited successfully");

        }catch (Exception e){
            log.error("Error edited user", e);
            return ResponseEntity.status(500).body("Error edited user");
        }
    }

    public ResponseEntity<Object> hapusUser(Long id) {
        try {
            log.info("Inside hapus User");
            Optional<User> optionalUser  = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                userRepository.deleteById(id);
                return ResponseEntity.ok("Successfully deleted user");
            } else {
                return ResponseEntity.status(404).body("user not found");
            }
        } catch (Exception e) {
            log.error("Error delete user", e);
            return ResponseEntity.status(500).body("Error delete user");
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }


    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setIduser(user.getIduser());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        userDto.setCreated(user.getCreated());

        return userDto;
    }
    
    public UserDto fetchUserDtoByIduser(Long id) throws AllException {
        log.info("Inside fetchUserDtoByIduser");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AllException("User with iduser " + id + " not found"));

        return mapUserToUserDto(user);
    }

//    public ResponseEntity<Object> changePasswords(ChangePassword changePassword) {
//        try {
//            log.info("Change password");
//            log.info("Received request with payload: {}", changePassword);
//
//            Long id = changePassword.getId();
//            if (id != null) {
//                User user = userRepository.findByIduser(id);
//                if (user != null) {
//                    String oldPassword = changePassword.getOldpassword();
//                    String newPassword = changePassword.getNewpassword();
//                    if (passwordEncoder.matches(oldPassword, user.getPassword())) {
//                        String encodedNewPassword = passwordEncoder.encode(newPassword);
//                        user.setPassword(encodedNewPassword);
//                        userRepository.save(user);
//                        return ResponseEntity.ok(Collections.singletonMap("message", "Password updated successfully"));
//                    } else {
//                        return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Incorrect old password"));
//                    }
//                } else {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "User not found"));
//                }
//            } else {
//                log.error("Token is null or empty");
//                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Token is null or empty"));
//            }
//        } catch (Exception ex) {
//            log.error("An error occurred while changing the password", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "An error occurred while changing the password"));
//        }
//    }

    public ResponseEntity<Object> changeUsername(ChangeUsername changeUsername) {
        try {
            log.info("Change username");
            log.info("Received request with payload: {}", changeUsername);

            Long id = changeUsername.getId();
            if (id != null) {
                User user = userRepository.findByIduser(id);
                if (user != null) {
                    String oldUser = changeUsername.getOldusername();
                    String newUser = changeUsername.getNewusername();
                    if (oldUser.equals(user.getUsername())) {
                        user.setUsername(newUser);
                        userRepository.save(user);
                        return ResponseEntity.ok(Collections.singletonMap("message", "Username updated successfully"));
                    } else {
                        return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Incorrect old username"));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "User not found"));
                }
            } else {
                log.error("Token is null or empty");
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Token is null or empty"));
            }
        } catch (Exception ex) {
            log.error("An error occurred while changing the username", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "An error occurred while changing the username"));
        }
    }

    public UserDto fetchUserDtoByUsername(String username) throws AllException {
        log.info("Inside fetchUserDtoByUsername");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AllException("User with username " + username + " not found"));

        return mapUserToUserDto(user);
    }

    public Page<UserDto> showAllAndPaginationUser(String role,String username, String order, int offset, int pageSize) {
        log.info("Inside showAllAndPaginationUser");
        Page<User>userPage;
        if (role != null && username != null) {
            userPage = userRepository.findByUsernameAndRoleContainingIgnoreCase(username,role,PageRequest.of(offset - 1, pageSize,  "desc".equals(order) ? Sort.by("iduser").descending() : Sort.by("iduser").ascending()));
        } else if (role != null) {
            userPage = userRepository.findByRoleContainingIgnoreCase(role,PageRequest.of(offset - 1, pageSize,  "desc".equals(order) ? Sort.by("iduser").descending() : Sort.by("iduser").ascending()));
        } else if (username != null) {
            userPage = userRepository.findByUsernameContainingIgnoreCase(username,PageRequest.of(offset - 1, pageSize,  "desc".equals(order) ? Sort.by("iduser").descending() : Sort.by("iduser").ascending()));
        } else {
            userPage = userRepository.findAll(PageRequest.of(offset - 1,pageSize, "desc".equals(order) ? Sort.by("iduser").descending() : Sort.by("iduser").ascending()));
        }
                List<UserDto> resultList = userPage.getContent().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setIduser(user.getIduser());
                    userDto.setUsername(user.getUsername());
                    userDto.setPassword(user.getPassword());
                    userDto.setRole(user.getRole());
                    userDto.setStatus(user.getStatus());
                    userDto.setCreated(user.getCreated());
                    return userDto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(resultList, userPage.getPageable(), userPage.getTotalElements());

    }

}


