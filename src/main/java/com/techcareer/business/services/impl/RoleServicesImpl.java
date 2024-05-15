package com.techcareer.business.services.impl;

import com.techcareer.bean.ModelMapperBeanClass;
import com.techcareer.business.dto.RoleDto;
import com.techcareer.business.services.IRoleService;
import com.techcareer.data.entity.RoleEntity;
import com.techcareer.data.repository.IRoleRepository;
import com.techcareer.exception.MustafaCaglarException;
import com.techcareer.exception.Resource404NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
@Component("roleServicesImpl")  //Componenti yazarsak artık springin bir parçasısın anlamına geliyor
public class RoleServicesImpl implements IRoleService<RoleDto, RoleEntity> {


    private final IRoleRepository iRoleRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;

        //Model Mapper
    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(roleEntity, RoleDto.class);
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapperBeanClass.modelMapperMethod().map(roleDto, RoleEntity.class);
    }

    // CRUD

    @Override
    @Transactional //Crete update delete kullanamak için yazarız
    public RoleDto roleServiceCreate(RoleDto roleDto) {
        RoleEntity roleEntity1;
        roleEntity1 = dtoToEntity(roleDto);
        roleEntity1.setRoleName(roleEntity1.getRoleName().toUpperCase());

        RoleEntity roleEntity2 = iRoleRepository.save(roleEntity1);

        //ID VE DATE Dto üzerinde Set yapıyorum
        roleDto.setRoleId(roleEntity2.getRoleId());
        roleDto.setSystemCreatedDate(roleEntity2.getSystemCreatedDate());

        return roleDto;
    }

    @Override
    public List<RoleDto> roleServiceList() {

        //Entity List
        List<RoleEntity> roleEntityList1 = iRoleRepository.findAll();
        //Dto List
        List<RoleDto> roleDtoList = new ArrayList<>();

        //Entity to Dto List
        for (RoleEntity tempEntity : roleEntityList1) {
            RoleDto roleDto1 = entityToDto(tempEntity);
            roleDtoList.add(roleDto1);
        }
        return roleDtoList;
    }

    @Override
    public RoleDto roleServiceFindById(Long id) {
        if (id == null) {
            throw new MustafaCaglarException("Roles Dto id boş değer geldi");
        }

        RoleEntity roleEntity = iRoleRepository.findById(id).orElseThrow(
                () -> new Resource404NotFoundException(id + " nolu ID Bulunamadı")
        );

        return entityToDto(roleEntity);
    }

    @Override
    @Transactional
    public RoleDto roleServiceUpdateById(Long id, RoleDto roleDto) {

        RoleDto roleDtoFind = roleServiceFindById(id);

        RoleEntity roleUpdateEntity = dtoToEntity(roleDtoFind);
        if(roleUpdateEntity!=null){
            roleUpdateEntity.setRoleName(roleDto.getRoleName());
            iRoleRepository.save(roleUpdateEntity);
        }


        roleDto.setRoleId(roleUpdateEntity.getRoleId());
        roleDto.setSystemCreatedDate(roleUpdateEntity.getSystemCreatedDate());


        return entityToDto(roleUpdateEntity);
    }

    @Override
    @Transactional
    public RoleDto roleServiceDeleteById(Long id) {

        RoleDto roleDtoFind = roleServiceFindById(id);

        RoleEntity roleDeleteEntity = dtoToEntity(roleDtoFind);
        if (roleDeleteEntity != null) {
            iRoleRepository.deleteById(id);
            return roleDtoFind;
        } else {
            throw new MustafaCaglarException(roleDtoFind+"nolu data silinemedi");
        }
    }
}
