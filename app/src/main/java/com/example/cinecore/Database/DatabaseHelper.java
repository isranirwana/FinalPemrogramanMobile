package com.example.cinecore.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.Model.TvModel;
import com.example.cinecore.Database.DatabaseContract.MovieEntry;
import com.example.cinecore.Database.DatabaseContract.TvShowEntry;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "media_database";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    private static final String CREATE_TABLE_MOVIE =
            "CREATE TABLE " + DatabaseContract.MovieEntry.TABLE_NAME + "(" +
                    DatabaseContract.MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT," +
                    DatabaseContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT," +
                    DatabaseContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT," +
                    DatabaseContract.MovieEntry.COLUMN_OVERVIEW + " TEXT," +
                    DatabaseContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT," +
                    DatabaseContract.MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT" +
                    ")";

    private static final String CREATE_TABLE_TV =
            "CREATE TABLE " + DatabaseContract.TvShowEntry.TABLE_NAME + "(" +
                    DatabaseContract.TvShowEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.TvShowEntry.COLUMN_BACKDROP_PATH + " TEXT," +
                    DatabaseContract.TvShowEntry.COLUMN_ORIGINAL_NAME + " TEXT," +
                    DatabaseContract.TvShowEntry.COLUMN_FIRST_AIR_DATE + " TEXT," +
                    DatabaseContract.TvShowEntry.COLUMN_OVERVIEW + " TEXT," +
                    DatabaseContract.TvShowEntry.COLUMN_POSTER_PATH + " TEXT," +
                    DatabaseContract.TvShowEntry.COLUMN_VOTE_AVERAGE + " TEXT" +
                    ")";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MovieEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TvShowEntry.TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public List<MovieModel> getAllFavoriteMovies() {
        List<MovieModel> favoriteMovies = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                MovieEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(MovieEntry.COLUMN_ID));
                String backdropPath = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_BACKDROP_PATH));
                String originalTitle = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_ORIGINAL_TITLE));
                String releaseDate = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE));
                String overview = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_OVERVIEW));
                String posterPath = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_POSTER_PATH));
                String voteAverage = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE));

                MovieModel movie = new MovieModel(id, backdropPath, originalTitle, releaseDate, overview, posterPath, voteAverage);
                favoriteMovies.add(movie);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return favoriteMovies;
    }

    public List<TvModel> getAllFavoriteTVs() {
        List<TvModel> favoriteTVs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseContract.TvShowEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(TvShowEntry.COLUMN_ID));
                String backdropPath = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_BACKDROP_PATH));
                String originalName = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_ORIGINAL_NAME));
                String firstAirDate = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_FIRST_AIR_DATE));
                String overview = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_OVERVIEW));
                String posterPath = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_POSTER_PATH));
                String voteAverage = cursor.getString(cursor.getColumnIndex(TvShowEntry.COLUMN_VOTE_AVERAGE));

                TvModel tv = new TvModel(id, backdropPath, originalName, firstAirDate, overview, posterPath, voteAverage);
                favoriteTVs.add(tv);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return favoriteTVs;
    }

    public void deleteFavoriteMovie(int movieId) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = DatabaseContract.MovieEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(movieId) };
        db.delete(DatabaseContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteFavoriteTV(int tvId) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = DatabaseContract.TvShowEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(tvId) };
        db.delete(DatabaseContract.TvShowEntry.TABLE_NAME, selection, selectionArgs);
    }
}
