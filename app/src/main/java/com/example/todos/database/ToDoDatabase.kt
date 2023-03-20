package com.example.todos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDos::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun ToDoDao(): ToDoDao
}

object ToDoDb {
    @Volatile
    private var INSTANCE: ToDoDatabase? = null

    fun getInstance(context: Context): ToDoDatabase {
        if (INSTANCE == null) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "todo-database"
                ).build()
            }
        }
        return INSTANCE!!
    }
}

