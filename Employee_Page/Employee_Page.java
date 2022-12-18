package narrow_shape_2645.Employee_Page;


import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Implements;
import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Interface;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.POJO.RaisedComplain;

import java.util.List;
import java.util.Scanner;

public class Employee_Page {

    static Dao_Interface dao= new Dao_Implements();
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        supreme();
        sc.close();
    }

    public  static void supreme(){
        System.out.println("Press 1 for sign up ");
        System.out.println("Press 2 for login");
        int press = sc.nextInt();
        if(press==1) sign_Up();
        else if(press==2) login();
        System.out.println("Try again.....");
    }

    public static void sign_Up(){
        System.out.println("Enter new username");
        sc.nextLine();
        String username=sc.nextLine();
        System.out.println("Enter new password(Must be in number)");
        int password=sc.nextInt();
        try {
            System.out.println(dao.signup_Employee(username,password));
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else supreme();

    }
    static String username;
    public static void login(){
        System.out.println("Enter your username");
        sc.nextLine();
         username=sc.nextLine();
        System.out.println("Enter your password(Must be in number)");
        int password=sc.nextInt();
        try {
           if(dao.login_Employee(username,password).equals("Login Successful")){
               after_Login();
           }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else supreme();

    }
    public static void after_Login(){
        System.out.println("Want to register a new complain? Press 1");
        System.out.println("Want to check the status? Press 2");
        System.out.println("Want to see complain history? Press 3");
        System.out.println("Want to change password? Press 4");
        System.out.println("Want to log out? Press 5");
        int choose=sc.nextInt();
        switch (choose) {
            case 1:  register_Complain();
            case 2: check_Status();
            case 3:complain_History();
            case 4:change_Password();
            case 5:
                System.out.println("Exiting........");
                sc.close();
                System.exit(0);
            default: System.out.println("\nTry again.......");
                after_Login();
        }
    }

    public static void register_Complain(){

        System.out.println("Enter 1 for  HARDWARE problem , 2 for SOFTWARE problem");
        int type=sc.nextInt();
        String type_="";
        if(type==1)  type_="HARDWARE";
        else if(type==2)  type_="SOFTWARE";
        else {
            System.out.println("Try again...");
            register_Complain();
        }

        System.out.println("Explain your complain details");
        sc.nextLine();
        String details=sc.nextLine();

        System.out.println(dao.Raise_A_Complain(type_,details,username));

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else after_Login();
    }

    public static void check_Status(){
        System.out.println("Enter your complain id you are searching for...");
        int id= sc.nextInt();
        try {
            RaisedComplain com=dao.check_Complain_Status(id,username);

            System.out.println("complainid:"+com.getComplainId()+", complain details:"+com.getComplainDetails()
                    +", problem type:"+com.getComplainType()+
                    ", engineer name:"+ com.getSolveBy()+",  employee name:"+com.getRaisedBy()+",  status details:"+com.getStatus()+"\n");

        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else after_Login();

    }

    public static void complain_History(){
       List<RaisedComplain>  list=dao.check_All_Raised_Complain(username);

        for(RaisedComplain com:list) {
            System.out.println("complainid:"+com.getComplainId()+", complain details:"+com.getComplainDetails()
                    +",  problem type:"+com.getComplainType()+
                    ",  engineer name:"+ com.getSolveBy()+",  employee name:"+com.getRaisedBy()+",  status details:"+com.getStatus()+"\n");
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else after_Login();
    }
    public static void change_Password(){
        System.out.println("Enter your password");
        int password=sc.nextInt();
        System.out.println("Enter new password");
        int new_password= sc.nextInt();
        try {
            System.out.println(dao.Update_Password_Employee(username,password,new_password));
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else after_Login();
    }
}