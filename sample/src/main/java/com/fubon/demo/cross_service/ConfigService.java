package com.fubon.demo.cross_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "config-service/config")
public interface ConfigService {
	@RequestMapping(value = "/actuator/health", method = RequestMethod.GET )
	String healthCheck();
}
