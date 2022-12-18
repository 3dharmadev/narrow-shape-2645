package narrow_shape_2645.usecases;

import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Implements;
import narrow_shape_2645.Dao_Classes_and_Interface.Dao_Interface;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.Exception.EngineerException;
import narrow_shape_2645.POJO.Engineer;

import java.util.Random;

public class Check_ {
    public static void main(String[] args) {
        Dao_Interface dao = new Dao_Implements();

       // System.out.println(dao.login_HOD(1234,"deba"));
//        try {
//            System.out.println(dao.login_Employee("rama@gmail",12345678));
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }




//        try {
//            System.out.println(dao.signup_Employee("rama@gmail",12292));
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        try {
//            System.out.println(dao.login_Engineer("deb@gmail.com",1234));
//        } catch (EngineerException e) {
//            System.out.println(e.getMessage());
//        }


//        Engineer engineer=new Engineer();
//        engineer.setUsername("sbsatapathy@gmail.com");
//        engineer.setPassword(1234);
//        engineer.setType("HARDWARE");
//        try {
//            System.out.println(dao.register_Engineer(engineer));
//        } catch (EngineerException e) {
//            System.out.println(e.getMessage());
//        }

//        Random rnd = new Random();
//        int number = rnd.nextInt(9999999);
//
//        System.out.println(number);

//        try {
//            System.out.println(dao.Assign_Complain_To_Engineer("sbsatapathy@gmail.com",1));
//        } catch (EngineerException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            System.out.println(dao.all_Raised_Complains());
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            System.out.println(dao.list_Of_Engineers());
//        } catch (EngineerException e) {
//            System.out.println(e.getMessage());
//        }

        // System.out.println(dao.list_Of_Complains_MapTo_Engineer("dbsatapathy@gmail.com"));

    //    System.out.println(dao.open_Complains("sbsatapathy@gmail.com"));

//        try {
//            System.out.println(dao.update_Status(100,"70% completed"));
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println(dao.delete_Engineer("sbsatapathy@gmail.com"));
//        } catch (EngineerException e) {
//            System.out.println(e.getMessage());
//        }

       //  System.out.println(dao.Raise_A_Complain("HARDWARE","the king of universe","rama@gmail.com"));

       // System.out.println(dao.check_All_Raised_Complain("rama@gmail.com"));


//        try {
//            System.out.println(dao.check_Complain_Status(59000,"k@gmail.com"));
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }

//        try {
//            System.out.println(dao.update_Status(1,"updated"));
//        } catch (EmployeeException e) {
//            System.out.println(e.getMessage());
//        }

        try {
            System.out.println(dao.update_Status(1,"newwwwwwwwwwwww"));
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }

    }
}
