package com.techcareer.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// @Component: BackendProfile nesnesi Spring nesnesi olması için
@Component
// application.properties => spring.profiles.active=backend
@Profile("backend")
public class BackendProfile implements IChooiseProfile{
    @Override
    public String message(String name) {
        return BackendProfile.class+" Profile: "+name;
    }
}