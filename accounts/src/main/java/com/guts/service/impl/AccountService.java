package com.guts.service.impl;

import com.guts.constants.AccountConstants;
import com.guts.dto.AccountsDTO;
import com.guts.dto.CustomerDTO;
import com.guts.exception.CustomerAlreadyExistException;
import com.guts.exception.ResourceNotExistException;
import com.guts.mapper.AccountMapper;
import com.guts.mapper.CustomerMapper;
import com.guts.modal.Accounts;
import com.guts.modal.Customer;
import com.guts.repo.AccountsRepo;
import com.guts.repo.CustomerRepo;
import com.guts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer =  customerRepo.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exists with mobile number " + customerDTO.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Customer savedCustomer = customerRepo.save(customer);
        accountsRepo.save(createAccount(savedCustomer));
    }

    @Override
    public CustomerDTO getAccountDetails(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotExistException("Customer", "mobileNumber" , mobileNumber));
        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotExistException("Account", "customerId" , customer.getCustomerId().toString()));
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDetails(AccountMapper.mapToAccountsDTO(accounts, new AccountsDTO()));
        return customerDTO;
    }

    @Override
    public Boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        if(customerDTO.getAccountDetails() != null) {
            Accounts accounts = accountsRepo.findById(customerDTO.getAccountDetails().getAccountNumber())
                    .orElseThrow( ()-> new ResourceNotExistException("Account", "AccountNumber", customerDTO.getAccountDetails().getAccountNumber().toString()));
            accountsRepo.save(AccountMapper.mapToAccounts(customerDTO.getAccountDetails(), accounts));
            Customer customer = customerRepo.findById(accounts.getCustomerId())
                    .orElseThrow( ()-> new ResourceNotExistException("Customer", "mobileNumber", customerDTO.getMobileNumber()));
            customerRepo.save(CustomerMapper.mapToCustomer(customerDTO, customer));
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotExistException("Customer", "mobileNumber" , mobileNumber));
        customerRepo.deleteById(customer.getCustomerId());
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        return true;
    }

    private Accounts createAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long accountNumber = (long) (Math.random() * 9000000000L) + 1000000000L;
        accounts.setAccountNumber(accountNumber);
        accounts.setAccountType(AccountConstants.SAVINGS);
        accounts.setBranchAddress(AccountConstants.ADDRESS);
        return accounts;
    }
}
