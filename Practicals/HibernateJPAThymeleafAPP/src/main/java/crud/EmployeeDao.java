package crud;

import crud.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeePU");

    public void save(Employee employee) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Employee findById(int id) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();
        return employee;
    }

//    public List<Employee> findAll() {
//        EntityManager em = emf.createEntityManager();
//        List<Employee> employees = em.createQuery("from Employee", Employee.class).getResultList();
//        em.close();
//        return employees;
//    }

    public List<Employee> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }
    
    public void update(Employee employee) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
        em.getTransaction().commit();
        em.close();
    }
}
