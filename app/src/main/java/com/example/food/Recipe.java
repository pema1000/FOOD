package com.example.food;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recipe extends AppCompatActivity {

    Button GotoHome, inventoryDisplay;
    private ListView listView;
    private List<String> recipeList;
    private List<String> recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        GotoHome=findViewById(R.id.homepage);
        inventoryDisplay=findViewById(R.id.inventoryaddbtn);
        listView=findViewById(R.id.listView);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        GotoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        inventoryDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Inventory.class));
                finish();
            }
        });

        recipeList = new ArrayList<>();
        recipeList.add("Pizza");
        recipeList.add("Momo");
        recipeList.add("Mutton");
        recipeList.add("French Fries");
        recipeList.add("Salad");

        recipe = new ArrayList<>();
        recipe.add("Take the dough out and roll it into a circle, and put chicken and hot.");
        recipe.add("Rolled meat in dough and steam");
        recipe.add("Hot oil and put meat and cooked.");
        recipe.add("Cut potatoes and put in hot oil");
        recipe.add("Put all vegies and mix it.");

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                recipeList);
        listView.setAdapter(arr);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                alertDialogBuilder.setTitle("Recipe for: " + selectedItem);
                alertDialogBuilder.setMessage(recipe.get(position));

                alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}