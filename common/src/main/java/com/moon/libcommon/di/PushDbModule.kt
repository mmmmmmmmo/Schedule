package com.moon.libcommon.di

import android.content.Context
import androidx.room.Room
import com.moon.libcommon.db.MessageDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PushDbModule {
    @Provides
    @Singleton
    open fun provideMessageDB(application: Context): MessageDB {
        return Room.databaseBuilder(application, MessageDB::class.java, "message.db")
            .addMigrations(MessageDB.MIGRATION_1_2)
            .build()
    }
}