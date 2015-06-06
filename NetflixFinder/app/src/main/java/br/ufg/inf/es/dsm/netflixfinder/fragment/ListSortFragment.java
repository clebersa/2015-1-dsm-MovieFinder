package br.ufg.inf.es.dsm.netflixfinder.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ListView;

import br.ufg.inf.es.dsm.netflixfinder.R;
import br.ufg.inf.es.dsm.netflixfinder.activity.HomeActivity;
import br.ufg.inf.es.dsm.netflixfinder.activity.SortMethod;

public class ListSortFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Order by")
                .setPositiveButton("SORT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ListView listView = ((AlertDialog) dialog).getListView();
                        int selectedIndex = listView.getCheckedItemPosition();
                        Log.d("SORT", "Option " + selectedIndex + " selected!");
                        SortMethod sortMethod;
                        switch(selectedIndex){
                            case 0:
                                sortMethod = SortMethod.NAME;
                                break;
                            case 1:
                                sortMethod = SortMethod.NAME_INVERSE;
                                break;
                            case 2:
                                sortMethod = SortMethod.YEAR;
                                break;
                            case 3:
                                sortMethod = SortMethod.YEAR_INVERSE;
                                break;
                            case 4:
                                sortMethod = SortMethod.RATING;
                                break;
                            default:
                                sortMethod = SortMethod.NONE;
                        }

                        SharedPreferences preferences = getActivity().getSharedPreferences(
                                getString(R.string.sortMode), 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(getString(R.string.sortMode), sortMethod.toString());
                        editor.commit();

                        try{
                            ((HomeActivity) getActivity()).updateMovieListOrder();
                        }catch(ClassCastException ex) {
                            Log.e("SORT", "Unable to request sorting. Error: " + ex.getMessage());
                        }

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("SORT", "Option canceled!");
                    }
                });

        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.sortMode), 0);
        String sortMode = preferences.getString(getString(R.string.sortMode), "");
        int preSelected;
        if(SortMethod.NAME.toString().equals(sortMode)){
            preSelected = 0;
        }else if(SortMethod.NAME_INVERSE.toString().equals(sortMode)){
            preSelected = 1;
        }else if(SortMethod.YEAR.toString().equals(sortMode)){
            preSelected = 2;
        }else if(SortMethod.YEAR_INVERSE.toString().equals(sortMode)){
            preSelected = 3;
        }else if(SortMethod.RATING.toString().equals(sortMode)){
            preSelected = 4;
        }else{
            preSelected = 5;
        }

        builder.setSingleChoiceItems(R.array.sortModes, preSelected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("SORT", "Option checked: " + which);
            }
        });

        return builder.create();
    }

}
