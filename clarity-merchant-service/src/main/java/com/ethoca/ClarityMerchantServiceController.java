package com.ethoca;

import org.openapitools.client.model.CmsSubMerchant;
import org.openapitools.client.model.MssResponse;
import org.openapitools.client.model.MssSubMerchant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClarityMerchantServiceController {

    @PostMapping("/cms-submerchants")
    public ResponseEntity<MssResponse> getCmsSubMerchants(MssSubMerchant mssSubMerchant) {

        CmsSubMerchant cmsSubMerchant = from(mssSubMerchant);

        cmsSubMerchant.setStatus("ACTIVE");
        cmsSubMerchant.setMerchantType("SUB_MERCHANT");

        save(cmsSubMerchant);

        MssResponse response = new MssResponse();
        response.setStatus(cmsSubMerchant.getStatus());

        return ResponseEntity
                .ok()
                .body(response);
    }

    private CmsSubMerchant from(MssSubMerchant gatewaySubMerchant) {
        CmsSubMerchant cmsSubMerchant = new CmsSubMerchant();
        cmsSubMerchant.setName(gatewaySubMerchant.getName());
        cmsSubMerchant.setSubMerchantId(gatewaySubMerchant.getSubMerchantId());
        cmsSubMerchant.setSalesForceAccountNumber(gatewaySubMerchant.getSalesForceAccountNumber());
        return cmsSubMerchant;
    }

    private void save(CmsSubMerchant cmsSubMerchant) {
        System.out.println("Saving:");
        System.out.println("Name: " + cmsSubMerchant.getName());
        System.out.println("Id: " + cmsSubMerchant.getSubMerchantId());
        System.out.println("Status: " + cmsSubMerchant.getStatus());
        System.out.println("SF Account Numb: " + cmsSubMerchant.getSalesForceAccountNumber());
        System.out.println("Merchant Type: " + cmsSubMerchant.getMerchantType());
    }
}
