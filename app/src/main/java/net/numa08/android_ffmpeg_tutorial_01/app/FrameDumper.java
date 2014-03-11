package net.numa08.android_ffmpeg_tutorial_01.app;

import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrameDumper {

    private final String mDupeddirPath;
    private final String mFrameMoviePath;
    private final int mNumOfFrame;

    public FrameDumper(String mDupeddirPath, String mFrameMoviePath, int mNumOfFrame) {
        this.mDupeddirPath = mDupeddirPath;
        this.mFrameMoviePath = mFrameMoviePath;
        this.mNumOfFrame = mNumOfFrame;
    }

    public void dump() {
        dump(mFrameMoviePath, mNumOfFrame);
    }

    public List<String> dumpedFilePathes() {
        final List<String> filePathes = new ArrayList<>();
        final File[] files = new File(mDupeddirPath).listFiles();
        for (File file : files) {
            if (file.getAbsolutePath().endsWith(".jpg")) {
                filePathes.add(file.getPath());
            }
        }

        return filePathes;
    }

    private void safeFrameToPath(Bitmap bitmap, String path) {
        final int bufferSize = 1024 * 8;
        final File file = new File(path);
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream, bufferSize);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream );
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private native void dump(String frameMoviePath, int numOfFrame);
}
