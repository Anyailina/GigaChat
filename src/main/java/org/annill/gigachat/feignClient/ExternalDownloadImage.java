package org.annill.gigachat.feignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.annill.gigachat.dto.RequestAiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "downloadImage")
public interface ExternalDownloadImage {
    @Headers({
            "Accept: application/jpg",
            "Authorization: Bearer {token}"
    })
    @RequestLine("GET /api/v1/files/{fileId}/content")
    ResponseEntity<ByteArrayResource> getImage(@Param("token") String token, @Param("fileId") String fileId);
}
