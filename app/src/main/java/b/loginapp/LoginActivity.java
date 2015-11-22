package b.loginapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btLogin;
    EditText etUname, etPass;
    TextView tvRegister;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUname = (EditText) findViewById(R.id.etUname);
        etPass = (EditText) findViewById(R.id.etPass);
        btLogin = (Button) findViewById(R.id.btLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btLogin:

                String uname = etUname.getText().toString();
                String pass = etPass.getText().toString();

                VerifyUserDetails verifyUserDetails = new VerifyUserDetails(uname, pass);

                    if (verifyUserDetails.verify())
                    {
                        User loggedInUser = new User(uname, pass);
                        authenticate(loggedInUser);
                        break;
                    }

                    else
                    {
                        Toast.makeText(this, verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                        break;
                    }

            case R.id.tvRegister:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    public void authenticate(User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack(){
            @Override
            public void done(User returnedUser)
            {
                if (returnedUser == null)
                {
                    System.out.println("If");
                    showErrorMessage();
                }

                else
                {
                    System.out.println("Else");
                    //LoginActivity l = new LoginActivity();
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage()
    {
        Toast.makeText(this, "Incorrect uname and/or pass", Toast.LENGTH_LONG).show();
    }


    void logUserIn(User returnedUser)
    {
        System.out.println("Entered logUserIn: " + returnedUser.uname + " " + returnedUser.name + " " + returnedUser.pass + " " + returnedUser.age);
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        System.out.println("Just before start_main");
        startActivity(new Intent(this, MainActivity.class));
    }
}