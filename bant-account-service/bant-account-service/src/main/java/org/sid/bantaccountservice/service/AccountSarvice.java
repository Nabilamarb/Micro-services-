package org.sid.bantaccountservice.service;

import org.sid.bantaccountservice.dto.BankAccountRequestDTO;
import org.sid.bantaccountservice.dto.BankAccountResponseDTO;
import org.sid.bantaccountservice.entities.BankAccount;

public interface AccountSarvice {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
