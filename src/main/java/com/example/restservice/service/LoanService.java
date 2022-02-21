package com.example.restservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;

@Service
public class LoanService {

	public Loan getLoan(Long id) {
		return LoanGeneratonUtil.createLoan(id);
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		// Use the LoanMetricFactory based on the loan type
		return null;
	}

	public LoanMetric calculateLoanMetric(Long loanId) {
		// Call getLoan(loanId)
		return null;
	}

	public Loan getMaxMonthlyPaymentLoan() {
		List<Loan> allLoans = LoanGeneratonUtil.getRandomLoans(20L);
		// get the loan with the max monthly payment
		return null;
	}
}
