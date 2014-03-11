package net.numa08.android_ffmpeg_tutorial_01.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.util.List;


public class MainActivity extends Activity implements DumpFrameTask.DumFrameTaskLisner{
    public static final String TARGET_MOVIE_FILE = "1.mp4";
    public static final String FRAME_DUMP_DIR = "android-ffmpeg-tutorial-01";
    public static final int MAX_FRAME_OF_NUM = 20;

    private Dialog mProgressDialog;
    private DumpFrameTask mDumpFrameTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final File dumpDir = new File(movieFileDir());
        if (!dumpDir.exists()) {
            dumpDir.mkdir();
        }
        final AssetFileManager assets = new AssetFileManager(getApplicationContext());
        final boolean success = assets.copy(MainActivity.TARGET_MOVIE_FILE, dumpDir.getPath());
        if (!success) {
            Log.e("FFMpeg Tutorial", "cannot copy target file");
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
        final File dumDir = new File(getFilesDir(), "dump");
        if (dumDir.exists()) {
            dumDir.delete();
        }
        dumDir.mkdir();
        final String dumedDirPath = dumDir.getPath();
        final String frameVideoPath = movieFileDir() + File.separator + MainActivity.TARGET_MOVIE_FILE;
        final FrameDumper dumper = new FrameDumper(dumedDirPath, frameVideoPath, numOfFrame);
        mDumpFrameTask = new DumpFrameTask(dumper);
//        mDumpFrameTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDumpFrameTask != null) {
            mDumpFrameTask.cancel(false);
        }
    }

    @Override
    public void onPreExecute() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = ProgressDialog.show(this, "Dum Frame", "Please wait", false);
    }

    @Override
    public void onPostExecute(FrameDumper dumper) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        final List<String> dumedFiles = dumper.dumpedFilePathes();
        final PagerAdapter adapter = new VideoFrameAdapter(dumedFiles);
        viewPager.setAdapter(adapter);
    }

    private String movieFileDir() {
        return new StringBuffer(getFilesDir().getPath())
                   .append(File.separator)
                   .append(MainActivity.FRAME_DUMP_DIR).toString();
    }
}