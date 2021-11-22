package com.b.simple.design.business.customer;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerBOTest {

	private CustomerBO customerBO;

	@BeforeEach
	public void  setup() {
		customerBO = new CustomerBOImpl();
	}

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {
		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
				new AmountImpl(new BigDecimal("6.0"), Currency.EURO)
		};
		List<Product> products = createProductList(amounts);

		Amount temp = customerBO.getCustomerProductsSum(products);

		Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
		assertAmount(expected, temp);
	}

	@Test
	public void customerProductSum_throwExceptionForDifferentCurrency() {
		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE),
				new AmountImpl(new BigDecimal("6.0"), Currency.EURO)
		};
		List<Product> products = createProductList(amounts);

		Assertions.assertThrows(DifferentCurrenciesException.class,
				() -> customerBO.getCustomerProductsSum(products));
	}

	@Test
	public void testCustomerProductSum_emptyProducts() throws DifferentCurrenciesException {
		List<Product> products = new ArrayList<>();

		Amount temp = customerBO.getCustomerProductsSum(products);

		Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);
		assertAmount(expected, temp);
	}

	private void assertAmount(Amount expected, Amount actual) {
		assertEquals(expected.getCurrency(), actual.getCurrency());
		assertEquals(expected.getValue(), actual.getValue());
	}

	private List<Product> createProductList(Amount[] amounts) {
		return Arrays.stream(amounts)
				.map(amount -> new ProductImpl(120, "Product 20", ProductType.BANK_GUARANTEE,
						amount
				)).collect(Collectors.toList());
	}

	@AfterEach
	public void cleanUp() {
		customerBO = null;
	}
}