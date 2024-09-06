package crud;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The `HibernateUtil` class is a utility class that provides a singleton `SessionFactory` 
 * for managing Hibernate sessions throughout the application.
 */
public class HibernateUtil {
    // Singleton instance of SessionFactory
    private static SessionFactory sessionFactory;

    // Static initializer block to configure and build the SessionFactory
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml configuration file
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Handle any errors during the creation of SessionFactory
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the singleton `SessionFactory` instance.
     * This method can be called throughout the application to obtain the `SessionFactory`.
     *
     * @return SessionFactory
     */
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
- Purpose: The getSessionFactory() method provides access to the singleton SessionFactory instance. 
		   This method ensures that only one SessionFactory is used throughout the application, which is crucial for performance and resource management.
*/
