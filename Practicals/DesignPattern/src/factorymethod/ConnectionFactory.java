package factorymethod;

import java.sql.Connection;
//Define a Factory Interface:
public interface ConnectionFactory {
    Connection createConnection();
}

