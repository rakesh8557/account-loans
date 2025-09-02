package com.guts.service;

import com.guts.dto.CustomerDTO;

public interface IAccountService {

     void createAccount(CustomerDTO customerDTO);

     CustomerDTO getAccountDetails(String mobileNumber);

     Boolean updateAccount(CustomerDTO customerDTO);

     Boolean deleteAccount(String mobileNumber);
}
