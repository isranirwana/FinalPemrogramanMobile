package com.example.cinecore.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.Model.TvModel;

public class ModelHelper {

    public static void insertMovie(Context context, MovieModel movieModel) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MovieEntry.COLUMN_ID, movieModel.getId());
        values.put(DatabaseContract.MovieEntry.COLUMN_BACKDROP_PATH, movieModel.getBackdrop_path());
        values.put(DatabaseContract.MovieEntry.COLUMN_ORIGINAL_TITLE, movieModel.getOriginal_title());
        values.put(DatabaseContract.MovieEntry.COLUMN_RELEASE_DATE, movieModel.getRelease_date());
        values.put(DatabaseContract.MovieEntry.COLUMN_OVERVIEW, movieModel.getOverview());
        values.put(DatabaseContract.MovieEntry.COLUMN_POSTER_PATH, movieModel.getPoster_path());
        values.put(DatabaseContract.MovieEntry.COLUMN_VOTE_AVERAGE, movieModel.getVote_average());

        db.insert(DatabaseContract.MovieEntry.TABLE_NAME, null, values);
    }

    public static void insertTv(Context context, TvModel tvModel) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TvShowEntry.COLUMN_ID, tvModel.getId());
        values.put(DatabaseContract.TvShowEntry.COLUMN_BACKDROP_PATH, tvModel.getBackdrop_path());
        values.put(DatabaseContract.TvShowEntry.COLUMN_ORIGINAL_NAME, tvModel.getOriginal_name());
        values.put(DatabaseContract.TvShowEntry.COLUMN_FIRST_AIR_DATE, tvModel.getFirst_air_date());
        values.put(DatabaseContract.TvShowEntry.COLUMN_OVERVIEW, tvModel.getOverview());
        values.put(DatabaseContract.TvShowEntry.COLUMN_POSTER_PATH, tvModel.getPoster_path());
        values.put(DatabaseContract.TvShowEntry.COLUMN_VOTE_AVERAGE, tvModel.getVote_average());

        db.insert(DatabaseContract.TvShowEntry.TABLE_NAME, null, values);
    }

    public static void deleteMovie(Context context, int movieId) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = DatabaseContract.MovieEntry.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(movieId)};

        db.delete(DatabaseContract.MovieEntry.TABLE_NAME, whereClause, whereArgs);
    }

    public static void deleteTv(Context context, int tvId) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = DatabaseContract.TvShowEntry.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(tvId)};

        db.delete(DatabaseContract.TvShowEntry.TABLE_NAME, whereClause, whereArgs);
    }

    public static boolean isFavoriteMovie(Context context, int movieId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseContract.MovieEntry.TABLE_NAME,
                null,
                DatabaseContract.MovieEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(movieId)},
                null,
                null,
                null
        );

        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

    public static boolean isFavoriteTv(Context context, int tvId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseContract.TvShowEntry.TABLE_NAME,
                null,
                DatabaseContract.TvShowEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(tvId)},
                null,
                null,
                null
        );

        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }
}
