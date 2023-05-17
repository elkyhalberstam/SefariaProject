package sefariaproject.index;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Content2 {
    private List<String> categories;
    private Long order;
    @SerializedName("primary_category")
    private String primaryCategory;
    public String enShortDesc;
    private String heShortDesc;
    public String heTitle;
    public String title;
}