package com.techcareer.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleApi<D> {

    //Role Crud
    //Role Create
    public ResponseEntity<?> roleServiceCreate(D d);

    //Role List
    public ResponseEntity<List<D>> roleServiceList();


    //Role Find ID
    public ResponseEntity<?> roleServiceFindById(Long id);

    //Role Update ID
    public ResponseEntity<?> roleServiceUpdateById(Long id,D d);

    //Role Delete ID
    public ResponseEntity<?>  roleServiceDeleteById(Long id);


}