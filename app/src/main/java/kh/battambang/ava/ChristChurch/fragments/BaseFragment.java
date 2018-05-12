package kh.battambang.ava.ChristChurch.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import kh.battambang.ava.ChristChurch.MainActivity;

/**
 * Created by PHIE on 5/12/2018.
 */

public class BaseFragment extends Fragment {
    MainActivity mainActivity;
    @Override
    public void onAttach(Context context) {
        if(mainActivity == null && context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }
        super.onAttach(context);
    }
}
