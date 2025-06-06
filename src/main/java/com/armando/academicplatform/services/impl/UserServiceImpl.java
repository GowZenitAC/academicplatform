package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.entities.Role;
import com.armando.academicplatform.entities.User;
import com.armando.academicplatform.exceptions.AdminUserAlreadyExistsException;
import com.armando.academicplatform.exceptions.InvalidRoleException;
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
    @Transactional
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        // Validar que el rol esté especificado
        if (user.getRole() == null || user.getRole().getName() == null) {
            throw new InvalidRoleException("Debe especificar un rol (ROLE_STUDENT o ROLE_TEACHER)");
        }

        RoleName targetRoleName = user.getRole().getName();
        // Validar rol permitido
        if (targetRoleName != RoleName.ROLE_STUDENT && targetRoleName != RoleName.ROLE_TEACHER) {
            throw new InvalidRoleException("Solo se permiten los roles: ROLE_STUDENT o ROLE_TEACHER");
        }

        // Validar rol ADMIN único (si aplica)
//        if (targetRoleName == RoleName.ROLE_ADMIN && userRepository.existsByRoleName(RoleName.ROLE_ADMIN)) {
//            throw new AdminUserAlreadyExistsException("Ya existe un usuario con el rol ROLE_ADMIN");
//        }


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
