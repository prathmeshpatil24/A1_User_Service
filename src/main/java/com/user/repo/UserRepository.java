package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.user.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

}
