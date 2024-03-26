package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUserName(String username);
    Page<UserAccount> findAll(Pageable pageable);
}
