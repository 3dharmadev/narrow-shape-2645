package narrow_shape_2645.Dao_Classes_and_Interface;


import narrow_shape_2645.DB_Connect.DB_Connect;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.Exception.EngineerException;
import narrow_shape_2645.POJO.Engineer;
import narrow_shape_2645.POJO.RaisedComplain;

 import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

public class Dao_Implements implements Dao_Interface{
    @Override
    public String login_HOD( int password,String username) {

        String message="Unable to login...";

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement(" select * from HOD where username='deba' AND password=1234");

            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("username").equals(username) && password==rs.getInt("password")){
                 message="Login Successful";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String login_Employee(String username, int password) throws EmployeeException {
        String message="Unable to login...";

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("select * from employee where username=? AND password=?");

            ps.setString(1,username);
            ps.setInt(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("username").equals(username) && password==rs.getInt("password")){
                message="Login Successful";
            }
            else throw new EmployeeException("No employee exists with these details");

        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String signup_Employee(String username, int password) throws EmployeeException {
        String message="Unable to register...";


        if(username.contains("@gmail.com"))

        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?)");

            ps.setString(1,username);
            ps.setInt(2,password);

            int rs = ps.executeUpdate();

            if(rs>0){
                message="Sign up Successful";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            message=e.getMessage();
            throw new EmployeeException(e.getMessage());
        }
        return message;
    }

    @Override
    public String login_Engineer(String username, int password) throws EngineerException {
        String message="Unable to login...";


        try (Connection conn= DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("select * from engineer where email=? AND password=?");

            ps.setString(1,username);
            ps.setInt(2,password);


            ResultSet rs = ps.executeQuery();

            if(rs.next() && rs.getString("email").equals(username) && password==rs.getInt("password")){
                message="Login Successful";
            }
            else throw new EngineerException("No engineer exists with these details");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            message=e.getMessage();
        }
        return message;
    }

    @Override
    public String register_Engineer(Engineer engineer) throws EngineerException {

        String message="Unable to register fill the proper details";

        if(!engineer.getUsername().contains("@gmail.com")){
            throw new EngineerException("does not contains @gmail.com");
        }

        try(Connection conn=DB_Connect.getConnection()){

            PreparedStatement ps =conn.prepareStatement("insert into engineer values(?,?,?)");

            ps.setString(1, engineer.getUsername());
            ps.setInt(2,engineer.getPassword());
            ps.setString(3,engineer.getType());

            int result=ps.executeUpdate();

            if(result>0) message="Sign up successfully";

        }
        catch (SQLException e){
            throw new EngineerException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Assign_Complain_To_Engineer(String username) throws EngineerException {
        String message="Unable to assign try again...";
        return null;
    }

    @Override
    public List<RaisedComplain> list_Of_Complains_MapTo_Engineer() {
        return null;
    }

    @Override
    public List<RaisedComplain> open_Complains() {
        return null;
    }

    @Override
    public List<RaisedComplain> all_Raised_Complains() throws EmployeeException {
        return null;
    }

    @Override
    public List<Engineer> list_Of_Engineers() throws EngineerException {
        return null;
    }

    @Override
    public String delete_Engineer(String username) throws EngineerException {
        return null;
    }

    @Override
    public String update_Status(int complainId) throws EmployeeException {
        return null;
    }

    @Override
    public String Update_Password_Engineer(String username, int password) throws EngineerException {
        return null;
    }

    @Override
    public String Update_Password_Employee(String username, int password) throws EmployeeException {
        return null;
    }

    @Override
    public RaisedComplain check_Complain_Status(int complainId) throws EmployeeException {
        return null;
    }

    @Override
    public String Raise_A_Complain(String complain_Type, String complainDetails) {
        return null;
    }

    @Override
    public List<RaisedComplain> check_All_Raised_Complain() {
        return null;
    }
}
