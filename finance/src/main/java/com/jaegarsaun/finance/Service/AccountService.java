package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.DTO.AccountHistoryDTO;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.projection.AuditProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Account> findById(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    public Account saveOrUpdateAccount(Account account) {
        return accountRepository.save(account);
    }
    public Optional<Account> findByUserId(Integer userId) {
        return Optional.ofNullable(accountRepository.findByUserUserId(userId));
    }

    public AccountHistoryDTO getAccountHistory(Integer accountId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<Object[]> revisions = auditReader.createQuery()
                .forRevisionsOfEntity(Account.class, true, true)
                .add(AuditEntity.id().eq(accountId))
                .getResultList();

        List<AccountHistoryDTO.AccountChangeEntry> changeEntries = new ArrayList<>();

        for (Object[] revision : revisions) {
            Account accountRevision = (Account) revision[0];
            Number revisionNumber = (Number) revision[1];
            Date revisionDate = (Date) auditReader.getRevisionDate(revisionNumber.longValue());

            String description = "A change was made to the account.";

            changeEntries.add(new AccountHistoryDTO.AccountChangeEntry(
                    revisionDate,
                    "GENERAL_UPDATE", // General update type
                    null,
                    accountRevision.getBalance(),
                    description
            ));
        }

        return new AccountHistoryDTO(accountId, changeEntries);
    }



}

