package com.ethoca;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.CmsSwaggerPoCApi;
import org.openapitools.client.model.CMSSubMerchantRequestBody;
import org.openapitools.client.model.CMSSubMerchantResponseBody;
import org.springframework.stereotype.Service;

@Service
public class MerchantSelfServeService {

    private final CmsSwaggerPoCApi cmsApi;

    public MerchantSelfServeService(CmsSwaggerPoCApi cmsApi) {
        this.cmsApi = cmsApi;
    }

    public void doSomething() {
        CMSSubMerchantRequestBody requestBody = new CMSSubMerchantRequestBody();
        requestBody.setName("Wilterson");
        requestBody.setSubMerchantId("ABC123");

        try {
            CMSSubMerchantResponseBody resp = cmsApi.getCmsSubMerchants(requestBody);
            System.out.println(resp);
        } catch (ApiException exception) {
            System.out.println(exception);
        }
    }
}
