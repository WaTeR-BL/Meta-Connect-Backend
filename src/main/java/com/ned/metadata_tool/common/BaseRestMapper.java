package com.ned.metadata_tool.common;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = {"http://localhost*"}, maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/meta-connect")
public interface BaseRestMapper {
}