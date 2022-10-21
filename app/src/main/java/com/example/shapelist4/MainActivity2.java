package com.example.shapelist4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements  ShapeAdapter.OnItemListener
{
    private RecyclerView recyclerView;

    public static ArrayList<Shape>shapeList=new ArrayList<Shape>();

    private ListView listView;

    private ArrayList<String> selectedFilters=new ArrayList<String>();

    private String currentSearchText="";

    private SearchView searchView;

    private Button circleButton,squareButton,rectangleButton,triangleButton,octagonButton,allButton;

    private int white,darkGray,red;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initSearchWidgets();
        initWidgets();
        setUpData();
        setUpList();
        initColors();
        lookSelected(allButton);
        selectedFilters.add("all");

    }

    private void initColors()
    {
        white= ContextCompat.getColor(getApplicationContext(),R.color.white);
        red= ContextCompat.getColor(getApplicationContext(),R.color.red);
        darkGray= ContextCompat.getColor(getApplicationContext(),R.color.darkerGray);
    }
    private void initWidgets()
    {
        circleButton=(Button)findViewById(R.id.circleFilter);
        squareButton=(Button)findViewById(R.id.squareFilter);
        rectangleButton=(Button)findViewById(R.id.rectangleFilter);
        triangleButton=(Button)findViewById(R.id.triangleFilter);
        octagonButton=(Button)findViewById(R.id.octagonFilter);
        allButton=(Button)findViewById(R.id.allFilter);

    }
    private void lookSelected(Button parsedButton)
    {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(red);
    }

    private void lookUnSelected(Button parsedButton)
    {
        parsedButton.setTextColor(white);
        parsedButton.setBackgroundColor(darkGray);

    }

    private void unselectAllFilterButtons()
    {
        lookUnSelected(allButton);
        lookUnSelected(circleButton);
        lookUnSelected(rectangleButton);
        lookUnSelected(octagonButton);
        lookUnSelected(triangleButton);
        lookUnSelected(squareButton);
    }

    @Override
    public void onItemClick(int position)
    {
        /*
        Intent detailIntent=new Intent(this,DetailActivity.class);
        detailIntent.putExtra("position",position);
        startActivity(detailIntent);

         */
    }






    //Searchview method
    private void initSearchWidgets()
    {
        searchView=(SearchView) findViewById(R.id.shapeListSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText=s;
                ArrayList<Shape>filteredShapes=new ArrayList<Shape>();

                for(Shape shape: shapeList)
                {
                    if(shape.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilters.contains("all"))
                        {
                            filteredShapes.add(shape);
                        }
                        else
                        {
                            for(String filter: selectedFilters) {
                                if (shape.getName().toLowerCase().contains(filter)) {
                                    filteredShapes.add(shape);
                                }
                            }
                        }
                    }
                }
                completeSearch(filteredShapes);
                return false;

            }
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
        });
    }
    private void completeSearch(ArrayList<Shape> filteredShapes)
    {
        ShapeAdapter adapter=new ShapeAdapter(filteredShapes,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



    private void setUpData()
    {
        Shape circle = new Shape("0", "Circle", R.drawable.circle);
        shapeList.add(circle);
        Shape triangle = new Shape("1","Triangle", R.drawable.triangle);
        shapeList.add(triangle);
        Shape square = new Shape("2","Square", R.drawable.square);
        shapeList.add(square);
        Shape rectangle = new Shape("3","Rectangle", R.drawable.rectangle);
        shapeList.add(rectangle);
        Shape octagon = new Shape("4","Octagon", R.drawable.octagon);
        shapeList.add(octagon);
        Shape circle2 = new Shape("5", "Circle 2", R.drawable.circle);
        shapeList.add(circle2);
        Shape triangle2 = new Shape("6","Triangle 2", R.drawable.triangle);
        shapeList.add(triangle2);
        Shape square2 = new Shape("7","Square 2", R.drawable.square);
        shapeList.add(square2);
        Shape rectangle2 = new Shape("8","Rectangle 2", R.drawable.rectangle);
        shapeList.add(rectangle2);
        Shape octagon2 = new Shape("9","Octagon 2", R.drawable.octagon);
        shapeList.add(octagon2);
    }
    private void setUpList ()
    {
        recyclerView=(RecyclerView)findViewById(R.id.shapesRecycleView);
        ShapeAdapter adapter=new ShapeAdapter(shapeList,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    //SHOW THE ITEMS ACCORDING TO FILTER Button name

    private void filterList(String status)
    {
        if(selectedFilters.contains(status))
        {
            selectedFilters.remove(status);
        }
        else if(status!= null)
            selectedFilters.add(status);

        ArrayList< Shape>filteredShapes=new ArrayList<Shape>();
        for(Shape shape: shapeList) {
            for (String filter : selectedFilters)
            {
                if (shape.getName().toLowerCase().contains(filter))
                {
                    if (currentSearchText == "")
                    {
                        filteredShapes.add(shape);
                    }
                    else
                    {
                        if (shape.getName().toLowerCase().contains(currentSearchText.toLowerCase()))
                        {
                            filteredShapes.add(shape);
                        }
                    }
                }
            }

        }
        ShapeAdapter adapter=new ShapeAdapter(filteredShapes,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



    //all buttons
    public void allFilterTapped(View view)
    {
        searchView.setQuery("",false);
         searchView.clearFocus();
        selectedFilters.clear();
        selectedFilters.add("all");

        unselectAllFilterButtons();

        lookSelected(allButton);

        ShapeAdapter adapter=new ShapeAdapter(shapeList,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void selectFilterUI(Button Button) {
        lookSelected(Button);
        lookUnSelected(allButton);
        selectedFilters.remove("all");
    }

    public void triangleFilterTapped(View view)
    {

        filterList("triangle");

        if(selectedFilters.contains("triangle"))
            selectFilterUI(triangleButton);
        else
            lookUnSelected(triangleButton);

    }
    public void squareFilterTapped(View view)
    {
        filterList("square");

        if(selectedFilters.contains("square"))
            selectFilterUI(squareButton);
        else
            lookUnSelected(squareButton);
    }

    public void octagonFilterTapped(View view)
    {
        filterList("octagon");
        if(selectedFilters.contains("octagon"))
            selectFilterUI(octagonButton);
        else
            lookUnSelected(octagonButton);
    }

    public void rectangleFilterTapped(View view)
    {
        filterList("rectangle");
        if(selectedFilters.contains("rectangle"))
            selectFilterUI(rectangleButton);
        else
            lookUnSelected(rectangleButton);
    }

    public void circleFilterTapped(View view)
    {
        filterList("circle");
        if(selectedFilters.contains("circle"))
            selectFilterUI(circleButton);
        else
            lookUnSelected(circleButton);
    }



}