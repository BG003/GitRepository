public class Employee {

    private Integer idCode;
    private String firstName, lastName;  
    private String mobileNo;
    private String address;
    private String gender;
    private String email;

    // Setters.
    
    public void setIdCode(int idCode) {
        if(idCode < 0)
            idCode = -idCode;
        this.idCode = idCode;
    }

    public void setFirstName(String firstName) {
        try {
            this.firstName = firstName;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide First Name !");
        }
    }

    public void setLastName(String lastName) {
        try {
            this.lastName = lastName;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide Last Name !");
        }
    }

    public void setMobileNo(String mobileNo) {
        try {
            this. mobileNo = mobileNo;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide Mobile Number. !");
        }
    }
    
    /*
    public void set(String dob) {
        
    }
    */
    public void setAddress(String address) {
        try {
            this.address = address;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide address. !");
        }
    }   

    public void setEmail(String email) {
        try {
            this.email = email;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide Email. !");
        }
    }

    public void setGender(String gender) {
        try {
            this.gender = gender;
        }
        catch(NullPointerException ex) {
            System.out.println("Please provide Gender. !");
        }
    }

    // Getters.

    public int getIdCode() {
        return this.idCode;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMobileNo() {
        return this.mobileNo;
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

    public String toString() {
        String idCode = "idCode = "+this.idCode;
        String firstName = ", First Name = "+this.firstName;
        String lastName = ", Last Name = "+this.lastName;
        String mobileNo = ", Mobile Number = "+this.mobileNo;
        String address = ", Address = "+this.address;
        String gender = ", Gender = "+this.gender;
        String email = ", Email = "+this.email;
        String finalStr = idCode+firstName+lastName+mobileNo+address+gender+email;
        return finalStr;
    }

}