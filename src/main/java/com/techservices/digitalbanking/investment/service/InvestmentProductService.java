/* (C)2024 */
package com.techservices.digitalbanking.investment.service;

import com.techservices.digitalbanking.core.domain.dto.ProductDto;

public interface InvestmentProductService {

	ProductDto retrieveCurrentInvestmentProduct(String investmentType);
}
