package au.edu.unsw.infs3634.Group79App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText memail;
    EditText mpassword;
    Button LeaderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        memail = findViewById(R.id.memail);
        mpassword = findViewById(R.id.mpassword);

        LeaderButton = findViewById(R.id.Leaderboard);
        LeaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Leaderboard.class);
                startActivity(intent);


            }
        });
    }
}