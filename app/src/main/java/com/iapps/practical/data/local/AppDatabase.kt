package com.iapps.practical.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iapps.practical.data.local.dao.FeedDao
import com.iapps.practical.data.local.entity.FeedEntity

@Database(entities = [FeedEntity::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val feedDao: FeedDao
}