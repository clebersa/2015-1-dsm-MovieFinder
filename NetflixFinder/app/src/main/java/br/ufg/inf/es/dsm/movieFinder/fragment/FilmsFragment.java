package br.ufg.inf.es.dsm.movieFinder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufg.inf.es.dsm.movieFinder.R;

/**
 * Created by cleber on 02/06/15.
 */
public class FilmsFragment extends Fragment {
    public FilmsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_films, container, false);

        //Connect list view

        return rootView;
    }
}
