package narrow_shape_2645.HOD_Login;

import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Implements;
import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Interface;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.Exception.EngineerException;
import narrow_shape_2645.POJO.Engineer;
import narrow_shape_2645.POJO.RaisedComplain;

import java.util.List;
import java.util.Scanner;

public class Head_Of_Department_Login {
    static Dao_Interface dao= new Dao_Implements();
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        Login();
        sc.close();
        System.out.println("Everything closed");
    }

    public static void Login(){

        System.out.println("Enter your username ");
        String username=sc.nextLine();
        System.out.println("Enter your password(Must be in number)");
        int password=sc.nextInt();

       if(dao.login_HOD(password,username).equals("Login Successful")){
           System.out.println("Login successful✔");

           System.out.println("Hello "+username);
           After_Login();

       }
       else{
           System.out.println("Wrong input try again...");
           Login();
       }
       sc.close();
    }
    public static void After_Login(){

        System.out.println("Want to register a new engineer? Press 1");
        System.out.println("Want to see all registered engineer? Press 2");
        System.out.println("Want to delete an engineer? Press 3");
        System.out.println("Want to see all raised problem? Press 4");
        System.out.println("Want to assign raised problem an engineer? Press 5");
        System.out.println("Want to log out? Press 6");
        int choose=sc.nextInt();
        switch (choose) {
             case 1:  register_Engineer();
            case 2: list_Of_Engineer();
            case 3:delete_Engineer();
            case 4:all_Raised_Problems();
            case 5:assign_Problem();
            case 6:
                System.out.println("Exiting........");
                sc.close();
                System.exit(0);
            default: System.out.println("\nTry again.......");
                After_Login();
         }
    }

    public static void register_Engineer(){

        System.out.println("Enter registered email");
        sc.nextLine();
        String email=sc.nextLine();

        System.out.println("Enter 1 for working in HARDWARE department , 2 for working in SOFTWARE department");
        int type=sc.nextInt();
        String type_="";
        if(type==1)  type_="HARDWARE";
        else if(type==2)  type_="SOFTWARE";

        System.out.println("set the password for engineer(Must be in number)");
        int password=sc.nextInt();



        Engineer engineer=new Engineer();
        engineer.setType(type_);
        engineer.setPassword(password);
        engineer.setEmail(email);
        try {
            System.out.println( dao.register_Engineer(engineer)+"\n");
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();

    }

    public static void list_Of_Engineer(){
        try {
         List<Engineer> list =dao.list_Of_Engineers();
            for (Engineer e:list) {
                System.out.println("Email:"+e.getEmail()+", Type:"+e.getType());
            }
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();

    }
    public static void delete_Engineer(){
        System.out.println("Enter engineer's username you want to delete");
        sc.nextLine();
        String username=sc.nextLine();


        try {

            System.out.println(dao.delete_Engineer(username));
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();
}
    public static void all_Raised_Problems(){
        try {
          List<RaisedComplain> list= dao.all_Raised_Complains();
            for (RaisedComplain com:list) {
                System.out.println("complainid:"+com.getComplainId()+", complain details:"+com.getComplainDetails() +",  status details:"+com.getStatus()
                        +",  problem type:"+com.getComplainType()+
                       ",  engineer name:"+ com.getSolveBy()+",  employee name:"+com.getRaisedBy()+"\n");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();
    }

    public static void assign_Problem(){


        System.out.println("Enter username of that engineer you want to assign..");
        sc.nextLine();
        String username=sc.nextLine();
        System.out.println("Enter complainId you want to assign for..(Must be in number)");
        int complainId=sc.nextInt();
        try {
            System.out.println(dao.Assign_Complain_To_Engineer(username,complainId));
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu(except enter key)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();

    }



}

