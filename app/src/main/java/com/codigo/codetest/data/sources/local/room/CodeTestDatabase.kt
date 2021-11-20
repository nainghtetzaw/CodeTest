package com.codigo.codetest.data.sources.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codigo.codetest.data.models.entities.*
import com.codigo.codetest.data.sources.local.room.daos.BelongsToTypeDao
import com.codigo.codetest.data.sources.local.room.daos.CastDataResponseDao
import com.codigo.codetest.data.sources.local.room.daos.GenreDao
import com.codigo.codetest.data.sources.local.room.daos.MovieDao
import com.codigo.codetest.data.sources.local.room.typeConverters.CastListTypeConverter
import com.codigo.codetest.data.sources.local.room.typeConverters.GenreListTypeConverter
import com.codigo.codetest.data.sources.local.room.typeConverters.IntListTypeConverter
import com.codigo.codetest.data.sources.local.room.typeConverters.MovieListTypeConverter

@Database(
    version = 0,
    entities = [
        BelongsToTypeEntity::class,
        MovieEntity::class,
        GenreEntity::class,
        CastDataResponseEntity::class,
        CastEntity::class
    ]
)
@TypeConverters(
    MovieListTypeConverter::class,
    CastListTypeConverter::class,
    GenreListTypeConverter::class,
    IntListTypeConverter::class
)
abstract class CodeTestDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "CodigoCodeTest.db"
        private var databaseInstance : CodeTestDatabase? = null

        fun getInstance(context : Context) : CodeTestDatabase = synchronized(this) {
            when (databaseInstance) {
                null -> {
                    databaseInstance = initDatabase(context)
                }
            }
            return  databaseInstance!!
        }

        private fun initDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                CodeTestDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }

    abstract fun movieDao() : MovieDao
    abstract fun belongsToTypeDao() : BelongsToTypeDao
    abstract fun castResponseDao() : CastDataResponseDao
    abstract fun genreDao() : GenreDao
}