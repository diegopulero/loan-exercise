package com.example.restservice.service;

import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanMetricFactory loanMetricFactory;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public LoanService(LoanMetricFactory loanMetricFactory) {
        this.loanMetricFactory = loanMetricFactory;
    }

    public Loan getLoan(Long id) {
        logger.info("Getting loan with id " + id);
        return LoanGeneratonUtil.createLoan(id);
    }

    public LoanMetric calculateLoanMetric(Loan loan) {
        logger.info("Calculating loan metric for loan: " + loan);
        return loanMetricFactory.getInstance(loan).getLoanMetric(loan);
    }

    public LoanMetric calculateLoanMetric(Long loanId) {
        return calculateLoanMetric(getLoan(loanId));
    }

    public Loan getMaxMonthlyPaymentLoan() {
        logger.info("Getting max monthly payment loan");
        List<Loan> allLoans = LoanGeneratonUtil.getRandomLoans(20L);

        // Use the max terminal stream operation to get the top payment.
        Optional<Loan> max = allLoans.stream().max(
                Comparator.comparingDouble(
                        loan -> {
                            Double monthlyPayment = loanMetricFactory.getInstance(loan).getMonthlyPayment(loan);
                            logger.info("Monthly payment is " + monthlyPayment.toString());
                            return monthlyPayment;
                        }
                )
        );
        return max.orElse(null);
    }
}
