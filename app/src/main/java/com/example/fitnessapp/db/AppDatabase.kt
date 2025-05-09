package com.example.fitnessapp.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Workout::class, CaloriesConsumed::class, WeightLog::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun caloriesDoa(): CaloriesDao
    abstract fun weightLogDao(): WeightLogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            /*synchronized(this){
                var instance= INSTANCE
                if(instance==null) {
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "fitness_app_database"
                    ).build()
                    INSTANCE= instance
                }
                return instance
            }*/
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val database = getDatabase(context)  // Get DB instance
                            val userDao = database.userDao()
                            // Prepopulate database with a test user
                            CoroutineScope(Dispatchers.IO).launch {
                                val testUser = User(username = "testuser", password = "1234")
                                userDao.insertUser(testUser)
                                Log.d("Database", "Test user inserted: ${testUser.username}")
                            }
                        }
                    })
                    .build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}