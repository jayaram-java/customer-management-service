package com.bank.customermanagement.customer_management_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.customermanagement.customer_management_service.model.BranchMaster;

@Repository
public interface BranchMasterRepository extends JpaRepository<BranchMaster, Long> {

	Optional<BranchMaster> findByBranchCode(String branchCode);
	
	// select * from branch where branchcode = "ICIC001"

	Optional<BranchMaster> findByIfscCode(String ifscCode);

	List<BranchMaster> findByStatus(String status);

}
