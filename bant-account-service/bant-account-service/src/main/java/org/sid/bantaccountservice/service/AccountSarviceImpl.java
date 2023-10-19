package org.sid.bantaccountservice.service;

import org.sid.bantaccountservice.dto.BankAccountRequestDTO;
import org.sid.bantaccountservice.dto.BankAccountResponseDTO;
import org.sid.bantaccountservice.entities.BankAccount;
import org.sid.bantaccountservice.mappers.AccountMapper;
import org.sid.bantaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountSarviceImpl implements AccountSarvice {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
       BankAccount saveBankAccount= bankAccountRepository.save(bankAccount);
      BankAccountResponseDTO bankAccountResponseDTO=accountMapper.fromBankAccount(saveBankAccount);
      
        return bankAccountResponseDTO;
    }
}
