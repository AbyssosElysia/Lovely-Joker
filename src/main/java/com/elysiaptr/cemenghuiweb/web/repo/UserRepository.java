package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // 根据用户名模糊查询
    List<User> findByUsernameContaining(String username);
    // 根据手机号模糊查询
//    List<User> findByMobileContaining(String mobile);
    List<User> findByMobile(Long mobile);
    //根据状态查询
    List<User> findByStatus(int status);
    //根据创建时间查询
    List<User> findByTime(LocalDateTime time);
}
