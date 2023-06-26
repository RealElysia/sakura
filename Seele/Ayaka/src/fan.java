import java.io.*;

public class fan {
    public static void main(String[] args) {
        Employee e = null;
        try{
            FileInputStream f = new FileInputStream("src/employee.ser");
            ObjectInputStream o = new ObjectInputStream(f);
            e = (Employee) o.readObject();
            f.close();
            o.close();
        }catch(IOException io){
            io.printStackTrace();
            return;
        }catch(ClassNotFoundException cls){
            System.out.println("Employee class not found");
            cls.printStackTrace();
            return;
        }
      System.out.println("Deserialized Employee...");
      System.out.println("Name: " + e.name);
      System.out.println("Address: " + e.address);
      System.out.println("SSN: " + e.ssn);
      System.out.println("Number: " + e.number);
    }
}
