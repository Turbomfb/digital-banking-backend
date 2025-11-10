/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import com.techservices.digitalbanking.core.eBanking.service.LockDepositAccountService;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.investment.service.InvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentProductServiceImpl implements InvestmentProductService {
	private final FlexDepositAccountService flexDepositAccountService;
	private final LockDepositAccountService lockDepositAccountService;

	@Override
	public ProductDto retrieveCurrentInvestmentProduct(String investmentType) {
		if (InvestmentType.FLEX.name().equals(investmentType)) {
			return flexDepositAccountService.retrieveFlexProductDetails();
		} else if (InvestmentType.LOCK.name().equals(investmentType)) {
			return lockDepositAccountService.retrieveLockProductDetails();
		} else {
			throw new ValidationException("invalid.investment.type", "The investment type provided is invalid");
		}
	}
}
