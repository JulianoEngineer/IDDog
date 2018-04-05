package juliano.oliveira.iddog;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GET {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("list")
    @Expose
    private ArrayList<String> list = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public GET withCategory(String category) {
        this.category = category;
        return this;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public GET withList(ArrayList<String> list) {
        this.list = list;
        return this;
    }

}