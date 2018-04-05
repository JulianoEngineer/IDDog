package juliano.oliveira.iddog;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static IDDogService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(IDDogService.class);
    }
}