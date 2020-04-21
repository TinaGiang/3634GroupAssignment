package au.edu.unsw.infs3634.Group79App;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

// Code below referenced from Medium - Android:Navigation Drawer
// Availability: https://medium.com/quick-code/android-navigation-drawer-e80f7fc2594f
//https://www.youtube.com/watch?v=zYVEMCiDcmY

public class LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.menu);

        NavigationView nv = findViewById(R.id.navView);
        nv.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {

      //      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
         //           new LeaderboardFragment()).commit();

           // nv.setCheckedItem(R.id.nav);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


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
            case R.id.profileInfo:
                //      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //              new ()).commit();

                break;
            case R.id.learningList:
                //       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //              new ()).commit();

                break;
            case R.id.takeQuiz:
                //     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //             new ()).commit();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


}
