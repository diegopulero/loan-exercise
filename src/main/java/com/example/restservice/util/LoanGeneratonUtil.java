package com.example.restservice.util;

import java.util.ArrayList;
import java.util.List;

import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;

public class LoanGeneratonUtil {

	public static String LOAN_TYPE_STUDENT = "student";
	public static String LOAN_TYPE_CONSUMER = "consumer";

	public static Loan createLoan(Long loanId) {
		String loanType = loanId % 2 == 0 ? LOAN_TYPE_STUDENT : LOAN_TYPE_CONSUMER;
		Borrower borrower = new Borrower();
		borrower.setName("Borrower ".concat(loanId.toString()));
		borrower.setAge(23);
		borrower.setAnnualIncome(150000D);
		borrower.setDelinquentDebt(true);
		borrower.setAnnualDebt(1200D);
		borrower.setCreditHistory(50);

		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setRequestedAmount(1000D * loanId);
		loan.setTermMonths(loanId % 2 == 0 ? 36 : 60);
		loan.setAnnualInterest(0.2 * (loanId / (loanId + 1)));
		loan.setType(loanType);
		loan.setBorrower(borrower);

		return loan;
	}
	
	public static List<Loan> getRandomLoans(Long numberOfLoans) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Integer x = 1; x <= numberOfLoans; x++) {
			loans.add(createLoan(Long.valueOf(x)));
		}
		return loans;
	}
}
