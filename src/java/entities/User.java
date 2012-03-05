package entities;

public class User
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String providerName;

    public User() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }
  
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", providerName=" + providerName + '}';
    }

    
}
