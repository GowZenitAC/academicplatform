package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.user.UserResponseDTO;
import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.exceptions.AdminUserAlreadyExistsException;
import com.armando.academicplatform.services.UserService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<UserResponseDTO> findAll(){
        return userService.findAll().stream().map( user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setUserName(user.getUsername());
            dto.setFullName(user.getName());
            dto.setRole(user.getRole().getName().name());
            dto.setEnabled(user.isEnabled());
            return dto;
        }).collect(Collectors.toList());
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);

        try {
            User savedUser = userService.save(user);
            return ResponseEntity.ok(savedUser);
        }catch (AdminUserAlreadyExistsException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
