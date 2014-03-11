package net.numa08.android_ffmpeg_tutorial_01.app;

import android.os.AsyncTask;

public class DumpFrameTask extends AsyncTask<Void, Integer, Void>{

    public static interface DumFrameTaskLisner {
        public void onPreExecute();
        public void onPostExecute(FrameDumper dumper);
    }

    private DumFrameTaskLisner mListener;

    private final FrameDumper mFrameDumper;

    public DumpFrameTask(FrameDumper mFrameDumper) {
        this.mFrameDumper = mFrameDumper;
    }

    public void setListner(final DumFrameTaskLisner listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        if (mListener != null) {
            mListener.onPreExecute();
        }
    }

    @Override
    protected void onCancelled() {
        mListener = null;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mFrameDumper.dump();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mListener != null) {
            mListener.onPostExecute(mFrameDumper);
        }
    }
}
