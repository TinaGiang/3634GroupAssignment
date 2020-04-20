package au.edu.unsw.infs3634.signup.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SignupTable {

    @PrimaryKey
    @NonNull public String semail;

    @ColumnInfo(name = "first_name")
    public String sfirstname;

    @ColumnInfo(name = "last_name")
    public String slastname;

    @ColumnInfo(name = "password")
    public String spassword;

    public static int size() {
        return SignupTable.size();
    }

    public String getFirstName() {
        return sfirstname;
    }

    public void setFirstName(String sfirstname) {
        this.sfirstname = sfirstname;
    }

    public String getLastName() {
        return slastname;
    }

    public void setFLastName(String slastname) {
        this.slastname = slastname;
    }

    public String getEmail() {
        return semail;
    }

    public void setEmail(String semail) {
        this.semail = semail;
    }

    public String getPassword() {
        return spassword;
    }

    public void setPassword(String spassword) {
        this.spassword = spassword;
    }



}
