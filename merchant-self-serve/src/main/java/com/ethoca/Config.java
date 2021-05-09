package com.ethoca;

import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.CmsSwaggerPoCApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CmsSwaggerPoCApi cmsSwaggerPoCApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:8080");
        apiClient.setDebugging(false);
        apiClient.setReadTimeout(5000);

        OkHttpClient.Builder okHttpClientBuilder = apiClient.getHttpClient().newBuilder();

        apiClient = apiClient.setHttpClient(okHttpClientBuilder.build());

        return new CmsSwaggerPoCApi(apiClient);
    }

}
