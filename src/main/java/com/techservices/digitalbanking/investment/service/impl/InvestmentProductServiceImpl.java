/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutRecurringDepositProductResponse;
import com.techservices.digitalbanking.investment.service.InvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentProductServiceImpl implements InvestmentProductService {
	private final FlexDepositAccountService flexDepositAccountService;
	private final FineractProperty fineractProperty;

	@Override
	public Object retrieveCurrentInvestmentProduct(String investmentType) {
		if (InvestmentType.FLEX.name().equals(investmentType)) {
			return flexDepositAccountService.retrieveAProduct(fineractProperty.getDefaultRecurringDepositProductId());
		} else if (InvestmentType.LOCK.name().equals(investmentType)) {
			return flexDepositAccountService.retrieveAProduct(fineractProperty.getDefaultFixedDepositProductId());
		} else {
			throw new ValidationException("invalid.investment.type", "The investment type provided is invalid");
		}
	}

	@Override
	public GetRecurringDepositProductProductIdResponse retrieveInvestmentProductById(String productId) {
		return flexDepositAccountService.retrieveAProduct(productId);
	}
}
