package com.user.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.account.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
