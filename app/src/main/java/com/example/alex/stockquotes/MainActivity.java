package com.example.alex.stockquotes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnKeyListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String sym, name, lastprice, lasttime, change, range;
    private TextView symcont, namecont, lastpricecont ,lasttimecont, changecont, rangecont;
    private EditText prompt;

    @Override
    public void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);
        b.putString("symString", sym);
        b.putString("nameString", name);
        b.putString("priceString", lastprice);
        b.putString("timeString", lasttime);
        b.putString("changeString", change);
        b.putString("rangeString", range);
        b.putString("inputString", prompt.getText().toString());
    }

    public void onRestoreInstanceState(Bundle b){
        if(b != null){
            super.onRestoreInstanceState(b);

            symcont.setText(b.getString("symString"));
            namecont.setText(b.getString("nameString"));
            lastpricecont.setText(b.getString("priceString"));
            lasttimecont.setText(b.getString("timeString"));
            changecont.setText(b.getString("changeString"));
            rangecont.setText(b.getString("rangeString"));
            prompt.setText(b.getString("inputString"));
            sym = b.getString("symString");
            name =b.getString("nameString");
            lastprice = b.getString("priceString");
            lasttime = b.getString("timeString");
            change = b.getString("changeString");
            range =b.getString("rangeString") ;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prompt = this.findViewById(R.id.stock_search);
        prompt.setOnKeyListener(mKeyListener);

        symcont = this.findViewById(R.id.symbolcont);
        namecont = this.findViewById(R.id.namecont);
        lastpricecont = this.findViewById(R.id.lastpricecont);
        lasttimecont = this.findViewById(R.id.lasttimecont);
        changecont = this.findViewById(R.id.changecont);
        rangecont = this.findViewById(R.id.rangecont);

    }


        private OnKeyListener mKeyListener = new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Stock stock = new Stock("null");

                getStockJson gsj = new getStockJson();
                gsj.execute(prompt.getText().toString());
                try {
                    stock = gsj.get();
                    if(stock == null || stock.getSymbol() == "null"){
                        Toast.makeText(getApplicationContext(), "Error gathering data", Toast.LENGTH_SHORT).show();
                        return false;
                    }else {
                        symcont.setText(sym);
                        namecont.setText(name);
                        lastpricecont.setText(lastprice);
                        lasttimecont.setText(lasttime);
                        changecont.setText(change);
                        rangecont.setText(range);
                    }
                }catch (Exception e){
                    Log.i("Error", e.getMessage());
                }
                return false;
            }

        };

    private class getStockJson extends AsyncTask<String,Integer,Stock>{
        @Override
        protected Stock doInBackground(String... symbol){
            Stock stock = new Stock(symbol[0]);
            try {
                stock.load();
                sym = stock.getSymbol();
                name = stock.getName();
                lastprice = stock.getLastTradePrice();
                lasttime = stock.getLastTradeTime();
                change = stock.getChange();
                range = stock.getRange();
            }catch(Exception e){
                Log.i("Error", e.getMessage());
                return null;

            }
            return stock;

        }

    }

    }

