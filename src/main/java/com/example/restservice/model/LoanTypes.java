package com.example.restservice.model;

import com.example.restservice.exception.BadRequestException;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;

import java.util.Arrays;

public enum LoanTypes {
    LOAN_TYPE_STUDENT("student", StudentLoanMetricCalculator.class),
    LOAN_TYPE_CONSUMER("consumer", ConsumerLoanMetricCalculator.class);

    private final String shortName;
    private final Class<? extends ILoanMetricCalculator> loanMetricCalculatorClass;

    LoanTypes(String shortName, Class<? extends ILoanMetricCalculator> loanMetricCalculatorClass) {
        this.shortName = shortName;
        this.loanMetricCalculatorClass = loanMetricCalculatorClass;
    }

    public String getShortName() {
        return shortName;
    }

    public Class<? extends ILoanMetricCalculator> getLoanMetricCalculatorClass() {
        return loanMetricCalculatorClass;
    }

    public static LoanTypes byShortName(String shortName) {
        return Arrays.stream(LoanTypes.values())
                .filter(type -> shortName.equals(type.shortName))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("Unidentified loan type '%s'", shortName)));
    }
}
