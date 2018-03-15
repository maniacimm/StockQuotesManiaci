package com.example.maddiemaniaci.stockquotes_maniaci;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.userInput);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                Stock stock = new Stock(text);
                new StockAsyncTask().execute(stock);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public class StockAsyncTask extends AsyncTask<Stock, Void, Stock> {
        @Override
        protected Stock doInBackground(Stock... stocks) {
            Stock stock = stocks[0];
            try {
                stock.load();
            } catch (Exception e) {
                Log.i("BOOTY", e.getMessage());
            }
            return stock;
        }

        @Override
        protected void onPostExecute(Stock stock) {
            TextView symbol = findViewById(R.id.symbolTextView);
            symbol.setText(stock.getSymbol());
            TextView name = findViewById(R.id.NameTextView);
            name.setText(stock.getName());
            TextView lastPrice = findViewById(R.id.Last_Trade_Price_TextView);
            lastPrice.setText(stock.getLastTradePrice());
            TextView lastTime = findViewById(R.id.LastTradeTime_TextView);
            lastTime.setText(stock.getLastTradeTime());
            TextView change = findViewById(R.id.ChangeTextView);
            change.setText(stock.getChange());
            TextView range = findViewById(R.id.WeekRageTextView);
            range.setText(stock.getRange());
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
