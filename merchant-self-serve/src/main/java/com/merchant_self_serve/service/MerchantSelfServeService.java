package com.merchant_self_serve.service;

import com.merchant_self_serve.client.ApiException;
import com.merchant_self_serve.client.model.MssSubMerchant;
import com.merchant_self_serve.server.api.MssSubmerchantsApiDelegate;
import com.merchant_self_serve.server.model.GatewayResponse;
import com.merchant_self_serve.server.model.GatewaySubMerchant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantSelfServeService implements MssSubmerchantsApiDelegate {

    private final ClarityMerchantService clarityMerchantService;
    private final SalesForceService salesForceService;

    public MerchantSelfServeService(ClarityMerchantService clarityMerchantService, SalesForceService salesForceService) {
        this.clarityMerchantService = clarityMerchantService;
        this.salesForceService = salesForceService;
    }

    @Override
    public ResponseEntity<GatewayResponse> addSubMerchants(GatewaySubMerchant gatewaySubMerchant) {

        MssSubMerchant mssSubMerchant = new MssSubMerchant();
        mssSubMerchant.setName(gatewaySubMerchant.getName());
        mssSubMerchant.setSubMerchantId(gatewaySubMerchant.getSubMerchantId());
        mssSubMerchant.setNewProp(gatewaySubMerchant.getNewProp());

        // Get account number from SalesForce and set it here
        // in order to send it to Clarity Merchant Service
        mssSubMerchant.setSalesForceAccountNumber(salesForceService.createAccount());

        try {

            GatewayResponse gatewayResponse = clarityMerchantService.persistSubMerchant(mssSubMerchant);
            return ResponseEntity
                    .ok()
                    .body(gatewayResponse);

        } catch (ApiException apiException) {
            System.out.println(apiException);
        }

        throw new NullPointerException("Invalid request");

    }

}
