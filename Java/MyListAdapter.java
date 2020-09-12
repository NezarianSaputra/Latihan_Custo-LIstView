package com.example.latihan_listview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<Hero> {
    List<Hero> heroList;
    Context context;
    int resource;

    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    public View getView(final int position, View concertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        ImageView imgView = view.findViewById(R.id.imgView);
        TextView txtViewName = view.findViewById(R.id.txtViewName);
        TextView txtViewTeam = view.findViewById(R.id.txtViewTeam);
        Button btnDelete = view.findViewById(R.id.btnDelete);

        Hero hero = heroList.get(position);

        imgView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        txtViewName.setText(hero.getName());
        txtViewTeam.setText(hero.getTeam());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeHero(position);
            }
        });
        return view;
    }

    private void removeHero(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Kmu yakin ingin menghapus ini ?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                heroList.remove(position);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
