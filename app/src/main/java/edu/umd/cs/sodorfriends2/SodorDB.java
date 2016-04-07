package edu.umd.cs.sodorfriends2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vibha on 4/7/2016.
 */
public class SodorDB {


        private static final String DB_NAME = "sodor.db";
        private static final int DB_VERSION = 1;
        private static final String TRAIN_TABLE = "traintable";
        private static final String COL_ID = "_id";

        private static final String COL_NAME = "name";
        private static final int COL_NAME_INDEX = 1;

        //SQL to create the table
        private static final String CREATE_TRAIN_TABLE
                = "CREATE TABLE " + TRAIN_TABLE + "(" + COL_ID + " INTEGER," + COL_NAME + " TEXT);";

        private static final String DROP_TRAIN_TABLE
                = "DROP TABLE IF EXISTS " + TRAIN_TABLE;

        private static class DBHelper extends SQLiteOpenHelper {

            public DBHelper(Context context) {
                super(context, DB_NAME, null, DB_VERSION);

            }

            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_TRAIN_TABLE);
                db.execSQL("INSERT INTO " + TRAIN_TABLE + " VALUES (1, 'Thomas')");
                db.execSQL("INSERT INTO " + TRAIN_TABLE + " VALUES (2, 'Edward')");
                db.execSQL("INSERT INTO " + TRAIN_TABLE + " VALUES (6, 'Percy')");
            }

            public void onUpgrade(SQLiteDatabase db, int older, int newer) {
                db.execSQL(DROP_TRAIN_TABLE);
                onCreate(db);
            }

        }

        private SQLiteDatabase mDb;
        private DBHelper mDbHelper;

        public SodorDB(Context context) {
            mDbHelper = new DBHelper(context);
            mDb = null;
        }

        protected void openReadableDB() {
            mDb = mDbHelper.getReadableDatabase();
        }

        protected void closeDB() {
            if (mDb!= null) {
                mDb.close();
            }
        }

        public ArrayList<Train> getTrains() {
            openReadableDB();
            String[] projection = { COL_ID, COL_NAME };
            Cursor c = mDb.query(TRAIN_TABLE, projection, null, null, null, null, null);
            ArrayList<Train> trains = new ArrayList<Train>();
            int COL_NAME_INDEX = c.getColumnIndex(COL_NAME);
            c.moveToFirst();
            for (int i=0; i < c.getCount(); i++) {
                String name = c.getString(COL_NAME_INDEX);
                Train train = new Train(name);
                trains.add(train);
                c.moveToNext();
            }
            c.close();
            closeDB();
            return trains;
        }


    }

