package org.sid.bantaccountservice.web;

import org.sid.bantaccountservice.dto.BankAccountRequestDTO;
import org.sid.bantaccountservice.dto.BankAccountResponseDTO;
import org.sid.bantaccountservice.entities.BankAccount;
import org.sid.bantaccountservice.mappers.AccountMapper;
import org.sid.bantaccountservice.repositories.BankAccountRepository;
import org.sid.bantaccountservice.service.AccountSarvice;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountSarvice accountSarvice;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountSarvice accountSarvice, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountSarvice = accountSarvice;
        this.accountMapper = accountMapper;
    }
    //pour consulter un list du compte
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    //pour consulter un compte
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    //pour ajouter un compte
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountSarvice.addAccount(requestDTO);

    }

    @PutMapping("/bankAccounts/{id}")//modifier tous les attributs mais patch va modifier que les attributs que envoyer dans la requete
    //pour faire update
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
       if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());//si different du null je veux inialiser
        if(bankAccount.getCreateAt()!=null) account.setCreateAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);

    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);
    }
}
