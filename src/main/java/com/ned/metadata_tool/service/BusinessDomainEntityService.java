package com.ned.metadata_tool.service;

import com.ned.metadata_tool.dto.BusinessDomainEntityDto;
import com.ned.metadata_tool.dto.DBConfigDto;
import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.model.BusinessDomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;

public interface BusinessDomainEntityService {

    BusinessDomainEntityDto update(BusinessDomainEntityDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;
    void delete(long id);
    Boolean canDelete(Long id);
    BusinessDomainEntityDto add(BusinessDomainEntityDto Dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;
    Page<BusinessDomainEntityDto> find(Pageable pageable, Flag flag) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

}
