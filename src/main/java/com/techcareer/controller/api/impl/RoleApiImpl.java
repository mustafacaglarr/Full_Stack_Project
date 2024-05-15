package com.techcareer.controller.api.impl;

import com.google.protobuf.Api;
import com.techcareer.business.dto.RoleDto;
import com.techcareer.business.services.IRoleService;
import com.techcareer.controller.api.IRoleApi;
import com.techcareer.error.ApiResult;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/role/api/v1.0.0")
@CrossOrigin

public class RoleApiImpl implements IRoleApi<RoleDto> {



    private final IRoleService iRoleService;

    private ApiResult apiResult;

    @PostMapping("/create")
    @Override
    public ResponseEntity<?> roleServiceCreate(@Valid @RequestBody RoleDto roleDtoData) {

        RoleDto roleCreateApi =(RoleDto) iRoleService.roleServiceCreate(roleDtoData);
        if(roleCreateApi==null){
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(404)
                    .error("role eklenmedi")
                    .message("Role Dto bulunamadı")
                    .path("localhost:4444/role/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultCreate);
        } else if (roleCreateApi.getRoleId()==0) {
            ApiResult apiResultCreate = ApiResult.builder()
                    .status(400)
                    .error("role eklenmedi")
                    .message("Role Dto bad request")
                    .path("localhost:4444/role/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(400).body(apiResultCreate);
        }

        log.info("Role Api Eklendi");

        return ResponseEntity.status(201).body(iRoleService.roleServiceCreate(roleDtoData));
    }

    @GetMapping("(/list")
    @Override
    public ResponseEntity<List<RoleDto>> roleServiceList() {
        log.info("Role Api Listelendi");

        return null;
    }

    @Override
    @GetMapping({"/find","/find/id"})
    public ResponseEntity<?> roleServiceFindById(@PathVariable(name = "id",required = false) Long id) {

        RoleDto roleFindApi =(RoleDto) iRoleService.roleServiceFindById(id);
        if(roleFindApi==null){

                ApiResult apiResultFind = ApiResult.builder()
                        .status(404)
                        .error("role Bulunamadı")
                        .message("Role Dto bulunamadı")
                        .path("localhost:4444/role/api/v1.0.0/find")
                        .createdDate(new Date(System.currentTimeMillis()))
                        .build();
                return ResponseEntity.status(404).body(apiResultFind);
        }
        log.info("Role Api Bulundu");

        return ResponseEntity.ok(iRoleService.roleServiceFindById(id));
    }

    @Override
    @PutMapping({"/update","/update/id"})
    public ResponseEntity<?> roleServiceUpdateById(@PathVariable(name = "id",required = false) Long id, @Valid @RequestBody RoleDto roleDto) {

        RoleDto roleUpdateApi =(RoleDto) iRoleService.roleServiceUpdateById(id,roleDto);
        if(roleUpdateApi==null){

            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("role Bulunamadı")
                    .message("Role Dto bulunamadı")
                    .path("localhost:4444/role/api/v1.0.0/update")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }

        log.info("Role Api Güncellendi");

        return ResponseEntity.ok(iRoleService.roleServiceUpdateById(id,roleDto));
    }

    @Override
    @DeleteMapping({"/delete","/delete/id"})
    public ResponseEntity<?> roleServiceDeleteById(@PathVariable(name = "id",required = false) Long id) {
        RoleDto roleDto =(RoleDto) iRoleService.roleServiceDeleteById(id);
        log.info("Role Api Silindi");

        return ResponseEntity.ok(iRoleService.roleServiceDeleteById(id));
    }
}
