package b.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etUname, etPass, etAge, etSex, etContact;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etUname = (EditText) findViewById(R.id.etUname);
        etPass = (EditText) findViewById(R.id.etPass);
        etAge = (EditText) findViewById(R.id.etAge);
        //etSex = (EditText) findViewById(R.id.etSex);
        ///etContact = (EditText)findViewById(R.id.etContact);

        btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btRegister:
                //System.out.println(etName.getText().toString() + " " + etUname.getText().toString() + " " +  etPass.getText().toString() + " " +  Integer.parseInt(etAge.getText().toString()));
                VerifyUserDetails verifyUserDetails = new VerifyUserDetails(etName.getText().toString(), etUname.getText().toString(), etPass.getText().toString(), etAge.getText().toString());

                if(verifyUserDetails.verify())
                {

                    User user = new User(etName.getText().toString(), etUname.getText().toString(), etPass.getText().toString(), Integer.parseInt(etAge.getText().toString()));
                    System.out.println(user.name + " " + user.uname + " " + user.pass + " " + user.age);
                    registerUser(user);
                    break;

                }

                else
                {
                    Toast.makeText(this, verifyUserDetails.verificationResult, Toast.LENGTH_LONG).show();
                    break;
                }

            default:
                break;
        }
    }

    private void registerUser(User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallBack()
        {
            @Override
            public void done(User returnedUser)
            {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }
        });
    }
}