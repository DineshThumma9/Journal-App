package net.engineeringdigest.journalApp.service;

public class WeatherService {


    public static final String apiKey  = "";
    public static final String API = "";


    @Autowired
    private RestTemplate  restTemplate;


    public String getWeather(STring city)
    {
        String finalAPI = API.replace("CITY", city).replace("APIKEY", apiKey);
        String res = restTemplate.exchange(finalAPI, HttpMethod.GET, null, String.class).getBody();
        return this.restTemplate.getForObject(finalAPI, String.class);
    }
}
