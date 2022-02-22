package com.example.restservice.metrics;

import com.example.restservice.exception.BadRequestException;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LoanMetricFactory {

    private final Map<Class<? extends ILoanMetricCalculator>, ILoanMetricCalculator> loanMetricCalculatorsMap;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // Spring injects every ILoanMetricCalculator implementation in the list.
    public LoanMetricFactory(List<ILoanMetricCalculator> loanMetricCalculators) {
        // We map them to their class on construction.
        this.loanMetricCalculatorsMap = loanMetricCalculators.stream().collect(
                Collectors.toMap(ILoanMetricCalculator::getClass, Function.identity())
        );
    }

    public ILoanMetricCalculator getInstance(Loan loan) {
        logger.info("Getting calculator instance for loan: " + loan);

        LoanTypes type = LoanTypes.byShortName(loan.getType());
        ILoanMetricCalculator calculator = loanMetricCalculatorsMap.get(type.getLoanMetricCalculatorClass());

        if (calculator != null) {
            logger.info("Calculator " + calculator.getClass().getSimpleName() + " found.");
            if (calculator.isSupported(loan)) return calculator;
        }
        throw new BadRequestException(String.format("Loan of type '%s' is not supported.", loan.getType()));
    }

}
