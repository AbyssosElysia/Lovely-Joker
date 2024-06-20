package com.elysiaptr.cemenghuiweb.repo;

import com.elysiaptr.cemenghuiweb.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
