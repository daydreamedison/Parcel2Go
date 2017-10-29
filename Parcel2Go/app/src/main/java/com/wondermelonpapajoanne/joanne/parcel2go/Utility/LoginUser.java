package com.wondermelonpapajoanne.joanne.parcel2go.Utility;

/**
 * Created by Sam on 10/20/2017.
 */

public class LoginUser {
    private String email;
    private String password;
    private int account_type;
    private boolean status;

    public LoginUser(String email, String password, int account_type, boolean status)
    {
        this.email = email;
        this.password = password;

    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getAccountType()
    {
        if(account_type == 1)
        {
            return "driver";
        }
        else
            return "customer";
    }
}
