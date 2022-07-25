import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class PrintJDBCDrivers {
    public static void main(String[] args) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        if(drivers.asIterator().hasNext()){
            System.out.println(drivers.nextElement().toString());
        }
    }
}
