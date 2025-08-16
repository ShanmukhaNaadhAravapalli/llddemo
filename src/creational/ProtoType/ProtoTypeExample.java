package creational.ProtoType;

import java.util.ArrayList;
import java.util.List;

class Employees implements Cloneable {

    private List<String> empList;

    public Employees() {
        empList = new ArrayList<String>();
    }

    public Employees(List<String> list) {
        this.empList = list;
    }

    public void loadData() {
        //read all employees from database and put into the list
        empList.add("Pankaj");
        empList.add("Raj");
        empList.add("David");
        empList.add("Lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<String>(this.empList);
        return new Employees(temp);
    }
}
public class ProtoTypeExample {
    public static void main(String[] args) {
        try {
            Employees original = new Employees();
            original.loadData();
            System.out.println("Original Object: " + original);

            // Creating a deep clone
            Employees cloned = (Employees) original.clone();
            System.out.println("Cloned Object (before modification): " + cloned);

            // Modify the cloned object
//            cloned.getEmpList().add("Peter");
            System.out.println("\nCloned Object (after modification): " + cloned);
            System.out.println("Original Object (after clone modification): " + original);

            // Verify that the lists are independent
            System.out.println("\nAre the lists the same object? " +  (original.getEmpList() == cloned.getEmpList()));
            System.out.println("\nAre the lists the same elements? " +  original.getEmpList().equals(cloned.getEmpList()));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
