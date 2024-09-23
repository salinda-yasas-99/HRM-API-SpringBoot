package com.hrm.human.resource.management.system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "Alibou",
                email="contact@gmail.com",
                url ="abc.com"
        ),
        description = "Open API doucmentation for HRM",
        title="Diana shoes -HRM API",
        version = "1.0",
        license = @License(
                name="Licencse name",
                url="abc.com"
        ),
        termsOfService = "Terms of Servvie"

        ) ,
        servers =
                {
                    @Server(
                            description= "Local ENV",
                            url = "httpU/tocaIhost;8080"
                    ),
                    @Server(
                            description="PROD ENVI",
                            url = "https : //atib@ucoding.com/course"
                    )
                },
        security = {
            @SecurityRequirement(
                    name = "bearerAuth"
            )
        }

)
@SecurityScheme(
                name="bearerAuth",
                description = "JWT AUTH description",
                scheme="bearer",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
