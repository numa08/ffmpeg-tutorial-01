package net.numa08.android_ffmpeg_tutorial_01.app;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class VideoFrameAdapter extends PagerAdapter{

    private final List<String> mFrameImageFilePathes;

    public VideoFrameAdapter(List<String> frameImageFilePathes) {
        this.mFrameImageFilePathes = frameImageFilePathes;
    }

    @Override
    public int getCount() {
        return mFrameImageFilePathes.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageDrawable(Drawable.createFromPath(mFrameImageFilePathes.get(position)));
        ((ViewPager)container).addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
