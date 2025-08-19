package behavioral;
abstract class Approver {
    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    public abstract void processLeaveRequest(int leaveDays);
}
class Supervisor extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays){
        if(leaveDays<= 3)
            System.out.println("Supervisor approved the leave.");
        else  if( nextApprover!= null)
            nextApprover.processLeaveRequest(leaveDays);
    }
}
class Manager extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays){
        if(leaveDays<= 7)
            System.out.println("Manager approved the leave.");
        else  if( nextApprover!= null)
            nextApprover.processLeaveRequest(leaveDays);
    }
}
class Director extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays){
        if(leaveDays<= 14)
            System.out.println("Director approved the leave.");
        else  if( nextApprover!= null)
            nextApprover.processLeaveRequest(leaveDays);
        else
            System.out.println("Leave request denied. Too many days!");
    }
}
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        supervisor.processLeaveRequest(10);
    }
}
/*
Use this pattern when you encounter the need to execute several handlers in a particular order
Use the pattern when it’s essential to execute several handlers in a particular order.
Instead of managing complex if-else conditions, each handler encapsulates its own logic. This modularity leads to cleaner, more maintainable code.
 */