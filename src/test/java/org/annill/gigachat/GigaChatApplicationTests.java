package org.annill.gigachat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients
@EnableCaching
class GigaChatApplicationTests {

    @Test
    void contextLoads() {
    }

}
