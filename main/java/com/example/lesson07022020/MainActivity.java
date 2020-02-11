package com.example.lesson07022020;


import android.os.Bundle;

import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd, btnSort, btnSearch, btnAddlist, btnupdlist, btndelllist;
    EditText inputsize;
    TextView outputarray;
    Integer[] array;
    ListView listView;
    int N = 0;
    LinkedList<String> myarraylist = new LinkedList();
    final String[] catNamesArray = new String[]{"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Бобик",
            "Кристина", "Пушок", "Дымка", "Кузя", "Китти",
            "Барбос", "Масяня", "Симба"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnupdlist = findViewById(R.id.btnupdlist);
        btndelllist = findViewById(R.id.btndelllist);
        btnAdd = findViewById(R.id.btnadd);
        btnSort = findViewById(R.id.btnsort);
        btnSearch = findViewById(R.id.btnsearch);
        btnAddlist = findViewById(R.id.btnaddlist);
        inputsize = findViewById(R.id.inputsize);
        outputarray = findViewById(R.id.outputarray);
        btnAdd.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnAddlist.setOnClickListener(this);
        btnupdlist.setOnClickListener(this);
        btndelllist.setOnClickListener(this);


        myarraylist.addAll(Arrays.asList(catNamesArray));

        listView = findViewById(R.id.listview);


        uPdate(myarraylist);
    }

    private void uPdate(LinkedList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnadd: {
                try {
                    N = Integer.parseInt(inputsize.getText().toString());
                    array = new Integer[N];
                    inputsize.setText("");
                    InputArray(array);
                    outputarray.setText(Arrays.toString(array));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Неверный размер массива", Toast.LENGTH_SHORT).show();
                }

            }
            break;
            case R.id.btnsort: {
                try {
                    Arrays.sort(array, new LastDigit());
                    outputarray.append("\n==Sort==\n" + Arrays.toString(array));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Массив не задан", Toast.LENGTH_SHORT).show();
                }

            }
            break;
            case R.id.btnsearch: {
                try {
                    int k = Integer.parseInt(inputsize.getText().toString());
                    inputsize.setText("");
                    int l = Arrays.binarySearch(array, k);
                    if (l >= 0) {
                        Toast.makeText(getApplicationContext(),
                                "Индекс элемента " + k + " равен " + l,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Такого элемента нет",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Введите число для поиска",
                            Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.btnaddlist: {
                try {
                    String[] s = inputsize.getText().toString().split(" ");
                    inputsize.setText("");

                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Заполните поле",
                                Toast.LENGTH_SHORT).show();
                    } else if (!myarraylist.contains(s[0])) {
                        if (Integer.parseInt(s[1]) == 1)
                            myarraylist.add(s[0]);
                        else
                            myarraylist.addFirst(s[0]);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Такой элемент существует",
                                Toast.LENGTH_SHORT).show();
                    }

                    //   outputarray.setText(myarraylist.toString());
                    uPdate(myarraylist);
                } catch (Exception e) {

                }
            }
            break;
            case R.id.btnupdlist: {
                try {
                    String[] s = inputsize.getText().toString().split(" ");
                    inputsize.setText("");

                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Заполните поле",
                                Toast.LENGTH_SHORT).show();
                    } else

                        while (myarraylist.contains(s[0])) {
                            myarraylist.set(myarraylist.indexOf(s[0]), s[1]);
                        }


//                    outputarray.setText(myarraylist.toString());
                    uPdate(myarraylist);
                } catch (Exception e) {

                }
            }
            break;
            case R.id.btndelllist: {
                try {
                    String s = inputsize.getText().toString();
                    inputsize.setText("");

                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Заполните поле",
                                Toast.LENGTH_SHORT).show();
                    } else if (myarraylist.remove(s)) {
                        Toast.makeText(getApplicationContext(),
                                "Такой объект удалён",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Такого элемента не существует",
                                Toast.LENGTH_SHORT).show();
                    }

//                    outputarray.setText(myarraylist.toString());
                    uPdate(myarraylist);
                } catch (Exception e) {

                }
            }
            break;

        }
    }

    private void InputArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
    }

    class LastDigit implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {


            if (o1 % 10 > o2 % 10)
                return 1;
            else if (o1 % 10 < o2 % 10)
                return -1;
            else
                return 0;
        }
    }
}
