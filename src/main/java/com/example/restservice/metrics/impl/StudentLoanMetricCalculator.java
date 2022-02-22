package com.example.restservice.metrics.impl;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class StudentLoanMetricCalculator implements ILoanMetricCalculator {

    @Override
    public boolean isSupported(Loan loan) {
        int borrowerAge = loan.getBorrower().getAge();
        return ILoanMetricCalculator.super.isSupported(loan) &&
                (18 < borrowerAge) && (borrowerAge < 30);
    }

    @Override
    public Double getMultiplier() {
        return 0.8;
    }
}
