package com.example.restservice.metrics;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

public interface ILoanMetricCalculator {

	/**
	 * Validates if a loan is supported to calculate metrics
	 * 
	 * @param loan
	 */
	public default boolean isSupported(Loan loan) {
		// Validate if the loan type is supported
		return true;
	}

	/**
	 * Calculates the Loan Metric of a Loan entity
	 * 
	 * @param loan
	 */
	public LoanMetric getLoanMetric(Loan loan);

}
