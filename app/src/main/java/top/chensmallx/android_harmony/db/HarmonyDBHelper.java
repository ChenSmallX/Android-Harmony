package top.chensmallx.android_harmony.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HarmonyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "harmony.db";
    private static final int DB_VERSION = 1;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS  ";
    public HarmonyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WishTable.CREABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 暂时删除处理，后续数据库升级需要把原有的数据迁移到新表中
        dropTable(db, WishTable.TABLE_NAME);
        db.execSQL(WishTable.TABLE_NAME);
    }

    private void dropTable(SQLiteDatabase db, String tableName) {
        db.execSQL(DROP_TABLE + tableName);
    }
}
