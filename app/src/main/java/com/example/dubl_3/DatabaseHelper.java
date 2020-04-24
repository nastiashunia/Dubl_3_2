package com.example.dubl_3;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE_COST = "costs"; // название таблицы в бд
    static final String TABLE_INCOME = "income"; // название таблицы в бд
    static final String TABLE_DOCUMENT = "document"; // название таблицы в бд
    static final String TABLE_CATEGORY = "category"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID_COST = "_id";
    public static final String COLUMN_CATEGORY_ID_COST = "category_id"; //будет категорией выпадающим списком
    public static final String COLUMN_COST_COST = "cost";
    public static final String COLUMN_DATE_COST = "date";
    public static final String COLUMN_COMMENT_COST = "comment";
    public static final String COLUMN_DOCUMENT_ID_COST = "comment";

    public static final String COLUMN_ID_INCOME = "_id";
    public static final String COLUMN_CATEGORY_ID_INCOME = "category_id"; //будет категорией  выпадающим списком
    public static final String COLUMN_COST_INCOME = "cost";
    public static final String COLUMN_DATE_INCOME = "date";
    public static final String COLUMN_COMMENT_INCOME = "comment";
    public static final String COLUMN_DOCUMENT_ID_INCOME = "comment";

    public static final String COLUMN_ID_DOCUMENT = "_id";
    public static final String COLUMN_NAME_DOCUMENT = "name";
    public static final String COLUMN_DATE_START_DOCUMENT = "date_start";
    public static final String COLUMN_DATE_FINISH_DOCUMENT = "date_finish";

    public static final String COLUMN_ID_CATEGORY = "_id";
    public static final String COLUMN_NAME_CATEGORY= "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE costs (" + COLUMN_ID_COST + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY_ID_COST + " INTEGER, " + COLUMN_COST_COST + " INTEGER,"
                + COLUMN_DOCUMENT_ID_COST + " INTEGER," + COLUMN_DATE_COST + " TEXT," + COLUMN_COMMENT_COST + " TEXT);");
        // добавление начальных данных
       /* db.execSQL("INSERT INTO "+ TABLE_COST +" (" + COLUMN_CATEGORY_ID_COST + ", " + COLUMN_DOCUMENT_ID_COST
                + ", " + COLUMN_COST_COST + ", " + COLUMN_DATE_COST +", "+ COLUMN_COMMENT_COST +") VALUES ('Долг', 3000, 7,'12-11-2019','LLL');"); */
        db.execSQL("INSERT INTO "+ TABLE_COST +" (" + COLUMN_CATEGORY_ID_COST + ", " + COLUMN_DOCUMENT_ID_COST
                + ", " + COLUMN_COST_COST  + ", " + COLUMN_DATE_COST +","+ COLUMN_COMMENT_COST +") VALUES (1, 2, 200, '10-10-2020', 'просмотр');");

        db.execSQL("CREATE TABLE income (" + COLUMN_ID_INCOME + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY_ID_INCOME + " INTEGER, " + COLUMN_COST_INCOME + " INTEGER,"
                + COLUMN_DOCUMENT_ID_INCOME + " INTEGER," + COLUMN_DATE_INCOME + " TEXT," + COLUMN_COMMENT_INCOME + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE_INCOME +" (" + COLUMN_CATEGORY_ID_INCOME + ", " + COLUMN_DOCUMENT_ID_INCOME
                + ", " + COLUMN_COST_INCOME  + ", " + COLUMN_DATE_INCOME +","+ COLUMN_COMMENT_INCOME +") VALUES (1, 2, 200, '10-10-2020', 'просмотр');");

        db.execSQL("CREATE TABLE document (" + COLUMN_ID_DOCUMENT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_DOCUMENT + " TEXT, " + COLUMN_DATE_START_DOCUMENT + " TEXT," + COLUMN_DATE_FINISH_DOCUMENT + " TEXT);");

        db.execSQL("CREATE TABLE category (" + COLUMN_ID_CATEGORY + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME_CATEGORY + " TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COST);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_INCOME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DOCUMENT);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        onCreate(db);
    }
}