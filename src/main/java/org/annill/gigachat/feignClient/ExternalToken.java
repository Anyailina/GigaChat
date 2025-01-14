package org.annill.gigachat.feignClient;

import feign.RequestLine;
import org.annill.gigachat.configuration.TokenAiFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "aiAuth", configuration = TokenAiFeignConfig.class)
public interface ExternalToken {
    @RequestLine("POST /api/v2/oauth")
    String getAccessToken(@RequestBody String payLoad);
}
