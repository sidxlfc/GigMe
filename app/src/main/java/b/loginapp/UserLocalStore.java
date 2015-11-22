package b.loginapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Siddharth Shah on 9/18/2015.
 */
public class UserLocalStore
{
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)
    {
        System.out.println("Before Shared Preference is called : " + " " + user.name  + " " + user.uname  + " " +  user.pass  + " " +  user.age);
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("uname", user.uname);
        spEditor.putString("pass", user.pass);
        spEditor.putInt("age", user.age);
        spEditor.commit();
        System.out.println("After commit : " + " " + user.name + " " + user.uname + " " + user.pass + " " + user.age);
    }

    public User getLoggedInUser()
    {
        String name = userLocalDatabase.getString("name", "");
        String uname = userLocalDatabase.getString("uname", "");
        String pass = userLocalDatabase.getString("pass", "");
        int age = userLocalDatabase.getInt("age", -1);

        User loggedInUser = new User(name, uname, pass, age);

        return loggedInUser;
    }

    public void setUserLoggedIn(boolean flag)
    {
        System.out.println("Entered setUserLoggedIn"/* + " " +userLocalDatabase.getString("name", "")+ " " +userLocalDatabase.getString("uname", "")+ " " +userLocalDatabase.getString("pass", "")+ " " +userLocalDatabase.getString("age", "")*/);
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", flag);
        spEditor.commit();
    }

    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
    }

    public boolean getUserLoggedIn()
    {
        if(userLocalDatabase.getBoolean("loggedIn", false) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}