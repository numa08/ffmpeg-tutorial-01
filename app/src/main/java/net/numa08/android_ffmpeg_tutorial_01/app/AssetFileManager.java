package net.numa08.android_ffmpeg_tutorial_01.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetFileManager {

    private final Context mContext;

    public AssetFileManager(Context mContext) {
        this.mContext = mContext;
    }

    public boolean copy(String fromPath, String toPath) {
        final AssetManager assets = mContext.getAssets();
        InputStream in = null;
        OutputStream out = null;

        try {
            in = assets.open(fromPath);
            final File outFIle = new File(toPath, fromPath);
            out = new FileOutputStream(outFIle);
            copyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FFMpeg-Tutorial","failed copy file from assets");
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
        }
        return true;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024 * 16];
        int read = 0;
        while (read != -1) {
            read = in.read(buffer);
            out.write(buffer, 0, read);
        }
    }
}
