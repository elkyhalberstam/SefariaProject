package sefariaproject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sefariaproject.text.Text;

import java.util.List;

public interface SefariaService {

    @GET("/api/texts/{ref}")
    Observable<Text> getSefariaText(@Path("ref") String ref);

}
