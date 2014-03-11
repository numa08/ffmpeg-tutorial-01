package net.numa08.android_ffmpeg_tutorial_01.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.apache.commons.io.IOUtils;

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
            IOUtils.copy(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("FFMpeg-Tutorial","failed copy file from assets");
            return false;
        }
        return true;
    }
}
