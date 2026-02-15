package com.bank.customermanagement.customer_management_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "BRANCH_MASTER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class BranchMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BRANCH_ID")
	private Long branchId;

	@Column(name = "BRANCH_CODE", nullable = false, unique = true, length = 20)
	private String branchCode;

	@Column(name = "BRANCH_NAME", nullable = false, length = 100)
	private String branchName;

	@Column(name = "IFSC_CODE", nullable = false, unique = true, length = 20)
	private String ifscCode;

	@Column(name = "BANK_CODE", length = 20)
	private String bankCode;

	@Column(name = "CITY", length = 50)
	private String city;

	@Column(name = "STATE", length = 50)
	private String state;

	@Column(name = "COUNTRY", length = 50)
	private String country;

	@Column(name = "PIN_CODE", length = 10)
	private String pinCode;

	@Column(name = "STATUS", length = 20)
	private String status;

	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
		updatedDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = LocalDateTime.now();
	}

}
// Branch master data is static, so we cache it using Hibernate second-level cache or Spring cache.