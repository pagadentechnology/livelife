package id.tech.rcslive.fragment;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import id.tech.rcslive.util.*;
import id.tech.rcslive.activity.R;

public class WalkthroughFragmentActivity extends Fragment{

    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static WalkthroughFragmentActivity create(int pageNumber){
        WalkthroughFragmentActivity fragment = new WalkthroughFragmentActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public WalkthroughFragmentActivity(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        switch (getPageNumber()){
            case 0:
                ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.item_vp_imageview, container, false);
                KenBurnsView img = (KenBurnsView)rootView.findViewById(R.id.img);
                TextView textView = (TextView)rootView.findViewById(R.id.tv);

                textView.setTypeface(Font.setFontGaramond(getActivity()));
                img.setImageResource(R.drawable.img_vp_01);
                textView.setText("Temukan Aktifitas Di Grup Anda");
                return rootView;
            case 1:
                ViewGroup rootView2 = (ViewGroup)inflater.inflate(R.layout.item_vp_imageview_2, container, false);
                KenBurnsView img2 = (KenBurnsView)rootView2.findViewById(R.id.img);
                TextView textView2 = (TextView)rootView2.findViewById(R.id.tv);

                textView2.setTypeface(Font.setFontGaramond(getActivity()));
                img2.setImageResource(R.drawable.img_vp_02);
                textView2.setText("Tingkatkan Kebersamaan Di Grup Anda");
                return rootView2;
            case 2:
                ViewGroup rootView3 = (ViewGroup)inflater.inflate(R.layout.item_vp_imageview_3, container, false);
                KenBurnsView img3 = (KenBurnsView)rootView3.findViewById(R.id.img);
                TextView textView3 = (TextView)rootView3.findViewById(R.id.tv);

                textView3.setTypeface(Font.setFontGaramond(getActivity()));
                img3.setImageResource(R.drawable.img_vp_03);
                textView3.setText("Ramaikan Komunitas Anda");
                return rootView3;
        }

        return null;
    }

    public int getPageNumber(){
        return mPageNumber;
    }
}
