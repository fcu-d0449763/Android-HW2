package com.feifei.hw0321;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    private Button btnEnter; //宣告Button
    private EditText userinput; //宣告EditText
    private TextView result;
    int[] flog = new int[4];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result = (TextView) findViewById(R.id.result);
        userinput = (EditText) findViewById(R.id.answer);
        btnEnter = (Button) findViewById(R.id.button);
        btnEnter.setOnClickListener(calcResult);
        GenerateAnswer();
        /*btnEnter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //取得EditText的輸入內容
                String content = userinput.getText().toString();
                //顯示在Debug Console
                Log.d("debug", "button click");
                //使用Toast顯示在螢幕上
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private View.OnClickListener calcResult = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String input_str =userinput.getText().toString();
            userinput.setText("");

            if (input_str.length() != 4) {
                Toast.makeText(MainActivity.this,
                        R.string.input_error,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            result.setText(Compare(input_str));
        }
    };

    private void GenerateAnswer() {
        for (int i = 0; i < 4; i++) {
            boolean breakflag = true;
            do {
                breakflag = true;
                flog[i] = (int)(Math.random()*10);
                for (int j = 0; j < i; j++) {
                    if (flog[i] == flog[j]) {
                        breakflag = false;
                        break;
                    }
                }
            } while (!breakflag);
        }
    }

    private String Compare(String input_str) {
        String result = new String();
        int guess = Integer.parseInt(input_str);
        int [] guessarray = new int[4];
        guessarray[0] = guess/1000;
        guessarray[1] = (guess%1000)/100;
        guessarray[2] = (guess%100)/10;
        guessarray[3] = (guess%10);

        int counta = 0, countb = 0;
        for (int i = 0; i < 4; i++) {
            if (guessarray[i] == flog[i])
                counta++;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                if (guessarray[i] == flog[j])
                    countb++;
            }
        }
        result = (String)getText(R.string.result);
        result = result + counta + "A" + countb + "B";
        return result;
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


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


}
