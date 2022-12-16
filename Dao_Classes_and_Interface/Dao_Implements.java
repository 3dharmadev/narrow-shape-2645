package narrow_shape_2645.Dao_Classes_and_Interface;


import SB_101.sprint_4.day_1.JDBC.JDBC_Config.JDBC_Conn;
import narrow_shape_2645.DB_Connect.DB_Connect;
import narrow_shape_2645.Exception.EmployeeException;
import narrow_shape_2645.Exception.EngineerException;
import narrow_shape_2645.POJO.Engineer;
import narrow_shape_2645.POJO.RaisedComplain;

 import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Dao_Implements implements narrow_shape_2645.Dao_Classes_and_Interface.Dao_Interface {
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

        if(!engineer.getEmail().contains("@gmail.com")){
            throw new EngineerException("does not contains @gmail.com");
        }

        try(Connection conn=DB_Connect.getConnection()){

            PreparedStatement ps =conn.prepareStatement("insert into engineer values(?,?,?)");

            ps.setString(1, engineer.getEmail());
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
    public String Assign_Complain_To_Engineer(String username,int complainId) throws EngineerException {
        String message="Unable to assign try again...";

        if(message.contains("@gmail.com")) throw new EngineerException("does not contain @gmail.com");

        try(Connection conn=DB_Connect.getConnection()){

            PreparedStatement ps=conn.prepareStatement("update complain set solveby=? where complainID=?");

            ps.setString(1,username);
            ps.setInt(2,complainId);
            int result=ps.executeUpdate();

            if(result>0) message="Assigned successfully";

        }
        catch (SQLException e){
            throw  new EngineerException(e.getMessage());
        }
        return message;
    }

    @Override
    public List<RaisedComplain> list_Of_Complains_MapTo_Engineer(String email) {

        List<RaisedComplain> list=new ArrayList<>();

        /*Field           | Type        | Null | Key | Default | Extra |
+-----------------+-------------+------+-----+---------+-------+
| complainId      | int(11)     | NO   | PRI | NULL    |       |
| complainType    | varchar(30) | NO   |     | NULL    |       |
| complainDetails | varchar(80) | NO   |     | NULL    |       |
| raisedby        | varchar(20) | NO   |     | NULL    |       |
| solveby         | varchar(30) | YES  | MUL | NULL    |*/

        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement("select * from complain where  solveby=?");
            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));
                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<RaisedComplain> open_Complains(String email) {
        List<RaisedComplain> list=new ArrayList<>();


        /*Field           | Type        | Null | Key | Default | Extra |
+-----------------+-------------+------+-----+---------+-------+
| complainId      | int(11)     | NO   | PRI | NULL    |       |
| complainType    | varchar(30) | NO   |     | NULL    |       |
| complainDetails | varchar(80) | NO   |     | NULL    |       |
| raisedby        | varchar(20) | NO   |     | NULL    |       |
| solveby         | varchar(30) | YES  | MUL | NULL    |*/
        try(Connection conn=DB_Connect.getConnection()){
            PreparedStatement ps=conn.prepareStatement("select * from complain where complainStatus!='NULL' AND solveby=?");

            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                RaisedComplain raisedComplain=new RaisedComplain();
                raisedComplain.setComplainId(rs.getInt("complainId"));
                raisedComplain.setComplainType(rs.getString("complainType"));
                raisedComplain.setComplainDetails(rs.getString("complainDetails"));
                raisedComplain.setRaisedBy(rs.getString("raisedby"));
                raisedComplain.setSolveBy(rs.getString("solveby"));

                list.add(raisedComplain);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return list;

    }

    @Override
    public List<RaisedComplain> all_Raised_Complains() throws EmployeeException {
       List<RaisedComplain> list = new ArrayList<>();

       /* Field           | Type        | Null | Key | Default | Extra |
        +-----------------+-------------+------+-----+---------+-------+
        | complainId      | int(11)     | NO   | PRI | NULL    |       |
        | complainType    | varchar(30) | NO   |     | NULL    |       |
        | complainDetails | varchar(80) | NO   |     | NULL    |       |
        | raisedby        | varchar(20) | NO   |     | NULL    |       |
        | solveby         | varchar(30) | YES  | MUL | NULL    |       |
        +-----------------+-------------+------+-----+---------+-------+*/

       try (Connection conn=DB_Connect.getConnection()){

           PreparedStatement ps =conn.prepareStatement("select * from complain");

           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               RaisedComplain raisedComplain=new RaisedComplain();

               raisedComplain.setComplainId(rs.getInt("complainId"));
               raisedComplain.setComplainType(rs.getString("complainType"));
               raisedComplain.setComplainDetails(rs.getString("complainDetails"));
               raisedComplain.setRaisedBy(rs.getString("raisedby"));
               raisedComplain.setSolveBy(rs.getString("solveby"));
               list.add(raisedComplain);
           }

       }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
       return list;
    }

    @Override
    public List<Engineer> list_Of_Engineers() throws EngineerException {
        List<Engineer> list=new ArrayList<>();

        try(Connection conn=DB_Connect.getConnection()){
         PreparedStatement ps = conn.prepareStatement("select * from engineer");

         ResultSet rs=ps.executeQuery();

         /*+----------+-------------+------+-----+---------+-------+
            | Field    | Type        | Null | Key | Default | Extra |
            +----------+-------------+------+-----+---------+-------+
            | email    | varchar(30) | NO   | PRI | NULL    |       |
            | password | int(11)     | NO   |     | NULL    |       |
            | Type     | varchar(10) | NO   |     | NULL    |       |
            +----------+-------------+------+-----+---------+-------+*/

         while(rs.next()){
             Engineer engineer=new Engineer();
             engineer.setEmail(rs.getString("email"));
             engineer.setPassword(rs.getInt("password"));
             engineer.setType(rs.getString("Type"));
             list.add(engineer);
         }

        }
        catch (SQLException e){
            throw new EngineerException(e.getMessage());
        }

        return list;
    }

    @Override
    public String delete_Engineer(String username) throws EngineerException {
        String message="Username not found";

        try(Connection conn=DB_Connect.getConnection()) {


            PreparedStatement ps=conn.prepareStatement(" delete from engineer where email=?");
            ps.setString(1,username);
          int res1=  ps.executeUpdate();

          if(res1>0) message="Deleted successfully";

        }
        catch (SQLException e){
     throw new EngineerException(e.getMessage());
        }
        return message;
    }

    @Override
    public String update_Status(int complainId,String updateMessage) throws EmployeeException {
        String message="complainId is wrong..!";

        try(Connection conn=DB_Connect.getConnection()) {
            PreparedStatement ps= conn.prepareStatement("update  complain set complainStatus=? where complainid=?");
            ps.setString(1,updateMessage);
            ps.setInt(2,complainId);

            int res=ps.executeUpdate();
            if(res>0) message="Status update successfully";

        }
        catch (SQLException e){
           throw   new  EmployeeException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Update_Password_Engineer(String username, int password,int new_Password) throws EngineerException {
        String message = "Wrong name..!";

        try (Connection conn =  JDBC_Conn.Set_Connection()) {

            PreparedStatement ps = conn.prepareStatement("update engineer set password=? where email=? and password=?");


            ps.setInt(1, new_Password);
            ps.setString(2, username);
            ps.setInt(3,password);


            int x = ps.executeUpdate();

            if (x > 0) message = "password Updated Successfully..!";

        } catch (SQLException e) {
            throw new EngineerException(e.getMessage());
        }

        return message;
    }

    @Override
    public String Update_Password_Employee(String username, int password,int new_Password) throws EmployeeException {
        String message = "Wrong name..!";

        try (Connection conn =  JDBC_Conn.Set_Connection()) {

            PreparedStatement ps = conn.prepareStatement("update employee set password=? where username=? and password=?");

            ps.setInt(1, new_Password);
            ps.setString(2, username);
            ps.setInt(3,password);


            int x = ps.executeUpdate();

            if (x > 0) message = "password Updated Successfully..!";

        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage());
        }

        return message;
    }

    @Override
    public RaisedComplain check_Complain_Status(int complainId,String username) throws EmployeeException {
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
