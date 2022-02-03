package com.example.task29_pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    protected RadioGroup rg_1;
    protected RadioGroup rg_2;
    protected RadioGroup rg_3;

    protected Button btnSave;
    protected Button btnLoad;
    protected Button btnDelete;
    protected Button btnClear;

    protected int ing_1_index;
    protected int ing_2_index;
    protected int ing_3_index;

    protected DBSupp dbSupp_1;

    SQLiteDatabase db;

    final String TAG = "MyLogs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button) findViewById(R.id.btn_1);
        btnSave.setOnClickListener(viewClickListenerSave);

        btnLoad = (Button) findViewById(R.id.btn_2);
        btnLoad.setOnClickListener(viewClickListenerLoad);

        btnDelete = (Button) findViewById(R.id.btn_3);
        btnDelete.setOnClickListener(viewClickListenerDelete);

        btnClear = (Button) findViewById(R.id.btn_4);
        btnClear.setOnClickListener(viewClickListenerClear);

        rg_1 = (RadioGroup) findViewById(R.id.rg_1);
        rg_2 = (RadioGroup) findViewById(R.id.rg_2);
        rg_3 = (RadioGroup) findViewById(R.id.rg_3);

        dbSupp_1 = new DBSupp(this);





    }

        View.OnClickListener viewClickListenerSave = new View.OnClickListener() {

            @Override
            public void onClick (View v) {

                try{


                    int ing = rg_1.getCheckedRadioButtonId();
                    switch(ing) {
                        case R.id.rb_11:
                            ing_1_index = R.id.rb_11;
                            break;
                        case R.id.rb_12:
                            ing_1_index = R.id.rb_12;
                            break;
                        case R.id.rb_13:
                            ing_1_index = R.id.rb_13;
                            break;
                        default:
                            Exception e = new Exception();
                            Toast.makeText(getApplicationContext(),
                                    "You need to check 1th ingredient",
                                    Toast.LENGTH_SHORT).show();
                            throw e;

                    }



                    ing = rg_2.getCheckedRadioButtonId();
                    switch(ing) {
                        case R.id.rb_21:
                            ing_2_index = R.id.rb_21;
                            break;
                        case R.id.rb_22:
                            ing_2_index = R.id.rb_22;
                            break;
                        case R.id.rb_23:
                            ing_2_index = R.id.rb_23;
                            break;
                        default:
                            Exception e = new Exception();
                            Toast.makeText(getApplicationContext(),
                                    "You need to check 2nd ingredient",
                                    Toast.LENGTH_SHORT).show();
                            throw e;

                    }

                    ing = rg_3.getCheckedRadioButtonId();
                    switch(ing) {
                        case R.id.rb_31:
                            ing_3_index = R.id.rb_31;
                            break;
                        case R.id.rb_32:
                            ing_3_index = R.id.rb_32;
                            break;
                        case R.id.rb_33:
                            ing_3_index = R.id.rb_33;
                            break;
                        default:
                            Exception e = new Exception();
                            Toast.makeText(getApplicationContext(),
                                    "You need to check 3rd ingredient",
                                    Toast.LENGTH_SHORT).show();
                            throw e;

                    }

                    db = dbSupp_1.getWritableDatabase();


                Log.d(TAG, "-=Insert into dbingredients=-");
                //prepare the data for insertion in the form: column name - value
                ContentValues ing_cv = new ContentValues();

                ing_cv.put("ing_1_index", ing_1_index);
                ing_cv.put("ing_2_index", ing_2_index);
                ing_cv.put("ing_3_index", ing_3_index);


                // insert row and get its ID
                long rowID = db.insert("dbingredients", null, ing_cv);
                //Log.d(TAG, "row inserted, ID = " + rowID);


                Toast.makeText(getApplicationContext(),
                        "last inserted id = "+rowID,
                        Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "-=Insert into dbingredients=-");


                }catch(Exception e){
                    Log.d(TAG, "<<<<< Something went wrong >>>>>");
                }finally {
                    dbSupp_1.close();
                }
            }

        };




        View.OnClickListener viewClickListenerLoad = new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                try {

                    db = dbSupp_1.getWritableDatabase();

                    Log.d(TAG, "-=Select from dbingredients=-");
                    //make a request for all data from the table mytable, get Cursor
                    Cursor cLoad = db.query("dbingredients", null, null, null, null, null, "id");


                    //put the cursor position on the first line of the selection
                    //if there are no rows in the selection, return false

                    if (cLoad.moveToFirst()){

                        cLoad.moveToLast();

                        int IDcol = cLoad.getColumnIndex("id");
                        int idRow = cLoad.getInt(IDcol);


                        //determine the column numbers by id

                        int ing_1_cursor_ID_col = cLoad.getColumnIndex("ing_1_index");
                        int ing_2_cursor_ID_col = cLoad.getColumnIndex("ing_2_index");
                        int ing_3_cursor_ID_col = cLoad.getColumnIndex("ing_3_index");

                        int ing_1_index = cLoad.getInt(ing_1_cursor_ID_col);
                        int ing_2_index = cLoad.getInt(ing_2_cursor_ID_col);
                        int ing_3_index = cLoad.getInt(ing_3_cursor_ID_col);

                        RadioButton rb;

                        switch(ing_1_index) {
                            case R.id.rb_11:
                                rb = (RadioButton) findViewById(R.id.rb_11);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_12:
                                rb = (RadioButton) findViewById(R.id.rb_12);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_13:
                                rb = (RadioButton) findViewById(R.id.rb_13);
                                rb.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch(ing_2_index) {
                            case R.id.rb_21:
                                rb = (RadioButton) findViewById(R.id.rb_21);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_22:
                                rb = (RadioButton) findViewById(R.id.rb_22);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_23:
                                rb = (RadioButton) findViewById(R.id.rb_23);
                                rb.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch(ing_3_index) {
                            case R.id.rb_31:
                                rb = (RadioButton) findViewById(R.id.rb_31);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_32:
                                rb = (RadioButton) findViewById(R.id.rb_32);
                                rb.setChecked(true);
                                break;
                            case R.id.rb_33:
                                rb = (RadioButton) findViewById(R.id.rb_33);
                                rb.setChecked(true);
                                break;
                            default:
                                break;
                        }



                            Log.d(TAG,
                                    "ID = " + cLoad.getInt(idRow ) +
                                            ", ing1= " + ing_1_index+
                                            ", ing2 = " + ing_2_index +
                                            ", ing3 = " + ing_3_index
                            );


                    } else {
                        //Log.d(TAG, "0 rows");
                        Toast.makeText(getApplicationContext(),
                                "no data available to select",
                                Toast.LENGTH_SHORT).show();


                        cLoad.close();
                    }
                 }catch(Exception e){

                    Log.d(TAG, "<<<<< Something went wrong >>>>>");

                }finally {
                    dbSupp_1.close();

                }


            }
        };


        View.OnClickListener viewClickListenerDelete = new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try{
                    db = dbSupp_1.getWritableDatabase();

                    Log.d(TAG, "-=Trying delete from table=-");

                    Cursor c = db.query("dbingredients", null, null, null, null, null, "id");

                    if (c.moveToFirst()) {

                        int IDcol = c.getColumnIndex("id");
                        int idRow = c.getInt(IDcol);

                        db.delete("dbingredients", "id = " +idRow, null);
                        //Log.d(TAG, "deleted rows = " + delCount);
                        Toast.makeText(getApplicationContext(),
                                "deleted row with id = " + idRow,
                                Toast.LENGTH_SHORT).show();




                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Nothing deleted" ,
                                Toast.LENGTH_SHORT).show();

                    }


                    }catch(Exception e){
                    Log.d(TAG, "<<<<< Something went wrong >>>>>");
                }finally {
                    dbSupp_1.close();
                }

            }
        };

    View.OnClickListener viewClickListenerClear = new View.OnClickListener() {
        @Override
        public void onClick (View v) {

            Log.d(TAG, "Clear selection");

            rg_1.clearCheck();
            rg_2.clearCheck();
            rg_3.clearCheck();
        }
    };
    @Override
    public void onClick(View v) {

    }

}




    class DBSupp extends SQLiteOpenHelper {

        final String TAG = "MyLogs";

        public DBSupp(Context context) {
            // superclass constructor
            super(context, "client", null, 1);
        };


        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.d(TAG, "onCreate database");

            // create a table with fields
            String myString = "create table dbingredients" +
                    "(id integer primary key autoincrement," +
                    "ing_1_index integer," +
                    "ing_2_index integer," +
                    "ing_3_index integer);";

            db.execSQL(myString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "onUpgrade database");

        }
    }