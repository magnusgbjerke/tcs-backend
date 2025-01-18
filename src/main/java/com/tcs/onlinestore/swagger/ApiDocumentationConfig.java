package com.tcs.onlinestore.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

import static com.tcs.onlinestore.swagger.Description.DESCRIPTION_TEXT;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "The Clothing Store API",
                version = "1.0.0",
                description = DESCRIPTION_TEXT,
                contact = @Contact(
                        name = "The Clothing Store",
                        email = "contact@tcs-onlinestore.com",
                        url = "http://www.tcs-onlinestore.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
public class ApiDocumentationConfig {
}
