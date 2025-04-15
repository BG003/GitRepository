import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    private static String field[] = new String[7];
    private int index = 0;
    private Employee emp;

    static {
        field[0] = "Id Code";
        field[1] = "First Name";
        field[2] = "Last Name";
        field[3] = "Mobile Number";
        field[4] = "Address";
        field[5] = "Gender";
        field[6] = "Email-Id";    
    }

    public UserInterface() {
        emp = new Employee();
    }

    /*
    private HashMap<Integer,String> field = new HashMap<>();
    public UserInterface() {
        field.put(1,"Id Code");
        field.put(2,"First Name");
        field.put(3,"Last Name");
        field.put(4,"Mobile Number");
        field.put(5,"Address");
        field.put(6,"Gender");
        field.put(7,"Email-Id");
    }
    */

    private boolean isNameValid(String name) {
        boolean flg = true;
        String nameUpper = name.toUpperCase();
        int len = name.length();
        if(len < 3 || len > 15)
            return false;
        for(int i = 0; i < len ; i++ ) {
            char ch = nameUpper.charAt(i);
            if(ch < 65 || ch > 90) {
                flg = false;
                break;
            }
        }

        return flg;
    }

    private boolean isNumberValid(String str) {
        boolean flg = true;
        int len = str.length();
        if(!(len == 10))
            return false;
        for(int i=0; i < len ; i++) {
            char ch = str.charAt(i);
            if(ch < 48 || ch > 57) {
                flg = false;
                break;
            }
        }
        return flg;
    }

    private boolean isEmailValid(String email) {
        boolean flg = true;
        String subStr = email.substring(0,email.lastIndexOf("@"));
        int len = subStr.length();
        for(int i = 0; i < len ; i++ ) {
            char ch = subStr.charAt(i);
            if((ch < 97 || ch > 122) && (ch < 48 || ch > 57)) {
                flg = false;
                break;
            }
        }
        if(flg != false)
            if(!(email.contains("@gmail.com") || email.contains("@outlook.com") || email.contains("@yahoo.com")))
                flg = false;
        return flg;
    }

    public Employee insertInterface() {
        Scanner sc = new Scanner(System.in);
        try
        {
            switch(index) {
                case 0 :
                    System.out.println("\nEnter Id Code (Integer 1 to 250).");
                    int id = Integer.parseInt(sc.next());
                    if(id < 1 || id > 250)
                        throw new InputMismatchException();
                    emp.setIdCode(id);
                    index++;
                case 1 :
                    System.out.println("\nEnter First Name ( Minimum 3 & Maximum 15 Character, Alphabets A-Z / a-z ).");
                    String firstName = sc.next();
                    if(isNameValid(firstName)) 
                        emp.setFirstName(firstName);
                    else
                        throw new InputMismatchException();
                    index++;
                case 2 :
                    sc.nextLine();
                    System.out.println("\nEnter Last Name ( Minimum 3 & Maximum 15 Character, Alphabets A-Z / a-z ).");
                    String lstName = sc.next();
                    if(isNameValid(lstName))
                        emp.setLastName(lstName);
                    else
                        throw new InputMismatchException();
                    sc.next();
                    index++;
                case 3 :
                    System.out.println("\nEnter Mobile Number");
                    String mobNo = sc.next();
                    if(isNumberValid(mobNo))
                        emp.setMobileNo(mobNo);
                    else
                        throw new InputMismatchException();
                    index++;
                case 4 :
                    sc.nextLine();
                    System.out.println("\nEnter Address.");
                    emp.setAddress(sc.nextLine());
                    index++;
                case 5 :
                    System.out.println("\nEnter Gender (Male / Female).");
                    String gender = sc.next();
                    if(gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE"))
                        emp.setGender(gender);
                    else
                        throw new InputMismatchException();
                    index++;
                case 6 : 
                    System.out.println("\nEnter Email-Id (gmail / outlook / yahoo).");
                    String email = sc.next();
                    if(isEmailValid(email))
                        emp.setEmail(email);
                    else
                        throw new InputMismatchException();
                    index++;
            }
        }
        catch(NumberFormatException ex) {
            System.out.println("\nEnter valid "+field[index]);
            insertInterface();
        }
        catch(InputMismatchException ex) {
            System.out.println("\nEnter valid "+field[index]);
            insertInterface();
        }
        finally {
            sc.close();
        }
        return this.emp;
    }

    
}