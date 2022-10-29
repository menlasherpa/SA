package clientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // Create a saving account
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO(123, "John Doe", "john@gmail.com");
        restTemplate.postForLocation("http://localhost:8091/saving",savingAccountDTO);

        // Deposit into account
        restTemplate.postForLocation("http://localhost:8091/deposit?accountNumber=123&amount=500", null);

        // Create a checking account
        CheckingAccountDTO checkingAccountDTO = new CheckingAccountDTO(533, "Jane Smith", "jane@gmail.com");
        restTemplate.postForLocation("http://localhost:8090/checking",checkingAccountDTO);

        // Deposit into account
        restTemplate.postForLocation("http://localhost:8090/deposit?accountNumber=533&amount=1500", null);

        //print accounts
        SavingAccountDTO savingAccount = restTemplate.getForObject("http://localhost:8091/saving/123" , SavingAccountDTO.class);
        CheckingAccountDTO checkingAccount = restTemplate.getForObject("http://localhost:8090/checking/533" , CheckingAccountDTO.class);
        System.out.println("Saving account"+ savingAccount);
        System.out.println("Checking account"+ checkingAccount);

        System.out.println("Transfer money");
        // Deposit into account
        restTemplate.postForLocation("http://localhost:8090/deposit?accountNumber=533&amount=700", null);
        String result= restTemplate.postForObject("http://localhost:8091/withdraw?accountNumber=123&amount=700",null, String.class);
        if(result.equals("not enough balance")){
            System.out.println("compensating action");
            restTemplate.postForLocation("http://localhost:8090/withdraw?accountNumber=533&amount=700", null); //compensate
        }

        //print accounts
        savingAccount = restTemplate.getForObject("http://localhost:8091/saving/123" , SavingAccountDTO.class);
        checkingAccount = restTemplate.getForObject("http://localhost:8090/checking/533" , CheckingAccountDTO.class);
        System.out.println("Saving account"+ savingAccount);
        System.out.println("Checking account"+ checkingAccount);
    }


    @Bean
    RestOperations restTemplate() {
        return new RestTemplate();
    }
}
