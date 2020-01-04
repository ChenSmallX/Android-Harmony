package top.chensmallx.android_harmony.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.sql.Date;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.model.GameSummary;

@Database(entities = {GameSummary.class}, version = 1, exportSchema = false)
//@TypeConverters({Converters.class})
public abstract class HarmonyDB extends RoomDatabase {
    public static final String DB = "harmonyDB";

    public abstract WishDao wishDao();
}
