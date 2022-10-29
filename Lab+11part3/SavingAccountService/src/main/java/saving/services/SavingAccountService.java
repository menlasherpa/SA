package saving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saving.data.SavingAccountRepository;
import saving.domain.Account;
import saving.domain.Transaction;

import java.time.LocalDate;

@Service
public class SavingAccountService {
    @Autowired
    SavingAccountRepository savingAccountRepository;

    public void deposit(int accountNumber, double amount) {
        Account account = savingAccountRepository.findById(accountNumber);
        account.deposit(amount);
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account account = savingAccountRepository.findById(accountNumber);
        return account.withdraw(amount);
    }

    public double getBalance(int accountNumber) {
        Account account = savingAccountRepository.findById(accountNumber);
        return account.getBalance();
    }

    public void createAccount(SavingAccountDTO savingAccountDTO) {
        Account account = SavingAccountAdapter.fromSavingAccountDTOToSavingAccount(savingAccountDTO);
        savingAccountRepository.save(account);
    }

    public SavingAccountDTO getAccount(int accountNumber) {
        Account account = savingAccountRepository.findById(accountNumber);
        SavingAccountDTO savingAccountDTO = SavingAccountAdapter.fromSavingAccountToSavingAccountDTO(account);
        return savingAccountDTO;
    }
}
