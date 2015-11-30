package csci3320.defuse;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by kuilin on 4/19/15.
 */
public class DatabaseOperations extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    public static final int database_version = 1;
    // Score Table Columns names
    public static final String USER_NAME = "user_name";
    public static final String USER_SCORE = "user_score";
    public static final String ID = "id";
    // Database Name
    public static final String DATABASE_NAME = "defuse_info";
    // Score table name
    public static final String TABLE_NAME = "highscores";

    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " integer primary key autoincrement, " + USER_NAME + " TEXT, " + USER_SCORE + " Integer );";
        db.execSQL(CREATE_SCORE_TABLE);
        Log.d("Database operations", "Table created");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("Drop table if exists " + TABLE_NAME);
        // Create tables again
        onCreate(db);

    }

    // All Operations (Create, Read, Update, Delete)
    // Adding new user information
    public void putInformation(String name, int score) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, name); // User name
        cv.put(USER_SCORE, score); // User score
        Log.d("Database Insertion", "Inserting " + name + ", " + score);
        // Inserting row
        sql.insert(TABLE_NAME, null, cv);
        // Closing database connection
        close();

    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("Drop table if exists " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    // Getting all user information
    public ArrayList<UserScore> getInformation() {
        ArrayList<UserScore> scoreList = new ArrayList<UserScore>();
        // Select all query
        String selectQuery = "Select * from " + TABLE_NAME + " ORDER BY " + USER_SCORE + " DESC";
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.rawQuery(selectQuery, null);
        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserScore x = new UserScore(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));

                scoreList.add(x);

            } while (cursor.moveToNext());
        }
        cursor.close();

        // return score list
        return scoreList;
    }

    class UserScore {

        UserScore(int x, String n, int s) {
            id = x;
            name = n;
            score = s;
        }

        int id;
        int score;
        String name;
    }

    class UserScoreAdapter extends ArrayAdapter<UserScore> {

        // declaring class members
        private Context context;
        private int layoutResourceId;
        private ArrayList<UserScore> arrayListData = null;

        public UserScoreAdapter(Context context, int layoutResourceId, ArrayList<UserScore> arrayListData) {
            super(context, layoutResourceId, arrayListData);
            this.arrayListData = arrayListData;
            this.layoutResourceId=layoutResourceId;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // assign the view we are converting to a local variable
            View row = convertView;

            if(row==null){
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId,parent,false);

            }


            UserScore i = arrayListData.get(position);

            if(i != null) {
                TextView txtPlayerName = (TextView) row.findViewById(R.id.txtPlayerName);
                TextView txtScore = (TextView) row.findViewById(R.id.txtScore);


                if(txtPlayerName !=null)
                    txtPlayerName.setText(i.name);
                if(txtScore !=null)
                    txtScore.setText(String.valueOf(i.score));

            }
            return row;
        }

    }

}
