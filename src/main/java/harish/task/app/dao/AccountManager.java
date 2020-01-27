package harish.task.app.dao;

import harish.task.app.model.Account;
import harish.task.app.model.AccountTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountManager {
    private SessionFactory sessionFactory;
    public AccountManager(){
        sessionFactory = HibernateConfig.getSessionFactory();
    }

    public Long save(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long saved = (Long)session.save(account);
        transaction.commit();
        session.close();
        return saved;
    }

    public Optional<Account> findById(Long accountId) {
        Session session = sessionFactory.openSession();
        Account account = session.get(Account.class,accountId);
        session.close();
        return Optional.ofNullable(account);
    }

    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> rootEntry = cq.from(Account.class);
        CriteriaQuery<Account> all = cq.select(rootEntry);
        TypedQuery<Account> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Account.class,id));
        transaction.commit();
        session.close();
    }

    public void transfer(Account fromAccount, Account toAccount, Double amount) {
        Session session = sessionFactory.openSession();
        fromAccount.reduceBalance(amount);
        toAccount.addBalance(amount);
        UUID transactionLink = UUID.randomUUID();
        ZonedDateTime transactionTime = ZonedDateTime.now();

        Transaction transaction = session.beginTransaction();
        session.save(new AccountTransaction(fromAccount.getId(),transactionLink, transactionTime, -amount));
        session.save(new AccountTransaction(toAccount.getId(),transactionLink,transactionTime,amount));
        session.update(fromAccount);
        session.update(toAccount);
        transaction.commit();
        session.close();
    }
}
