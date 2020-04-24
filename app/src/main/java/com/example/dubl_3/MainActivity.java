package com.example.dubl_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   /* ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  userList = (ListView)findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());*/
    }
    /*   @Override
   public void onResume() {
         super.onResume();
         // открываем подключение
         db = databaseHelper.getReadableDatabase();

         //получаем данные из бд в виде курсора
         userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
         // определяем, какие столбцы из курсора будут выводиться в ListView
         String[] headers = new String[] {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
         // создаем адаптер, передаем в него курсор
         userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                 userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
         userList.setAdapter(userAdapter);
     }*/
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void adds(View view){
        Intent intent = new Intent(this, CostActivity.class);
        startActivity(intent);
    }
    public void addi(View view){
        Intent intent1 = new Intent(this, IncomeActivity.class);
        startActivity(intent1);
    }
    public void stat(View view){
        Intent intent1 = new Intent(this, StatActivity.class);
        startActivity(intent1);
    }
  /*  @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }*/
}
/*<Button
android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Cost"
        android:onClick="add"
        android:textSize="18dp"/>*/