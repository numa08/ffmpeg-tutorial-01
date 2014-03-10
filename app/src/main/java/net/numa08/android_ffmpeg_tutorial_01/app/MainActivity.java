package net.numa08.android_ffmpeg_tutorial_01.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;


public class MainActivity extends Activity {
    public static final String FRAME_DUMP_DIR = "android-ffmpeg-tutorial-01";
    public static final int MAX_FRAME_OF_NUM = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final File dumpDir = new File(getFilesDir().getPath() + File.separator + MainActivity.FRAME_DUMP_DIR);
        if (!dumpDir.exists()) {
            dumpDir.mkdir();
        }
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

        if (numOfFrame > MainActivity.MAX_FRAME_OF_NUM) {
            numOfFrame = MainActivity.MAX_FRAME_OF_NUM;
        }
    }
}