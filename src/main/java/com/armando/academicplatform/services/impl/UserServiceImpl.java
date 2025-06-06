package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.entities.Role;
import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.exceptions.AdminUserAlreadyExistsException;
import com.armando.academicplatform.repositories.RoleRepository;
import com.armando.academicplatform.repositories.UserRepository;
import com.armando.academicplatform.entities.Role.RoleName;
import com.armando.academicplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        //se determina el tipo de rol
        RoleName targetRoleName;
        if (user.getRole() == null || user.getRole().getName() == null)
            targetRoleName = user.isAdmin() ? RoleName.ROLE_ADMIN : RoleName.ROLE_STUDENT;
         else
            targetRoleName = user.getRole().getName();

        //aca se valida si ya hay alguien con él role de admin
        if (targetRoleName == RoleName.ROLE_ADMIN && userRepository.existsByRoleName(RoleName.ROLE_ADMIN)) {
            throw new AdminUserAlreadyExistsException("Ya existe un usuario con el rol ROLE_ADMIN");
        }


            Optional<Role> optionalRole = roleRepository.findByName(targetRoleName);
            optionalRole.ifPresentOrElse(user::setRole, () -> {
                throw new RuntimeException("Rol '" + targetRoleName + "' no encontrado");
            });


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        return  userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
