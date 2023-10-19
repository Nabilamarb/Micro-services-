package org.sid.bantaccountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bantaccountservice.enums.AccountType;

import javax.persistence.EnumType;
@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class BankAccountRequestDTO {
    private Double balance;
    private String currency;
    private AccountType type;
}
