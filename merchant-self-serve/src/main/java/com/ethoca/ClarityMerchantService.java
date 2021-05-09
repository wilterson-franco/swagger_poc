package com.ethoca;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ClarityMerchantServiceApi;
import org.openapitools.client.model.GatewayResponse;
import org.openapitools.client.model.MssResponse;
import org.openapitools.client.model.MssSubMerchant;
import org.springframework.stereotype.Service;

@Service
public class ClarityMerchantService {

    private final ClarityMerchantServiceApi cmsApi;

    public ClarityMerchantService(ClarityMerchantServiceApi cmsApi) {
        this.cmsApi = cmsApi;
    }

    public GatewayResponse persistSubMerchant(MssSubMerchant mssSubMerchant) throws ApiException {

        // call
        MssResponse mssResponse = cmsApi.createSubMerchant(mssSubMerchant);
        GatewayResponse gatewayResponse = from(mssResponse);

        // Send SalesForce account number to ItemBase
        gatewayResponse.setSalesForceAccountNumber(mssSubMerchant.getSalesForceAccountNumber());

        return gatewayResponse;

    }

    private GatewayResponse from(MssResponse mssResponse) {
        GatewayResponse gatewayResponse = new GatewayResponse();
        gatewayResponse.setSubMerchantStatus(mssResponse.getStatus());
        return gatewayResponse;
    }
}
