package au.edu.unsw.infs3634.Group79App;

import android.content.Context;

import androidx.room.Room;

import au.edu.unsw.infs3634.Group79App.Room.SignupDatabase;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;

    //our app database object
    private SignupDatabase signupDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        //creating the app database with Room database builder
        signupDatabase = Room.databaseBuilder(context, SignupDatabase.class, "Signup Details").fallbackToDestructiveMigration().build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public SignupDatabase getAppDatabase() {
        return signupDatabase;
    }
}
