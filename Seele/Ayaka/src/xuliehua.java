import java.io.*;;

public class xuliehua {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.name = "Mouse Jerry";
        e.address = "The World ACG";
        e.ssn = 114514;
        e.number = 10086;
        try{
            FileOutputStream f = new FileOutputStream("src/employee.ser");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(e);
            o.close();
            f.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i){
            i.printStackTrace();
        }
    }
}
