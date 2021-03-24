package com.user.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.account.entity.User;


public interface RepoLayer extends JpaRepository<User, Long> {

}
