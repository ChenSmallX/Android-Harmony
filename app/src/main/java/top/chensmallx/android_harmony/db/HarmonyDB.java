package top.chensmallx.android_harmony.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.model.GameSummary;

@Database(entities = {GameSummary.class}, version = 1)
public abstract class HarmonyDB extends RoomDatabase {
    public static final String DB = "harmonyDB";

    public abstract WishDao wishDao();
}
