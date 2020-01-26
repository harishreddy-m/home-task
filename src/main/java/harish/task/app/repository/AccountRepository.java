package harish.task.app.repository;

import harish.task.app.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private SessionFactory sessionFactory;
    public AccountRepository(){
        sessionFactory = HibernateConfig.getSessionFactory();
    }

    public void save(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();
    }

    public Optional<Account> findById(Long accountId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
       Account account = session.get(Account.class,accountId);
        transaction.commit();
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

    public void delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Account.class,id));
        transaction.commit();
        session.close();
    }
}
