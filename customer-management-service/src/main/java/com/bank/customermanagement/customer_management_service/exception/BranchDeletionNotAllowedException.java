package com.bank.customermanagement.customer_management_service.exception;

// Run time exception - unchecked exception  - no need to declare throws
public class BranchDeletionNotAllowedException extends RuntimeException {

	public BranchDeletionNotAllowedException(String message) {
		super(message);
	}

}
