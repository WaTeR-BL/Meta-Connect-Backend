package com.ned.metadata_tool.repo;

import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.BusinessDomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusinessDomainEntityRepository extends JpaRepository<BusinessDomainEntity, Long> {
    Page<BusinessDomainEntity> findAllByFlag(Pageable pageable, Flag flag);
}
