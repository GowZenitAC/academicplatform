package com.armando.academicplatform.controllers;

import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.exceptions.AdminUserAlreadyExistsException;
import com.armando.academicplatform.services.UserService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

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
