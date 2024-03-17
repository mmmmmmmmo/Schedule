package com.moon.libcommon.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.moon.libcommon.db.dao.BindUserDao;
import com.moon.libcommon.db.dao.FriendDao;
import com.moon.libcommon.db.dao.MessageDao;
import com.moon.libcommon.db.dao.SessionDao;
import com.moon.libcommon.db.entity.BindUserEnity;
import com.moon.libcommon.db.entity.ChatMessages;
import com.moon.libcommon.db.entity.ChatSession;
import com.moon.libcommon.db.entity.Friend;

@Database(entities = {ChatMessages.class, ChatSession.class, Friend.class, BindUserEnity.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class MessageDB extends RoomDatabase {

    public abstract MessageDao getMessageDao();

    public abstract SessionDao getSessionDao();

    public abstract FriendDao getFriendDao();

    public abstract BindUserDao getBindUserDao();

    public static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE BindUserEnity "
                    + " ADD COLUMN sensor INTEGER");
        }
    };
}
