package com.hcl.mapper;

import org.mapstruct.Mapper;

import com.hcl.ecommerce.dto.PurchaseDto;
import com.hcl.ecommerce.dto.PurchaseResponseDto;
import com.hcl.ecommerce.entity.Purchase;
import com.hcl.ecommerce.entity.PurchaseResponse;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
	
	PurchaseDto toPurchaseDto(Purchase purchase);
	
	PurchaseResponseDto toPurchaseResponse(PurchaseResponse purchaseResponse);

}