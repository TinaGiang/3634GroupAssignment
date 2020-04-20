package au.edu.unsw.infs3634.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import au.edu.unsw.infs3634.signup.Room.SignupDao;
import au.edu.unsw.infs3634.signup.Room.SignupDatabase;
import au.edu.unsw.infs3634.signup.Room.SignupTable;

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
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        //SignupTable data = new SignupTable();

        if (memail.toString().isEmpty()) {
            memail.setError("Email required");
            memail.requestFocus();
            //      return;
        }

        if (mpassword.toString().isEmpty()) {
            mpassword.setError("Password required");
            mpassword.requestFocus();
            //     return;
        }

        //trying to see if it can reach the loop


        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = memail.getText().toString();
                String password = mpassword.getText().toString();




               if(email.equals("email")) {
                {

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    getDetails();
                }
            }
            }
        });

        getDetails();

    }


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



