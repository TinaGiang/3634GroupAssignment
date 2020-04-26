package au.edu.unsw.infs3634.Group79App;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;




public class LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Button signOutButton;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set nav button
        drawerLayout = findViewById(R.id.menu);
        NavigationView nv = findViewById(R.id.navView);
        nv.setNavigationItemSelectedListener(this);

        //Instantiating action bar and declaring a sign out method
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        //default page if no previous saved actviity
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TakeQuizFragment()).commit();

           nv.setCheckedItem(R.id.navView);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.super.onBackPressed();
            }
        });
    }

    //This method enables user to navigate different screens
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.myAwards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AwardsFragment()).commit();

                break;

            case R.id.Leaderboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LeaderboardFragment()).commit();

                break;

            case R.id.takeQuiz:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TakeQuizFragment()).commit();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Method prevents user from going back to login screen unless sign out button is clicked
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }

    }

}
