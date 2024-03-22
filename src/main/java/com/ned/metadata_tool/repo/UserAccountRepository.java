package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.model.UserAccount;
import com.ned.metadata_tool.model.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, UserId> {
    UserAccount findByUserIdAndPassword(UserId userId, String password);
    Page<UserAccount> findAll(Pageable pageable);
}
