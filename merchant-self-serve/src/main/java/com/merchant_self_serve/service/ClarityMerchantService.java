package com.merchant_self_serve.service;

import com.merchant_self_serve.client.ApiException;
import com.merchant_self_serve.client.api.ClarityMerchantServiceApi;
import com.merchant_self_serve.client.model.MssResponse;
import com.merchant_self_serve.client.model.MssSubMerchant;
import com.merchant_self_serve.server.model.GatewayResponse;
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
