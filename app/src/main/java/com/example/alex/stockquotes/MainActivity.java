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

public class MainActivity extends AppCompatActivity {
    private String symbol, name, lastprice, lasttime, change, range;
    private TextView symcont = this.findViewById(R.id.symbolcont), namecont = this.findViewById(R.id.namecont),
            lastpricecont = this.findViewById(R.id.lastpricecont),lasttimecont = this.findViewById(R.id.lastpricecont),
            changecont = this.findViewById(R.id.changecont), rangecont = this.findViewById(R.id.rangecont);
    private EditText prompt = this.findViewById(R.id.stock_search);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prompt.setOnKeyListener(mKeyListener);
    }


        private OnKeyListener mKeyListener = new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                getStockJson gsj = new getStockJson();
                gsj.execute(prompt.getText().toString());

                return false;
            }

        };

    private class getStockJson extends AsyncTask<String,Integer,Stock>{
        @Override
        protected Stock doInBackground(String... symbol){
            Stock stock = new Stock(symbol[0]);
            try {
                stock.load();
                symcont.setText(stock.getSymbol());
                namecont.setText(stock.getName());
                lastpricecont.setText(stock.getLastTradePrice());
                lasttimecont.setText(stock.getLastTradeTime());
                changecont.setText(stock.getChange());
                rangecont.setText(stock.getRange());
            }catch(Exception e){
                prompt.setText("Error Retrieving Symbol");
                Log.i("Error", e.getMessage());
            }
            symcont.setText(stock.getSymbol());
            symcont.setText(stock.getSymbol());
            return stock;

        }

    }

    }

