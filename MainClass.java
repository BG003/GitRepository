import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;

public class MainClass {

   public static void main(String []args) throws SQLException {

      Scanner sc = new Scanner(System.in);

      String url = "jdbc:mysql://localhost:3306/demo";   // URL

      String usr = "root";
      String pswd = "MySQL@Admin@123";
     
      DatabaseProcedure dbpcdr = new DatabaseProcedure(url,usr,pswd);
      
      boolean bool = false;
      ArrayList <Employee> empList = new ArrayList<>();

      System.out.println("\nWant to Insert record True / False...");  
      bool = sc.nextBoolean();

      while(bool) {
         empList.add(new Employee());
         System.out.println("Want to Insert another record True / False... \n");
         bool = sc.nextBoolean();
      }
      
      System.out.println(dbpcdr.insertRecords(empList)+" Records inserted...\n");  // Insert method call
      


      System.out.println("Want to Show All records True / False... \n");
      if(sc.nextBoolean()) {      
         dbpcdr.displayRecords();  // Display method call
         System.out.println();
      }



      System.out.println("\nWant to Update Records True / False... \n");

      if(sc.nextBoolean()) {

         String values[] = new String[8];

         System.out.println("Enter Id Code to Update... :");
         values[0] = sc.next();

         System.out.println("Want to Update Fist Name True/False...");
         if(sc.nextBoolean()) {
            System.out.println("Enter New First Name... :");
            values[1] = sc.next();
         }

         System.out.println("Want to Update Last Name True/False...");
         if(sc.nextBoolean()) {
            System.out.println("Enter New Last Name... :");
            values[2] = sc.next();
         }

         System.out.println("Want to Update Mobile Number True/False... :");
         if(sc.nextBoolean()) {
            System.out.println("Enter New Mobile Number... :");
            values[3] = sc.next();
         }

         System.out.println("Want to Update Date of Birth True/False... :");
         if(sc.nextBoolean()) {
            System.out.println("Enter New Date of Birth (YYYY-MM-DD)... :");
            values[4] = sc.next();
         }

         System.out.println("Want to Update Address True/False... :");
         if(sc.nextBoolean()) {
            sc.nextLine();
            System.out.println("Enter New Address... :");
            values[5] = sc.nextLine();
         }

         System.out.println("Want to Update Gender True/False... :");
         if(sc.nextBoolean()) {
            System.out.println("Enter New Gender... :");
            values[6] = sc.next();
         }

         System.out.println("Want to Update Email True/False... :");
         if(sc.nextBoolean()) {
            System.out.println("Enter New Email... :");
            values[7] = sc.next();
         }
         
         System.out.println(dbpcdr.updateRecords(values)+" Records Updated...\n");  // Update Method call
         
      } // End of If body.


      System.out.println("Want to Delete record True / False... \n");

      if(sc.nextBoolean()) {

         System.out.println("Enter Id Code to delete record...");
         int idCode = sc.nextInt();
         System.out.println(dbpcdr.deleteRecords(idCode)+" Records deleted...\n");  // Delete Method call
         dbpcdr.displayRecords();

      }

   
   }  // End of main method.

} // End of Class Body.