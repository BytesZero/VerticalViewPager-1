package io.github.wr1241.verticalviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VerticalViewPager vvp = new VerticalViewPager(getApplicationContext());
        vvp.setId(0x00001111);
        setContentView(vvp);

        vvp.setAdapter(new TempAdapter(getSupportFragmentManager()));
    }


    private final class TempAdapter extends FragmentPagerAdapter {

        private Fragment[] mFragments;

        public TempAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new Fragment[]{
                    ColorFragment.newInstance(0xFFFF0000),
                    ColorFragment.newInstance(0xFF00FF00),
                    ColorFragment.newInstance(0xFF0000FF),
                    ColorFragment.newInstance(0xFFFFFF00)};
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

    public static class ColorFragment extends Fragment {

        private int mColor;

        private static final String EXTRA_COLOR = "extra_color";

        public static ColorFragment newInstance(int color) {
            Bundle data = new Bundle();
            data.putInt(EXTRA_COLOR, color);
            ColorFragment fragment = new ColorFragment();
            fragment.setArguments(data);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mColor = getArguments().getInt(EXTRA_COLOR);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            FrameLayout layout = new FrameLayout(getActivity());
            layout.setBackgroundColor(mColor);
            return layout;
        }
    }


}
