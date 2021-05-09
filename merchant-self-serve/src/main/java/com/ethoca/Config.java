package com.ethoca;

import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.ClarityMerchantServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ClarityMerchantServiceApi clarityMerchantServiceApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://localhost:8080/api");
        apiClient.setDebugging(false);
        apiClient.setReadTimeout(50000);

        OkHttpClient.Builder okHttpClientBuilder = apiClient.getHttpClient().newBuilder();

        apiClient = apiClient.setHttpClient(okHttpClientBuilder.build());

        return new ClarityMerchantServiceApi(apiClient);
    }

}
