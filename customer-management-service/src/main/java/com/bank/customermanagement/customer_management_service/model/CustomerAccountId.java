package com.bank.customermanagement.customer_management_service.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable // composite table
public class CustomerAccountId implements Serializable {

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "account_id")
	private Long accountId;

	public CustomerAccountId() {
	}

	public CustomerAccountId(Long customerId, Long accountId) {
		this.customerId = customerId;
		this.accountId = accountId;
	}

	// getters & setters

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CustomerAccountId))
			return false;
		CustomerAccountId that = (CustomerAccountId) o;
		return Objects.equals(customerId, that.customerId) && Objects.equals(accountId, that.accountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, accountId);
	}

}
