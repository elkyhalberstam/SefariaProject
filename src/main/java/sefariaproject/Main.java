package sefariaproject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sefariaproject.index.Content1;
import sefariaproject.text.Text;

import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        SefariaFrame frame = new SefariaFrame();
        frame.setVisible(true);
    }
}