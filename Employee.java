import java.util.Scanner;
import java.time.LocalDate;
import java.sql.Date;

public class Employee {

   private int idCode;
   private String firstName, lastName;
   private String address, gender, email, mobileNo;
   private Date dob;

   public Employee() {
      setEmployee(this);
   }

   public static void setEmployee(Employee emp) {

       Scanner sc = new Scanner(System.in);
       System.out.println();
       System.out.println("Enter Id Code... :");
       emp.setIdCode(sc.nextInt());
       System.out.println("Enter First Name... :");
       emp.setFirstName(sc.next());
       System.out.println("Enter Last Name... :");
       emp.setLastName(sc.next());
       sc.nextLine();
       System.out.println("Enter Address... :");
       emp.setAddress(sc.nextLine());
       System.out.println("Enter Gender... :");
       emp.setGender(sc.next());
       System.out.println("Enter Email... :");
       emp.setEmail(sc.next());
       System.out.println("Enter Mobile Number... :");
       emp.setMobileNo(sc.next());
       System.out.println("Enter Date of birth (YYYY-MM-DD)... :");
       emp.setDob(sc.next());         
       System.out.println();
       
   }


   /* Setters */

   public void setIdCode(int idCode) {
      this.idCode = idCode;
   }

   public void setFirstName(String frsName) {
      this.firstName = frsName;
   }

   public void setLastName(String lstName) {
      this.lastName = lstName;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setMobileNo(String mobileNo) {
      this.mobileNo = mobileNo;
   }
   
   public void setDob(String dob) {
      this.dob = Date.valueOf(LocalDate.parse(dob));
   }


   /* Getters */
 
   public int getIdCode() {
      return this.idCode;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public String getAddress() {
      return this.address;
   }

   public String getGender() {
      return this.gender;
   }

   public String getEmail() {
      return this.email;
   }

   public String getMobileNo() {
      return this.mobileNo;
   }
   
   public Date getDob() {
      return this.dob;
   }  

} // End of Class Body.
