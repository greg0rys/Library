/**
 * Represents Library employee's. Implements security role permissions
 * @author Greg Shenefelt
 * @version 06.29.24
 */
package Logic;

import Utils.IDGenKt;

public class Employee
{
    private String name;
    private int idNumber;
    private boolean isAdmin;
    boolean loggedIn = false;

    public Employee(String eName)
    {
        name = eName;
        idNumber = IDGenKt.getRandomID();
        isAdmin = false;
    }

    public Employee(String eName, int eIdNumber, boolean adminRole, boolean eLoggedIn)
    {
        name = eName;
        idNumber = eIdNumber;
        isAdmin = adminRole;
        loggedIn = eLoggedIn;

    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedOnStatus(boolean sessionStatus)
    {
        loggedIn = sessionStatus;
    }

    public String getName()
    {
        return name;
    }

    public int getIdNumber()
    {
        return idNumber;
    }

    public boolean getAdminStatus()
    {
        return isAdmin;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAdminStatus(boolean adminStatus)
    {
        isAdmin = adminStatus;
    }

    public boolean isAdmin() {return false;}

    public boolean isLoggedOn() { return true;}
}
