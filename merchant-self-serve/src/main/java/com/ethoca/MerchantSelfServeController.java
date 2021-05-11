package com.ethoca;

import com.merchant_self_serve.client.ApiException;
import com.merchant_self_serve.client.model.MssSubMerchant;
import com.merchant_self_serve.server.model.GatewayResponse;
import com.merchant_self_serve.server.model.GatewaySubMerchant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<GatewayResponse> addSubMerchants(@Valid @RequestBody GatewaySubMerchant gatewaySubMerchant) {

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
