package edu.umd.cs.sodorfriends2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Vibha on 4/7/2016.
 */
public class SodorDB {


        private static final String DB_NAME = "sodor.db";
        private static final int DB_VERSION = 2;
        private static final String TRAIN_TABLE = "traintable";
        private static final String COL_ID = "_id";

        private static final String COL_NAME = "name";
        private static final String COL_IMAGE = "image";

        //SQL to create the table
        private static final String CREATE_TRAIN_TABLE
                = "CREATE TABLE " + TRAIN_TABLE + "(" + COL_ID + " INTEGER, "
                                                      + COL_NAME + " TEXT, "
                                                      + COL_IMAGE + " BLOB);";

        private static final String DROP_TRAIN_TABLE
                = "DROP TABLE IF EXISTS " + TRAIN_TABLE;

        private static class DBHelper extends SQLiteOpenHelper {

            private Context mContext;

            public DBHelper(Context context) {
                super(context, DB_NAME, null, DB_VERSION);
                mContext = context;

            }

            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_TRAIN_TABLE);
                SQLiteStatement insert = db.compileStatement("INSERT INTO " + TRAIN_TABLE + " VALUES (?, ?, ?)");

                Drawable[] images = new Drawable[3];
                images[0] = ContextCompat.getDrawable(mContext, R.drawable.thomashappy);
                images[1] = ContextCompat.getDrawable(mContext, R.drawable.edward);
                images[2] = ContextCompat.getDrawable(mContext, R.drawable.percy);
                Bitmap[] bitmaps = new Bitmap[3];
                ArrayList byte_arrays = new ArrayList<byte[]>();
                for (int i = 0; i < 3; i++) {
                    bitmaps[i] = ((BitmapDrawable) images[i]).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmaps[i].compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte_arrays.add(stream.toByteArray());
                }


                insert.bindLong(1, 1);
                insert.bindString(2, "Thomas");
                insert.bindBlob(3, (byte[]) byte_arrays.get(0));
                insert.execute();
                insert.bindLong(1, 2);
                insert.bindString(2, "Edward");
                insert.bindBlob(3, (byte[])byte_arrays.get(1));
                insert.execute();
                insert.bindLong(1, 6);
                insert.bindString(2, "Percy");
                insert.bindBlob(3, (byte[])byte_arrays.get(2));
                insert.execute();

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

            Log.d("SODORDB", "in getTrains()");
            openReadableDB();
            String[] projection = { COL_ID, COL_NAME, COL_IMAGE };
            Cursor c = mDb.query(TRAIN_TABLE, projection, null, null, null, null, null);
            ArrayList<Train> trains = new ArrayList<Train>();
            int COL_NAME_INDEX = c.getColumnIndex(COL_NAME);
            int COL_NUM_INDEX = c.getColumnIndex(COL_ID);
            int COL_IMAGE_INDEX = c.getColumnIndex(COL_IMAGE);
            c.moveToFirst();
            for (int i=0; i < c.getCount(); i++) {
                String name = c.getString(COL_NAME_INDEX);
                String num = c.getString(COL_NUM_INDEX);
                byte[] bytes = c.getBlob(COL_IMAGE_INDEX);
                Log.d("SODORDB", "just got name " + name);
                Train train = new Train(name, num, bytes);
                trains.add(train);
                c.moveToNext();
            }
            c.close();
            closeDB();
            return trains;
        }


    }

