package juliano.oliveira.iddog.ApiAccess;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedGet {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("list")
    @Expose
    private List<String> list = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FeedGet withCategory(String category) {
        this.category = category;
        return this;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public FeedGet withList(List<String> list) {
        this.list = list;
        return this;
    }

}