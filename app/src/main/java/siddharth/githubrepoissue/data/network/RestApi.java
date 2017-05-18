package siddharth.githubrepoissue.data.network;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import siddharth.githubrepoissue.data.model.Issue;


public interface RestApi {

    String OWNER = "owner";
    String REPO = "repo";


    @GET("repos/{owner}/{repo}/issues")
    Observable<List<Issue>> getIssues(@Path(OWNER) String owner, @Path(REPO) String repo);


}
