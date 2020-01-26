package harish.task.app.repository;

import org.hibernate.SessionFactory;

public class TransactionRepository {
    private SessionFactory sessionFactory;
    public TransactionRepository(){
        sessionFactory = HibernateConfig.getSessionFactory();
    }

}
