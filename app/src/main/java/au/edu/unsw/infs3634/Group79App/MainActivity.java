package au.edu.unsw.infs3634.Group79App;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import au.edu.unsw.infs3634.Group79App.Room.SignupTable;


/***************************************************************************************
                                 REFERENCES

 *    Title: Making a Notes App Using Room Database
 *    Author: PAVEENT SINGH
 *    Date: ACCESSED 04-04-2020
 *    Code version: Jan 10, 2019
 *    Availability: https://www.pluralsight.com/guides/making-a-notes-app-using-room-database
 *
 *   Title: Android Room Database Example – Building a Todo App
 *   Author: Belal Khan
 *   Date: ACCESSED 04-04-2020
 *   Code version: July 27, 2018
 *   Availability: https://www.simplifiedcoding.net/android-room-database-example/#Database-Client
 *
 *   Title: Simple RecyclerView (Java)
 *   Author: codinginflow
 *   Date: ACCESSED 04-04-2020
 *   Code version: Oct 19, 2017
 *   Availability: https://codinginflow.com/tutorials/android/simple-recyclerview-java/part-1-layouts-model-class
 *
 *
 ***************************************************************************************/




public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    EditText memail;
    EditText mpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        memail = findViewById(R.id.memail);
        mpassword = findViewById(R.id.mpassword);

        //SignupActivity is called when the user clicks on the sign up button
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        //When user clicks on login button, they receive an alert or are successful in logging in
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = memail.getText().toString();
                String password = mpassword.getText().toString();

                if (email.isEmpty()) {
                    memail.setError("Type in 'email'");
                    memail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    mpassword.setError("Password required");
                    mpassword.requestFocus();
                    return;
                }

               if(email.equals("email") && password.equals("password")) {
                    memail.setText("");
                    mpassword.setText("");
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    getDetails();
                } else {
                   openLoginDialog();
               }
            }
        });

        getDetails();

    }

    public void openLoginDialog() {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.show(getSupportFragmentManager(), "Login Dialog");
    }

    //Method adds user's details to database once they are successful in signing up
    private void getDetails() {
        class GetDetails extends AsyncTask<Void, Void, List<SignupTable>> {

            @Override
            protected List<SignupTable> doInBackground(Void... voids) {
                List<SignupTable> tableList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .signupDao()
                        .getDetails();

                return tableList;

            }

            @Override
            protected void onPostExecute(List<SignupTable> tasks) {
                super.onPostExecute(tasks);
                //TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                //recyclerView.setAdapter(adapter);
            }
        }

        GetDetails gd = new GetDetails();
        gd.execute();
    }

}



