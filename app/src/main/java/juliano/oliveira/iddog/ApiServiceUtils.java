package juliano.oliveira.iddog;

public class ApiServiceUtils {

    private ApiServiceUtils() {}

    public static final String BASE_URL = "https://iddog-api.now.sh";

    public static IDDogService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(IDDogService.class);
    }
}