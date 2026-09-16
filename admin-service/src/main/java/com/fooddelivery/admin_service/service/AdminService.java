package com.fooddelivery.admin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.admin_service.exception.InvalidCredentialsException;
import com.fooddelivery.admin_service.dto.AdminLoginRequest;
import com.fooddelivery.admin_service.entity.Admin;
import com.fooddelivery.admin_service.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin login(AdminLoginRequest loginRequest) {

    Admin admin = adminRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() ->
                new InvalidCredentialsException("Invalid email or password"));

    if (!admin.getPassword().equals(loginRequest.getPassword())) {
        throw new InvalidCredentialsException("Invalid email or password");
    }

    return admin;
    }

    

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin updateAdmin(Long id, Admin admin) {

        Admin existingAdmin = adminRepository.findById(id)
                .orElse(null);

        if (existingAdmin == null) {
            return null;
        }

        existingAdmin.setName(admin.getName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPassword(admin.getPassword());

        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}