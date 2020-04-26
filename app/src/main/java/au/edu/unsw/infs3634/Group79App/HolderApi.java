package au.edu.unsw.infs3634.Group79App;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HolderApi {

    //additionl url to the base url

    @GET("api.php?amount=1&category=18&type=boolean")
    Call<Quiz> getPosts();
}