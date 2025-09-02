package com.guts.service.impl;

import com.guts.constants.LoanConstants;
import com.guts.dto.LoanDTO;
import com.guts.exception.ResourceAlreadyExistException;
import com.guts.exception.ResourceNotExistException;
import com.guts.mapper.LoanMapper;
import com.guts.modal.Loan;
import com.guts.repo.LoanRepo;
import com.guts.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Override
    public void createLoan(String mobileNumber) {
       Optional<Loan> optionalLoan = loanRepo.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()) {
            throw new ResourceAlreadyExistException("Loan", "mobileNumber", mobileNumber);
        }
        loanRepo.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoanConstants.HOME_LOAN);
        loan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        loan.setLoanNumber(Long.toString(randomNumber));
        return loan;
    }

    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan = loanRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotExistException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToDTO(loan, new LoanDTO());
    }

    @Override
    public boolean updateLoan(LoanDTO loanDTO) {
        Loan loan = loanRepo.findByMobileNumber(loanDTO.getMobileNumber())
                .orElseThrow(() -> new ResourceNotExistException("Loan", "mobileNumber", loanDTO.getMobileNumber()));
        loanRepo.save(LoanMapper.mapToEntityForUpdate(loanDTO, loan));
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepo.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotExistException("Loan", "mobileNumber", mobileNumber));
        loanRepo.deleteById(loan.getLoanId());
        return true;
    }
}
