import java.sql.*;
import java.util.ArrayList;

public class DatabaseProcedure {

   private Connection con;

   /* 
      Parameterized constructore to initilize 'Connection object'
   */

   public DatabaseProcedure(String url, String usr, String pswd) {
      try
      {
         con = DriverManager.getConnection(url,usr,pswd);
      }
      catch(SQLException e) {
         System.out.println(e);
      }
   }


   /*

   Method 'insertRecords' to insert records into database. Take ArrayList of type Employee as an arguments & returns Integer value that how many records inserted.

   */

   public int insertRecords(ArrayList<Employee> list) {
      
      int rowCount = 0;  // To count how many records are inserted.
      if(list.size() > 0) {

         String insertQuery = "INSERT INTO employee (Id_Code, First_Name, Last_Name, Mob_no, Dob, Address, Gender, Email) VALUES (?,?,?,?,?,?,?,?)";
   
         try
         {
            PreparedStatement prstmt = con.prepareStatement(insertQuery); 

            for(int index = 0; index < list.size(); index++) // To set insert Query...
            {
               Employee emp = list.get(index);
               prstmt.setInt(1,emp.getIdCode());
               prstmt.setString(2,emp.getFirstName());
               prstmt.setString(3,emp.getLastName());
               prstmt.setString(4,emp.getMobileNo());
               prstmt.setDate(5,emp.getDob());
               prstmt.setString(6,emp.getAddress());
               prstmt.setString(7,emp.getGender());
               prstmt.setString(8,emp.getEmail());
               rowCount = prstmt.executeUpdate();
            }
         
         }
         catch(SQLException e) {
            System.out.println(e);
         }

      } // End of if.

      return rowCount;  

   } // End of method boyd. 


   // Method 'displayRecords' to retrive and display records from database.

   public void displayRecords() {

      String selectQuery = "SELECT * FROM employee";
      try
      {
         PreparedStatement prstmt = con.prepareStatement(selectQuery); 
         ResultSet rst = prstmt.executeQuery();

         if(rst.next()) {
            do {
               System.out.println();
               System.out.println("Id Code........... : "+rst.getInt(1));
               System.out.println("First Name........ : "+rst.getString(2));
               System.out.println("Last Name......... : "+rst.getString(3));
               System.out.println("Mobile No......... : "+rst.getString(4));
               System.out.println("Date of Birth..... : "+rst.getDate(5).toString());
               System.out.println("Address........... : "+rst.getString(6));
               System.out.println("Genger............ : "+rst.getString(7));
               System.out.println("Email-Id.......... : "+rst.getString(8));
            } while(rst.next());
         }
         else
            System.out.println("NO RECORD FOUND...\n");
      }
      catch(SQLException e) {
         System.out.println(e);
      }
   }


   /*

   Method 'deleteRecords' to delete records from database. Take idCode as an arguments & returns integer value that how many records deleted.

   */

   public int deleteRecords(int idCode) {

      int rowCount  = 0;
      String deleteQuery = "DELETE FROM employee WHERE Id_Code="+idCode;
      
      try 
      {
         PreparedStatement prstmt = con.prepareStatement(deleteQuery);
         rowCount = prstmt.executeUpdate();
         return rowCount;
      }
      catch(SQLException e) {
         System.out.println(e);
      }
      
      return rowCount;
   }

  
   /*

   Method 'deleteRecords' to delete records from database. Take idCode as an arguments & returns integer value that how many records inserted.

   */

   public int updateRecords(String values[]) throws SQLException {
      
      int rowCount = 0;

      DatabaseMetaData mtdt = con.getMetaData();  // To get Metadata for retrives Columns name of tables.

      ResultSet rst = mtdt.getColumns(null,null,"employee",null);     
      rst.absolute(2);

      StringBuffer updateQuery = new StringBuffer("UPDATE employee SET ");  // update Query.

      for(int i=1; i < values.length; i++)
      {
         if(values[i] != null) {
            updateQuery.append(rst.getString("COLUMN_NAME") + "='" + values[i] + "', ");
            rst.next();
         }
         else
            rst.next();

      }

      updateQuery.deleteCharAt(updateQuery.lastIndexOf(",")); 
      updateQuery.append(" where Id_Code="+Integer.parseInt(values[0]));

      Statement stmt = con.createStatement();
      rowCount = stmt.executeUpdate(updateQuery.toString());

      return rowCount;
   }
   	
} // End of Class Body.


