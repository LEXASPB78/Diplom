[# The WoW App 🌦️
> The Wow (The Weather noW) is an Android application that allows users to check the weather in different cities. The application uses data from the OpenWeatherMap API to display weather conditions.
<img src="wow_exsample.gif" alt="How the app works" height="400px" style="margin-right: 20px;">

## 🎯 Technologies Used
- *Java Programming Language*
- *Retrofit (version 2.9.0)*
- *OpenWeatherMap API*: <sup>The project uses the API version with "metric" units (degrees Celsius).<sup>
- *UI Using XML Layouts*
- *Android Studio (version 4.2.2)*

## 🔧 Functionality
📱**Main Screen:** The app opens to a main screen where users can input the city name they want to check the weather for.

🌍**Weather Data Retrieval:** After entering a city name and clicking "Gey weather" the app sends a request to an external weather API to retrieve weather data for the specified city.

☀️**Display Weather Information:**
- City name
- Current temperature in degrees Celsius
- Weather description (e.g., "Cloudy," "Sunny," "Rainy")
- Humidity percentage
- Wind speed in meters per second
- Atmospheric pressure in hPa (hectopascal)
- Weather icon corresponding to the current weather conditions
 
⚠️**Error Handling:** The app handles various types of errors:
- If the specified text isn't a city name, it displays an error message like "City not found."
- If the city's name is specified, but there is no information about the weather in the API, the appropriate message will be displayed.
- If there's an issue with the API or network connectivity, it shows a network error message.
 
↩️**Return to Main Screen:** Users can return to the main screen by clicking the "Back" button.

## 💻 Instructions for launching the project
1. Clone the repository from GitHub to your computer.
2. Open the project in your preferred Android development environment, such as Android Studio.
3. Ensure that your device or emulator is connected and configured for running Android applications.
4. Run the project on your device or emulator.
5. Enter the name of the city for which you want to retrieve weather data.
6. Click the "Search" button.\
**Enjoy the up-to-date weather information for the selected city!** 😎
]()