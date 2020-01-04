package top.chensmallx.android_harmony.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import top.chensmallx.android_harmony.model.GameSummary;

@Dao
public interface WishDao {
    @Query("SELECT * FROM " + "wishes")
    List<GameSummary> getAll();

    @Query("SELECT * FROM " + "wishes" + " WHERE id=:gid")
    GameSummary findById(int gid);

    @Query("SELECT * FROM " + " wishes " + " LIMIT :offset, :len")
    List<GameSummary> getByLimit(int offset, int len);

    @Insert
    void insertAll(GameSummary... gameSummaries);

    @Delete
    void delete(GameSummary gameSummary);
}
