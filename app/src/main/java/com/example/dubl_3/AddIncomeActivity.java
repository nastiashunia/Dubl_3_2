package com.example.dubl_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddIncomeActivity extends AppCompatActivity {

    EditText nameBox;
    EditText costBox;
    Button delButton;
    Button saveButton;
    Button escButton;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long costId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setTitle("Добавить доход");

        nameBox = (EditText) findViewById(R.id.name);
        costBox = (EditText) findViewById(R.id.cost);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        escButton = (Button) findViewById(R.id.escButton);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            costId = extras.getLong("id");
        }
        // если 0, то добавление
        if (costId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_INCOME + " where " +
                    DatabaseHelper.COLUMN_ID_INCOME + "=?", new String[]{String.valueOf(costId)});
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            costBox.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_CATEGORY_ID_INCOME, nameBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_COST_INCOME, Integer.parseInt(costBox.getText().toString()));

        if (costId > 0) {
            db.update(DatabaseHelper.TABLE_INCOME, cv, DatabaseHelper.COLUMN_ID_INCOME + "=" + String.valueOf(costId), null);
        } else {
            db.insert(DatabaseHelper.TABLE_INCOME, null, cv);
        }
        goHome();
    }
    public void delete(View view){
        db.delete(DatabaseHelper.TABLE_INCOME, "_id = ?", new String[]{String.valueOf(costId)});
        goHome();
    }
    public void esc(View view){
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, IncomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
