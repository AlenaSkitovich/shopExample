package com.example.spring152.services;

import com.example.spring152.config.WebSecurityConfig;
import com.example.spring152.models.RoleModel;
import com.example.spring152.models.UserModel;
import com.example.spring152.repos.RoleRepo;
import com.example.spring152.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public UserModel findUserById(Long userId) {
        Optional<UserModel> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new UserModel());
    }

    public List<UserModel> allUsers() {
        return userRepo.findAll();
    }

    public boolean saveUser(UserModel user) {
        UserModel userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new RoleModel(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<UserModel> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM UserModel u WHERE u.id > :paramId", UserModel.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
