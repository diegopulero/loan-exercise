package com.example.restservice.controller;

import com.example.restservice.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@RestController("/loans")
public class LoanController {

	private LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/{loanId}")
	public Loan getLoan(@PathVariable Long loanId) {
		return loanService.getLoan(loanId);
	}

	public LoanMetric calculateLoanMetric(Long loanId) {
		return loanService.calculateLoanMetric(loanId);
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		return loanService.calculateLoanMetric(loan);
	}

	public Loan getMaxMonthlyPaymentLoan() {
		return loanService.getMaxMonthlyPaymentLoan();
	}

}
