package com.example.dubl_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class IncomeActivity extends AppCompatActivity {

    ListView incomeList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor incomeCursor;
    SimpleCursorAdapter incomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        //setContentView(R.layout.my_line);
        setTitle("Доходы");

        incomeList = (ListView)findViewById(R.id.list);
        incomeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddIncomeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }
    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        incomeCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_INCOME, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.COLUMN_ID_INCOME, DatabaseHelper.COLUMN_CATEGORY_ID_INCOME, DatabaseHelper.COLUMN_DOCUMENT_ID_INCOME,
                DatabaseHelper.COLUMN_COST_INCOME, DatabaseHelper.COLUMN_DATE_INCOME, DatabaseHelper.COLUMN_COMMENT_INCOME};
        // создаем адаптер, передаем в него курсор
       /* incomeAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                incomeCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);*/
        int[] toViews = {R.id.text1, R.id.text2,R.id.text3, R.id.text4,R.id.text5, R.id.text6,};
        incomeAdapter = new SimpleCursorAdapter(this, R.layout.my_line,
                incomeCursor, headers,toViews , 0);
        incomeList.setAdapter(incomeAdapter);
    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, AddIncomeActivity.class);
        startActivity(intent);
    }
    public void esc(View view){
        //onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        incomeCursor.close();
    }
}
