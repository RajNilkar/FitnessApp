FitnessApp

Overview

FitnessApp is an Android mobile application developed to help users track their fitness journey through three main functionalities:
Workout Logging: Log exercises and track different muscle group activities.
Calorie Tracking: Search for meals, log calorie and macronutrient intake, and monitor daily/weekly consumption.
Progress Tracking: Visualize weight changes over time with a dynamic graph and track personal progress.
The app is designed with a simple, user-friendly interface and uses modern Android development tools and libraries.

Features

1. User Authentication
   Users can sign up and log in securely.
   Authentication data is stored locally using Room Database.
2. Workout Logging
   Users can search for exercises by muscle group using the API Ninjas Exercises API.
   Logged workouts include details such as exercise name, muscle group, and type.
   Workout data is saved locally.
3. Calorie Tracking
   Users can search for meals and log their calories, protein, carbs, fats, and sodium.
   Data is fetched using the Edamam Recipe API.
   Logged meals display meal images and nutritional breakdowns.
   Users can set daily calorie goals and track their progress visually through a progress bar.
4. Progress Visualization
   Users can log weight entries over time.
   A dynamic line chart displays weight trends using MPAndroidChart.
   The progress graph is organized inside a clean CardView for better visual structure.
   
Technologies Used

Kotlin for Android Development
Room Database for local data storage
Retrofit2 for API integration
OkHttp with logging interceptors for network communication
MPAndroidChart for chart and graph display
Glide for image loading
Firebase Authentication (optionally integrated, depending on features)
ConstraintLayout, CardView, RecyclerView for UI components

API Integrations

Exercises API from API Ninjas (used for exercise searching)
Recipe Search API from Edamam (used for calorie and meal tracking)

How to Build and Run

Clone the repository:
git clone https://github.com/RajNilkar/FitnessApp.git
Open the project in Android Studio.
Ensure you have a working internet connection (for API requests).
Update any necessary API keys inside:
RetrofitInstance.kt (for Exercises API)
RecipeSearchActivity.kt (for Recipe API)
Sync Gradle and run the app on an emulator or device.

Notes

The free tier of the Exercises API has limitations. An upgrade may be required if exceeding daily API call limits.
The project is configured to fallback to destructive migration during Room Database updates for simplicity.
API keys are hardcoded for demonstration purposes. For production, they should be secured appropriately.


Contributors

- Raj Nilkar
- Rubicel Trejo