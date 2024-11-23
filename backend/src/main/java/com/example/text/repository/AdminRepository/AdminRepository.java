package com.example.text.repository.AdminRepository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.text.model.AdminModel.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
//    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);
}