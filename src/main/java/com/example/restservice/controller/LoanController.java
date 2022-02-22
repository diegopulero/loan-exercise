package com.example.restservice.controller;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(path = "/{loanId}")
    public Loan getLoan(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }

    @PostMapping("/metric")
    public LoanMetric calculateLoanMetric(@RequestBody Loan loan) {
        return loanService.calculateLoanMetric(loan);
    }

    @PostMapping("/{loanId}/metric")
    public LoanMetric calculateLoanMetric(@PathVariable Long loanId) {
        return loanService.calculateLoanMetric(loanId);
    }

    @GetMapping("/max-monthly-payment")
    public Loan getMaxMonthlyPaymentLoan() {
        return loanService.getMaxMonthlyPaymentLoan();
    }

}
