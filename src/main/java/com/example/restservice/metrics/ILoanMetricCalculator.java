package com.example.restservice.metrics;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.model.LoanTypes;

public interface ILoanMetricCalculator {

    /**
     * Validates if a loan is supported to calculate metrics
     *
     * @param loan
     */
    public default boolean isSupported(Loan loan) {
        LoanTypes type = LoanTypes.byShortName(loan.getType());
        return type.getLoanMetricCalculatorClass().equals(this.getClass());
    }

    /**
     * Calculates the Loan Metric of a Loan entity
     *
     * @param loan
     */
    default public LoanMetric getLoanMetric(Loan loan) {
        Double monthlyInterestRate = getMonthlyInterestRate(loan);
        Double monthlyPayment = getMonthlyPayment(loan);
        return new LoanMetric(monthlyInterestRate, monthlyPayment);
    }

    default Double getMonthlyInterestRate(Loan loan) {
        return (loan.getAnnualInterest() / 12) / 100;
    }

    default Double getMonthlyPayment(Loan loan) {
        Double monthlyInterestRate = getMonthlyInterestRate(loan);
        return getMultiplier() * (loan.getRequestedAmount() * monthlyInterestRate) / (1 -
                Math.pow((1 + monthlyInterestRate), (-1) * loan.getTermMonths())
        );
    }

    default Double getMultiplier() {
        return 1D;
    }

}
