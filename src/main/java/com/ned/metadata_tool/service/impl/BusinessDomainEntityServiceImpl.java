package com.ned.metadata_tool.service.impl;

import com.ned.metadata_tool.dto.BusinessDomainEntityDto;
import com.ned.metadata_tool.enums.Flag;
import com.ned.metadata_tool.helper.ModelDtoConverter;
import com.ned.metadata_tool.model.BusinessDomainEntity;
import com.ned.metadata_tool.model.ColumnBusinessDomainEntityMap;
import com.ned.metadata_tool.repo.BusinessDomainEntityRepository;
import com.ned.metadata_tool.repo.ColumnBusinessDomainEntityMapRepository;
import com.ned.metadata_tool.service.BusinessDomainEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessDomainEntityServiceImpl implements BusinessDomainEntityService {

    @Autowired
    BusinessDomainEntityRepository businessDomainEntityRepository;
    @Autowired
    ColumnBusinessDomainEntityMapRepository columnBusinessDomainEntityMapRepository;

    @Override
    public BusinessDomainEntityDto update(BusinessDomainEntityDto dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Optional<BusinessDomainEntity> dbOpt = businessDomainEntityRepository.findById(dto.getId());
        if (!dbOpt.isPresent()) {
            return null;
        } else {
            dbOpt.get().setName(dto.getName());
            dbOpt.get().setDescription(dto.getDescription());
            businessDomainEntityRepository.save(dbOpt.get());
            return ModelDtoConverter.BUSINESS_DOMAIN_ENTITY.modelToDto(dbOpt.get(), BusinessDomainEntityDto.class);
        }
    }

    @Override
    public void delete(long id) {
        Optional<BusinessDomainEntity> byId = businessDomainEntityRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setFlag(Flag.DELETED);
            businessDomainEntityRepository.save(byId.get());
        }
    }

    @Override
    public Boolean canDelete(Long id) {
        List<ColumnBusinessDomainEntityMap> columnEntityMap = columnBusinessDomainEntityMapRepository.findAllByBusinessDomainEntityId(id);
        if (!(columnEntityMap.isEmpty())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public BusinessDomainEntityDto add(BusinessDomainEntityDto Dto) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        BusinessDomainEntity o = ModelDtoConverter.BUSINESS_DOMAIN_ENTITY.dtoToModel(Dto, BusinessDomainEntity.class);
        businessDomainEntityRepository.save(o);
        return Dto;
    }

    @Override
    public Page<BusinessDomainEntityDto> find(Pageable pageable, Flag flag) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Page<BusinessDomainEntity> entities = businessDomainEntityRepository.findAllByFlag(pageable, flag);
        List<BusinessDomainEntityDto> list = new ArrayList<>();
        for (BusinessDomainEntity entity : entities) {
            if (entity.getFlag().equals(Flag.ACTIVE)) {
                BusinessDomainEntityDto entityDto = ModelDtoConverter.BUSINESS_DOMAIN_ENTITY.modelToDto(entity, BusinessDomainEntityDto.class);
                list.add(entityDto);
            }
        }
        return new PageImpl<>(list, pageable, entities.getTotalElements());
    }
}
