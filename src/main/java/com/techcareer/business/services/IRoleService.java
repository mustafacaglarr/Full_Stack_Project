package com.techcareer.business.services;

import java.util.List;

public interface IRoleService<D,E> {

    //Model mapper
    public D entityToDto(E e);
    public E dtoToEntity(D d);


    //Role Crud




    //Role Create
    public D roleServiceCreate(D d);

    //Role List
    public List<D> roleServiceList();


    //Role Find ID
    public D roleServiceFindById(Long id);

    //Role Update ID
    public D roleServiceUpdateById(Long id,D d);

    //Role Delete ID
    public D roleServiceDeleteById(Long id);


}
