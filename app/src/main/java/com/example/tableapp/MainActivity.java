package com.example.tableapp;

import android.content.Context;
import android.os.Bundle;

import com.example.tableapp.data.DatabaseHandler;
import com.example.tableapp.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText name;
    private EditText family;
    private EditText age;
    private EditText phone;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);

        //check if item saved
//
//        List<Item> items = databaseHandler.getAllItems();
//        for (Item item : items){
//            Log.d("Main", "onCreate: ", item.getItemName());
//        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopup();

            }
        });
    }

    private void saveItem(View view) {
        //save each item to DB
        Item item = new Item();

        String newName = name.getText().toString().trim();
        String newFamily = family.getText().toString().trim();
        int newAge = Integer.parseInt(age.getText().toString().trim());
        int newPhone = Integer.parseInt(phone.getText().toString().trim());

        item.setItemName(newName);
        item.setItemFamily(newFamily);
        item.setItemAge(newAge);
        item.setItemPhone(newPhone);

        databaseHandler.addItem(item);

        Snackbar.make(view, "Item Saved!", Snackbar.LENGTH_SHORT).show();

    }

    private void createPopup() {
        builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        name = view.findViewById(R.id.name);
        family = view.findViewById(R.id.family);
        age = view.findViewById(R.id.age);
        phone = view.findViewById(R.id.phone_num);

        saveButton = view.findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!name.getText().toString().isEmpty()
                        && !family.getText().toString().trim().isEmpty()
                        && !phone.getText().toString().trim().isEmpty()
                        && !age.getText().toString().trim().isEmpty()) {
                    saveItem(view);
                } else {
                    Snackbar.make(view, "Empty fields not allowed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
