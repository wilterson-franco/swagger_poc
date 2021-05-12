package com.ethoca;

import com.clarity_merchant_service.client.model.CmsSubMerchant;
import com.clarity_merchant_service.server.model.MssResponse;
import com.clarity_merchant_service.server.model.MssSubMerchant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClarityMerchantServiceController {

    @PostMapping("/cms-submerchants")
    public ResponseEntity<MssResponse> createSubMerchant(@Valid @RequestBody MssSubMerchant mssSubMerchant) {

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
