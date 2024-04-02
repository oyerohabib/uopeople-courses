package hellofx;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class WeatherApp extends Application {

    private static final String API_KEY = "881b4cad37f5900512e1388c26bb3141";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private TextField locationInput;
    private Label temperatureLabel;
    private Label humidityLabel;
    private Label windLabel;
    private ImageView weatherIcon;
    // Add more GUI components as needed

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Information App");

        // Initialize GUI components
        locationInput = new TextField();
        Button searchButton = new Button("Search");
        temperatureLabel = new Label();
        humidityLabel = new Label();
        windLabel = new Label();
        weatherIcon = new ImageView();

        // Layout setup
        VBox layout = new VBox(10);
        layout.getChildren().addAll(locationInput, searchButton, temperatureLabel, humidityLabel, windLabel,
                weatherIcon);

        // Event handlers
        searchButton.setOnAction(e -> {
            try {
                fetchWeatherData();
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Show GUI
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchWeatherData() throws JSONException {
        String location = locationInput.getText().trim();
        String apiUrl = String.format(API_URL, location, API_KEY);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.next());
            }
            scanner.close();

            JSONObject json = new JSONObject(response.toString());
            updateWeatherInfo(json);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    private void updateWeatherInfo(JSONObject json) throws JSONException {
        double temperature = json.getJSONObject("main").getDouble("temp");
        double humidity = json.getJSONObject("main").getDouble("humidity");
        double windSpeed = json.getJSONObject("wind").getDouble("speed");
        String iconCode = json.getJSONArray("weather").getJSONObject(0).getString("icon");

        temperatureLabel.setText("Temperature: " + temperature + "°C");
        humidityLabel.setText("Humidity: " + humidity + "%");
        windLabel.setText("Wind Speed: " + windSpeed + " m/s");

        // Load weather icon
        Image icon = new Image("http://openweathermap.org/img/w/" + iconCode + ".png");
        weatherIcon.setImage(icon);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
