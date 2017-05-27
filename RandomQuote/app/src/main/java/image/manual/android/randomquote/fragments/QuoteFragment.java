package image.manual.android.randomquote.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import image.manual.android.randomquote.R;
import image.manual.android.randomquote.networks.ImageLoader;
import image.manual.android.randomquote.networks.QuoteLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {
    private ImageView ivBackground;
    private TextView tvQuote;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ivBackground = (ImageView) view.findViewById(R.id.imv_background);
        tvQuote = (TextView) view.findViewById(R.id.tv_quote);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupUI();
    }

    private void setupUI() {
        (new ImageLoader(this.getActivity())).setIvBackground(ivBackground).loadImage();
        (new QuoteLoader(tvQuote)).execute();
    }
}
