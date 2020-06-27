package com.BlogBoot.repository;

import com.BlogBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT name FROM user WHERE id = :userId")
    String findNameById(int userId);
}
