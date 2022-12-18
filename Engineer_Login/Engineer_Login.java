package narrow_shape_2645.Engineer_Login;

import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Implements;
import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Interface;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.Exception.EngineerException;
import narrow_shape_2645.POJO.Engineer;
import narrow_shape_2645.POJO.RaisedComplain;

import java.util.List;
import java.util.Scanner;

public class Engineer_Login {
    static Dao_Interface dao = new Dao_Implements();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Login();
        sc.close();

    }
    static String username; static boolean unable_login=false;
    public static void Login() {

        if (unable_login==true) sc.nextLine();
        System.out.println("Enter your username ");

          username = sc.nextLine();
        System.out.println("Enter your password(Must be in number)");
        int password = sc.nextInt();

        try {
          if(dao.login_Engineer(username,password).equals("Login Successful")){
              System.out.println("=============================================================");
              System.out.println("Login successful✔");
              System.out.println("Hello "+username);
              After_Login();
          }
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }

        unable_login=true;
        System.out.println("Try again....");

        System.out.println("======================================================================");
        Login();

    }

    public static void After_Login() {

        System.out.println("Want to see new assigned complains? Press 1");
        System.out.println("Want to update status of the complains? Press 2");
        System.out.println("Want to see progress works? Press 3");
        System.out.println("Want to change the password? Press 4");
        System.out.println("Want to logout? Press 5");
        int choose=sc.nextInt();
        switch (choose) {
            case 1:  assigned_Problem_By_HOD();
            case 2: update_Status();
            case 3:attempt_Queries();
            case 4:change_Password();
            case 5:
                System.out.println("Exiting........");
                sc.close();
                System.exit(0);
            default: System.out.println("\nTry again.......");
                After_Login();
        }

    }

    public static void assigned_Problem_By_HOD(){

      List<RaisedComplain> list =dao.list_Of_Complains_MapTo_Engineer(username);

        for (RaisedComplain com:list) {
            System.out.println("complainid:"+com.getComplainId()+", complain details:"+com.getComplainDetails()
                    +",  problem type:"+com.getComplainType()+
                    ",  engineer name:"+ com.getSolveBy()+",  employee name:"+com.getRaisedBy()+",  status details:"+com.getStatus()+"\n");
        }

        if(list.size()==0) System.out.println("No new assigned complain found!!");
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        System.out.println("===========================================================");
        if(return_=="") return;
        else After_Login();

    }

    public static void update_Status(){

        System.out.println("Enter complainId for updating the status");
        int id=  sc.nextInt();

        System.out.println("write update status message");
        sc.nextLine();
        String message=  sc.nextLine();
        try {
            System.out.println(dao.update_Status(id,message));
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();


    }

    public static void attempt_Queries(){
           List<RaisedComplain> list=  dao.open_Complains(username);

        for (RaisedComplain com:list) {
            System.out.println("complainid:"+com.getComplainId()+", complain details:"+com.getComplainDetails()
                    +",  problem type:"+com.getComplainType()+
                    ",  engineer name:"+ com.getSolveBy()+",  employee name:"+com.getRaisedBy()+",  status details:"+com.getStatus()+"\n");
        }

        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        System.out.println("===========================================================");
        if(return_=="") return;
        else After_Login();
    }

    public static void change_Password(){
        System.out.println("Enter your password");
        int password=sc.nextInt();
        System.out.println("Enter new password");
        int new_password= sc.nextInt();
        try {
            System.out.println(dao.Update_Password_Engineer(username,password,new_password));
        } catch (EngineerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nPress any key for returning to main menu(except enter)..");
        String return_=sc.next();
        if(return_=="") return;
        else After_Login();;
    }






}
