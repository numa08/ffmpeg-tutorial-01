package net.numa08.android_ffmpeg_tutorial_01.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStartButton(View view) {
        final EditText numberEditText = (EditText)findViewById(R.id.editText);
        int numOfFrame;
        try {
            final String numOfText = numberEditText.getText().toString();
            numOfFrame = Integer.valueOf(numOfText);
        } catch (NumberFormatException e){
            e.printStackTrace();
            numOfFrame = 5;
        }
    }
}