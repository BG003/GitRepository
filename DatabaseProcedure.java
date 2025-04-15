import java.sql.*;
import java.util.ArrayList;
public class DatabaseProcedure {
    private Connection con;
    private String url;
    private String usrnm;
    private String pswd;

    // Parameterized Constructor to initilize URL, USER NAME & PASSWORD.

    public DatabaseProcedure(String url, String usrnm, String pswd) {
        this.url = url;
        this.usrnm = usrnm;
        this.pswd = pswd;
    }

    // Private Method to get CONNECTION to DB SERVER. Call every time with CRUD OPERATION.

    private void establishConnection() throws SQLException {
        this.con = DriverManager.getConnection(url,usrnm,pswd);
    }

    // Private Method to close the CONNECTION to DBMS SERVER. Call every time with CRUD OPERATION.

    private void closeConnection() {
        if(this.con != null) {
            try
            {
                con.close();
            }
            catch(SQLException sqlEx) {
                System.out.println(sqlEx.getMessage());
            }
        }
    }

    // Method to INSERT RECORD into Employee TABLE.

    public int insertRecord(Employee emp) {
        int effectedRow = 0;
        try
        {
            this.establishConnection(); // throws SQL Exception if url null or DB error.
            String insertQuery = "INSERT INTO employee VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prstmt = con.prepareStatement(insertQuery);  // throws DB error only.
            // Here we can use HashMap instead of Employee Object.
            prstmt.setInt(1,emp.getIdCode());
            prstmt.setString(2,emp.getFirstName());
            prstmt.setString(3,emp.getLastName());
            prstmt.setString(4,emp.getMobileNo());
            prstmt.setString(5,emp.getAddress());
            prstmt.setString(6,emp.getGender());
            prstmt.setString(7,emp.getEmail());

            // effectedRow = prstmt.executeUpdate();
        }
        catch(SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        finally {
            this.closeConnection();
        }
        return effectedRow;
    }


    // Private Method to RETRIEVE RECORDS as a ArrayList FROM TABLE.

    public ArrayList<String> retrieveRecord() {
        String query = "SELECT * FROM employee";
        return retrieve(query);
    }

    // Private Method to RETRIEVE SPECIFIED RECORD as a ArrayList FROM TABLE.

    public ArrayList<String> retrieveRecord(int empIdCode) {
        String query = "SELECT * FROM employee WHERE Id_Code="+empIdCode;
        return retrieve(query);
    }

    // Driver Method for RETRIEVE RECORD FROM TABLE using above private method.

    private ArrayList<String> retrieve(String selectQuery) {
        ArrayList<String> values = new ArrayList<>();
        try
        {
            this.establishConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                values.add(Integer.toString(rs.getInt(1)));
                values.add(rs.getString(2));
                values.add(rs.getString(3));
                values.add(rs.getString(4));
                values.add(rs.getString(5));
                values.add(rs.getString(6));
                values.add(rs.getString(7));
            }
        }
        catch(SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        finally {
            this.closeConnection();
        }
        return values;
    }

    // Private Method to DISPLAY ALL RECORD FROM TABLE.

    public void displayRecord() {
        String query = "SELECT * FROM employee";
        displayValue(query);
    }

    // Private Method to DISPLAY SPECIFIED RECORD FROM TABLE.

    public void displayRecord(int empIdCode) {
        String query = "SELECT * FROM employee WHERE Id_Code="+empIdCode;
        displayValue(query);
    }

    // Driver Method to DISPLAY RECORDS FROM TABLE using above method.

    private void displayValue(String selectQuery) { 
        try
        {
            this.establishConnection();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);
            if(!rs.next())
                System.out.println("NO RECORD FOUND !");
            else
            {
                do {
                    System.out.println("Id Code........."+rs.getInt(1));
                    System.out.println("First Name......"+rs.getString(2));
                    System.out.println("Last Name......."+rs.getString(3));
                    System.out.println("Mobile Number..."+rs.getString(4));
                    System.out.println("Address........."+rs.getString(5));
                    System.out.println("Gender.........."+rs.getString(6));
                    System.out.println("Email Id........"+rs.getString(7));
                    System.out.println("\n\n");
                } while(rs.next());
            }
        }
        catch(SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        finally {
            this.closeConnection();
        }
    }

    // Method to DELETE SPECIFIED RECORD FROM TABLE.

    public int deleteRecord(int empIdCode) {
        int effectedRow = 0;
        try
        {
            this.establishConnection();
            String deleteQuery = "DELETE FROM employee WHERE Id_Code=?";
            PreparedStatement prstmt = con.prepareStatement(deleteQuery);
            prstmt.setInt(1,empIdCode);
            effectedRow = prstmt.executeUpdate();
            if(effectedRow == 0)
                throw new SQLException("NO RECORD FOUND WITH ID CODE - "+empIdCode);
        }
        catch(SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        finally {
            this.closeConnection();
        }
        return effectedRow;
    }

    // Methdo to UPDATE RECORD into TABLE.

    public int updateRecord(String values[]) {

        int count = valuesCount(values); // To count not null values in values[]
        int effectedRow = 0; // To count Effected Row by UPDATE Query.
        try
        {
            if(count > 1 && values[0] != null) {
                this.establishConnection(); // Establish the Connection to DBMS.
                DatabaseMetaData dbmt = con.getMetaData(); // Get Object of DatabaseMetaData to use MetaData of table.

                ResultSet rs = dbmt.getColumns(null, null, "employee",null); // Get Columns name from Meta Data.

                rs.next();
                int number[] = new int[count]; // Array Of Int which contains index of not null value of values[].

                StringBuffer updateQuery = new StringBuffer("UPDATE employee SET "); // update query to prepare.
                String condition = "WHERE "+rs.getString("COLUMN_NAME")+"=?"; // update contition to append to update query.
                
                int i=1,j=1;
                for(rs.next(); i < values.length; i++) {
                    if(values[i] != null) {
                        updateQuery.append(rs.getString("COLUMN_NAME")+"=?, ");
                        rs.next();
                        number[j] = i;
                        j++;
                    }
                    else
                        rs.next();
                }
                updateQuery.deleteCharAt(updateQuery.lastIndexOf(","));

                updateQuery.append(condition); // Append the condition of update Query.
                System.out.println(updateQuery);
                
                PreparedStatement prstmt = con.prepareStatement(updateQuery.toString()); // Get Object of Prepared Statement.

                // Change below code for different table.

                for(int n = 0; n < count ; n++) {
                    switch(n) {
                        case 0:
                            prstmt.setInt(count,Integer.parseInt(values[number[n]]));
                            break;
                        case 1:
                            prstmt.setString(n,values[number[n]]);
                            break;
                        case 2:
                            prstmt.setString(n,values[number[n]]);
                            break;
                        case 3:
                            prstmt.setString(n,values[number[n]]);
                            break;
                        case 4:
                            prstmt.setString(n,values[number[n]]);
                            break;
                        case 5:
                            prstmt.setString(n,values[number[n]]);
                            break;
                        case 6:
                            prstmt.setString(n,values[number[n]]);
                            break;
                    }
                }
                
                effectedRow = prstmt.executeUpdate();
                if(effectedRow == 0)
                    throw new SQLException("NO RECORD FOUND WITH ID CODE - "+values[0]);  
            }
            else
                throw new SQLException("PROVIDE ENOUGH DATA !");
        }
        catch(SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException arrayIndexEx) {
            System.out.println(arrayIndexEx.getMessage());
        }
        finally {
            this.closeConnection();
        }
        return effectedRow;
    }

    private int valuesCount(String values[]) {
        int count = 0;
        for(int i = 0 ; i < values.length; i++) {
            if(values[i] != null)
                count++;
        }
        return count;
    }
}