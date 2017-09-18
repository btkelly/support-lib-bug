package root.example.com.fragmenttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Random;

/**
 * Created by bobbake4 on 15/09/17.
 */

public class ClickFragment extends Fragment {

    int color;
    Random rnd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new View(getContext());
        view.setBackgroundColor(color);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(Integer.toString(color))
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(getId(), new ClickFragment())
                        .commit();
            }
        });

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(500, 500);
        params.topMargin = rnd.nextInt(500);
        params.leftMargin = rnd.nextInt(500);
        params.rightMargin = rnd.nextInt(500);
        params.bottomMargin = rnd.nextInt(500);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(view, params);

        return frameLayout;
    }
}
