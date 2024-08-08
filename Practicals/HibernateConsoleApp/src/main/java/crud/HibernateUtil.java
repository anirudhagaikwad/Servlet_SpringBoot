package crud;
/* 
Create a Utility Class for Hibernate Session 
- Class Purpose: Provides a singleton `SessionFactory` for creating Hibernate `Session` objects.
- Static Initialization Block: Initializes the `SessionFactory` when the class is loaded.
- Method: Provides a static method to access the `SessionFactory`.
- Exception Handling: Ensures that any initialization errors are caught and rethrown as `ExceptionInInitializerError`.

This utility class is a common pattern in Hibernate applications to manage the lifecycle of the `SessionFactory` and to ensure it is only created once, improving efficiency and resource management.

### SessionFactory:
Interface: SessionFactory is an interface in Hibernate.
Primary Entry Point: It serves as the primary entry point for interacting with the Hibernate framework.
Creation of Session: Used to create Session objects for database operations.
Thread-Safe and Immutable: Once created, it is thread-safe and immutable.
Resource Management: Manages database connections, transactions, and caching.
*/
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

/*
### Purpose of the `HibernateUtil` Class
The `HibernateUtil` class is a utility class that provides a `SessionFactory` for managing Hibernate sessions. A `SessionFactory` is a thread-safe, immutable cache of compiled mappings for a single database. It is used to obtain `Session` objects, which are the primary interface for performing CRUD operations in Hibernate.

### Import Statements
These import statements bring in the necessary classes from the Hibernate framework:
1. `org.hibernate.SessionFactory`: Represents a factory for `Session` objects, which are used to interact with the database.
2. `org.hibernate.cfg.Configuration`: Configures Hibernate, typically using an XML configuration file or programmatically.


### `new Configuration().configure().buildSessionFactory()`:
  - `new Configuration()`: Creates a new `Configuration` object, which is used to configure Hibernate.
  - `.configure()`: Reads the `hibernate.cfg.xml` configuration file (by default) to set up the configuration.
  - `.buildSessionFactory()`: Builds a `SessionFactory` using the configuration settings.

### Method
- `public static SessionFactory getSessionFactory()`:
- Purpose: Provides a way to access the `SessionFactory` from other classes.

*/