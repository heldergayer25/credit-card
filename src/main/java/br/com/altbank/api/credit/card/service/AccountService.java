package br.com.altbank.api.credit.card.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import br.com.altbank.api.credit.card.domain.Account;
import br.com.altbank.api.credit.card.domain.Card;
import br.com.altbank.api.credit.card.domain.Customer;
import br.com.altbank.api.credit.card.dto.AccountDTO;
import br.com.altbank.api.credit.card.dto.AccountResponse;
import br.com.altbank.api.credit.card.enums.DeliveryStatus;
import br.com.altbank.api.credit.card.exception.BusinessException;
import br.com.altbank.api.credit.card.repository.AccountRepository;
import br.com.altbank.api.credit.card.repository.CardRepository;
import br.com.altbank.api.credit.card.repository.CustomerRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

//@Transactional
@ApplicationScoped
public class AccountService /*implements IAccountService*/ {
    
    @Inject 
    private CustomerRepository customerRepo;
    @Inject 
    private AccountRepository accountRepo;
    @Inject 
    private CardRepository cardRepo;

    /*@Transactional
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getCustomer().getName());
        customer.setDocument(request.getCustomer().getDocument());
        customer.persist();

        Account account = new Account();
        account.setCustomer(customer);
        account.setAgency(request.getAgency());
        account.setNumber(request.getNumber());
        account.persist();

        return new CreateAccountResponse(account.id, customer.id);
    }*/

    @Transactional    
    public AccountResponse createAccount(AccountDTO dto) throws BusinessException {
        this.validateIfActiveAccountExistsByDocument(dto.getCustomer().getDocument());
        // Cliente
        Customer customer = new Customer();
        customer.name = dto.getCustomer().getName();
        customer.email = dto.getCustomer().getEmail();
        customer.document = dto.getCustomer().getDocument();
        
        // Conta
        Account account = new Account();
        account.setCustomer(customer);   
        account.setAccountNumber(this.generateAccountNumber());     
        account.setAgency(this.generateAgencyNumber());

        // Cartão
        Card physical = new Card();
        physical.cardNumber = generateCardNumber();
        physical.type = "PHYSICAL";
        physical.account = account;
        physical.cvv = this.generateCVV();
        physical.trackingId = this.generateTracking();
        physical.deliveryStatus = DeliveryStatus.IN_TRANSIT.name();

        account.setCards(List.of(physical));
        this.customerRepo.persist(customer);
        this.accountRepo.persist(account);
        return new AccountResponse(account);
    }

    private void validateIfActiveAccountExistsByDocument(String document) throws BusinessException {
        boolean exists = Account.find("customer.document = ?1 and active = ?2", document, true)
                                .firstResultOptional()
                                .isPresent();
    
        if (exists) {
            throw new BusinessException("Já existe uma conta ativa para o documento informado: " + document);
        }
    }

    @Transactional
    public void cancelAccountByDocument(String document) {
        Account account = Account.find("customer.document", document).firstResult();
        if (account != null) {
            account.setActive(false);
            account.persist(); // Atualiza a conta no banco de dados
        } else {
            throw new RuntimeException("Conta não encontrada com documento: " + document);
        }
    }

    private void validateIfAccountExistsByDocument(String document) {
        PanacheQuery<Account> existingAccounts = Account.find("document", document);
        if (!existingAccounts.list().isEmpty()) {
            throw new IllegalStateException("Já existe uma conta para o documento informado: " + document);
        }
    }

    /*public Card createVirtualCard(Long accountId) {
        List<Card> cards = cardRepo.findByAccountId(accountId);
        boolean physicalValidated = cards.stream()
            .anyMatch(c -> c.type.equals("PHYSICAL") && c.validated);
        if (!physicalValidated) throw new IllegalStateException("Physical card not validated");

        Card virtual = new Card();
        virtual.cardNumber = generateCardNumber();
        virtual.type = "VIRTUAL";
        virtual.cvv = generateCVV();
        virtual.cvvExpiration = LocalDateTime.now().plusMinutes(30);
        virtual.account = accountRepo.findById(accountId);
        cardRepo.persist(virtual);
        return virtual;
    }*/

    private String generateCardNumber() {
        return this.randomNumber(16);
    }

    private String generateAccountNumber() {
        return String.format("%s-%s", this.randomNumber(6), this.randomNumber(1));
    }

    private String generateAgencyNumber() {
        return this.randomNumber(4);
    }

    private String randomDelivery(int index) {
        return UUID.randomUUID().toString().substring(0, index);
    }

    private String randomNumber(int index) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(index);
    
        for (int i = 0; i < index; i++) {
            sb.append(random.nextInt(10)); // Gera um número aleatório entre 0 e 9
        }
    
        return sb.toString();
    }

    private String generateCVV() {
        return String.valueOf(new Random().nextInt(900) + 100);
    }

    private String generateTracking() {
        return this.randomDelivery(32);
    }
}
