package org.com.config;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsCodeConfig{
    @Value("${AccessKey.ID}")
    private String id;
    @Value("${AccessKey.Secret}")
    private String secret;
    @Bean
    public Client getClient() throws Exception {
        Config config=new Config()
                .setAccessKeyId(id)
                .setAccessKeySecret(secret);
        config.endpoint="dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
