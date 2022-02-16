package com.example.numad22sp_yilinzhou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class LinkCollector extends AppCompatActivity /*implements ExampleDialog.ExampleDialogListener*/{
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();
    ;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    protected void dialog(){
        final EditText linkName=new EditText(this);
        final EditText linkUrl=new EditText(this);
        Pattern pattern;

        LayoutInflater factory = LayoutInflater.from(LinkCollector.this);

        final View DialogView = factory.inflate(R.layout.dialog_layout, null);

        AlertDialog dlg = new AlertDialog.Builder(LinkCollector.this)
                .setTitle("*Link Collector*")
                .setView(DialogView)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText linkName = (EditText) DialogView.findViewById(R.id.name);
                                String strLinkName = linkName.getText().toString();
                                EditText linkUrl = (EditText) DialogView.findViewById(R.id.url);
                                String strLinkUrl = linkUrl.getText().toString();
                                if (android.util.Patterns.WEB_URL.matcher(strLinkUrl).matches()) {
                                    ItemCard item = new ItemCard(strLinkName, strLinkUrl);
                                    itemList.add(item);
                                    Snackbar snackbar = Snackbar.make(recyclerView,"Add an item",Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }
                                else{
                                    Snackbar snackbar = Snackbar.make(recyclerView, "Invalid Url", Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }


                            }
                        })
                .create();
        dlg.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkCollector.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put image information id into instance
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getLinkName());
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getLinkUrl());
        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {

                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String linkUrl  = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    com.example.numad22sp_yilinzhou.ItemCard itemCard = new com.example.numad22sp_yilinzhou.ItemCard(linkName, linkUrl);

                    itemList.add(itemCard);
                }
            }
        }

    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        com.example.numad22sp_yilinzhou.ItemClickListener itemClickListener = new com.example.numad22sp_yilinzhou.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url=itemList.get(position).getLinkUrl();
                if (!url.startsWith("http://")&&!url.startsWith("https://"))
                    url="http://" +url;
                Uri uri=Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }


        };
        rviewAdapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }


}