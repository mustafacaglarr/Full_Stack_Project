package com.techcareer.annotation;


import com.techcareer.data.repository.IRoleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UniqueRoleNameValidation implements ConstraintValidator<AnnotationUniqueRoleName,String> {
    private final IRoleRepository iRoleRepository;


    //Databasede rolName aynı veri var mı yok mu onu kontrol için
    @Override
    public boolean isValid(String rolName, ConstraintValidatorContext constraintValidatorContext) {

        Boolean isRolesFind = iRoleRepository.findByRoleName(rolName).isPresent();
        if(isRolesFind){
            return false;
        }
        return true;
    }
}
