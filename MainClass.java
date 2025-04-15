public class MainClass {
    public static void main(String []args) {
        
        String userName = "root";
        String password = "MySQL@Admin@123";
        String url = "jdbc:mysql://localhost:3306/demo";

        UserInterface obj = new UserInterface();
        Employee emp = obj.insertInterface();
        System.out.println("\n"+emp+"\n");

        DatabaseProcedure dbprcd = new DatabaseProcedure(url,userName,password);  // Create Object of Databaseprocedure.
        
        System.out.println(dbprcd.insertRecord(emp)+" Record Inserted.\n\n"); // Insert Method call.
                
        dbprcd.displayRecord(); // Retrieve Method call.

        System.out.println(dbprcd.deleteRecord(20)+" Record Deleted.\n"); // Delete Method call.
        dbprcd.displayRecord();

        String values[] = {"35",null,"Gohel","7862019644",null,null,"maitrigohel2808@gmail.com"};
        dbprcd.updateRecord(values);

    }
}
