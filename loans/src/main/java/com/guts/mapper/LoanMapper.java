package com.guts.mapper;

import com.guts.dto.LoanDTO;
import com.guts.modal.Loan;

public class LoanMapper {

    public static LoanDTO mapToDTO(Loan loan, LoanDTO loanDTO) {
        loanDTO.setMobileNumber(loan.getMobileNumber());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setTotalLoan(loan.getTotalLoan());
        loanDTO.setAmountPaid(loan.getAmountPaid());
        loanDTO.setOutstandingAmount(loan.getOutstandingAmount());
        loanDTO.setLoanNumber(loan.getLoanNumber());
        return loanDTO;
    }

    public static Loan mapToEntity(LoanDTO loanDTO, Loan loan) {
        loan.setMobileNumber(loanDTO.getMobileNumber());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setTotalLoan(loanDTO.getTotalLoan());
        loan.setAmountPaid(loanDTO.getAmountPaid());
        loan.setOutstandingAmount(loanDTO.getOutstandingAmount());
        loan.setLoanNumber(loanDTO.getLoanNumber());
        return loan;
    }

    public static Loan mapToEntityForUpdate(LoanDTO loanDTO, Loan loan) {
        loan.setAmountPaid(loanDTO.getAmountPaid());
        loan.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loan;
    }

}
