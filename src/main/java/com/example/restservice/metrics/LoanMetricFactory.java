package com.example.restservice.metrics;

import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory {
   
   public ILoanMetricCalculator getInstance(Loan loan) {
      return null;
   }

}
