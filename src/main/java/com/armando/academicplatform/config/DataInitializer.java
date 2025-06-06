package com.armando.academicplatform.config;

import com.armando.academicplatform.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import com.armando.academicplatform.entities.Role;
import com.armando.academicplatform.entities.Role.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleName roleName : RoleName.values()){
                if (!roleRepository.findByName(roleName).isPresent()){
                    Role role = new Role();
                    role.setName(roleName);
                    roleRepository.save(role);
                    System.out.println("Rol creado: " + roleName);
                }else {
                    System.out.println("Rol ya existe");
                }
            }
        };
    }
}
