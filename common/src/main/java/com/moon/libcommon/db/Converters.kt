package com.moon.libcommon.db

import androidx.room.TypeConverter
import com.moon.libcommon.db.entity.ChatType
import com.moon.libcommon.db.entity.SendType
import com.moon.libcommon.db.entity.Status

class Converters {
    @TypeConverter
    fun intToChatType(value: Int): ChatType {
        return ChatType.valueOf(value)
    }

    @TypeConverter
    fun chatTypeToInt(type: ChatType): Int {
        return type.ordinal
    }

    @TypeConverter
    fun intToStatus(value: Int): Status {
        return Status.valueOf(value)
    }

    @TypeConverter
    fun statusToInt(status: Status): Int {
        return status.ordinal
    }

    @TypeConverter
    fun intToMessageType(value: Int): MessageType {
        return MessageType.valueOf(value)
    }

    @TypeConverter
    fun mssageTypeToInt(type: MessageType): Int {
        return type.value
    }

    @TypeConverter
    fun intToSendType(value: Int): SendType {
        return SendType.valueOf(value)
    }

    @TypeConverter
    fun sendTypeToInt(type: SendType): Int {
        return type.ordinal
    }


}