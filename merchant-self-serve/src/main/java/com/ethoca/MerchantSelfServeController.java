package com.ethoca;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.GatewayResponse;
import org.openapitools.client.model.GatewaySubMerchant;
import org.openapitools.client.model.MssSubMerchant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MerchantSelfServeController {

    private final ClarityMerchantService clarityMerchantService;
    private final SalesForceService salesForceService;

    public MerchantSelfServeController(ClarityMerchantService clarityMerchantService, SalesForceService salesForceService) {
        this.clarityMerchantService = clarityMerchantService;
        this.salesForceService = salesForceService;
    }

    @PostMapping("/mss-submerchants")
    public ResponseEntity<GatewayResponse> addSubMerchants(@RequestBody GatewaySubMerchant gatewaySubMerchant) {

        MssSubMerchant mssSubMerchant = new MssSubMerchant();
        mssSubMerchant.setName(gatewaySubMerchant.getName());
        mssSubMerchant.setSubMerchantId(gatewaySubMerchant.getSubMerchantId());

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
