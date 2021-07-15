package com.newsapi.newsfeeder.data.db

import androidx.room.TypeConverter
import com.newsapi.newsfeeder.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source("", name)
    }
}