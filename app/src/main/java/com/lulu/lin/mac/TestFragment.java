package com.lulu.lin.mac;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("abcd","onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("abcd","onCreateView");
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        ((TextView)view.findViewById(R.id.view)).setText(mParam1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("abcd","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("abcd","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("abcd","onResume");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("abcd",""+isVisibleToUser);
        Log.d("abcd","get"+getUserVisibleHint());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("abcd","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("abcd","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("abcd","onDestroyView");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("abcd","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("abcd","onDetach");
    }
}
