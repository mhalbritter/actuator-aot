package com.example.actuator_aot;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Endpoint(id = "custom")
@Component
class CustomEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEndpoint.class);

//	@ReadOperation
//	Dto read(@Nullable String firstName, @Nullable String lastName) {
//		LOGGER.info("read()");
//		return new Dto(firstName == null ? "Moritz" : firstName, lastName == null ? "Halbritter" : lastName);
//	}

//	@ReadOperation
//	Resource read() {
//		LOGGER.info("read() with Resource");
//		return new ByteArrayResource("Hello world".getBytes(StandardCharsets.UTF_8));
//	}

    @ReadOperation
    WebEndpointResponse<Dto> readWithDto() {
        return new WebEndpointResponse<>(new Dto("First", "Last"), 299, MediaType.APPLICATION_JSON);
    }

    @WriteOperation
    Dto write(@Nullable String firstName, @Nullable String lastName) {
        LOGGER.info("write()");
        return new Dto(firstName == null ? "Moritz" : firstName, lastName == null ? "Halbritter" : lastName);
    }

    @DeleteOperation
    Resource delete(@Nullable String firstName, @Nullable String lastName) {
        LOGGER.info("delete()");
        return new ByteArrayResource("Hello resource".getBytes(StandardCharsets.UTF_8));
    }
}
