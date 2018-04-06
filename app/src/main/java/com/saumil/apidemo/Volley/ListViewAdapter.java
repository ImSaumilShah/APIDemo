package com.saumil.apidemo.Volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saumil.apidemo.R;

import java.util.List;

/**
 * Created by Saumil on 1/8/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Hero> {

    private List<Hero> heroList;
    private Context context;

    public ListViewAdapter(List<Hero> heroList,Context context){
        super(context, R.layout.list_item,heroList);
        this.heroList=heroList;
        this.context=context;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        LayoutInflater inflater = LayoutInflater.from(context);

        View listviewitem = inflater.inflate(R.layout.list_item,null,true);

        TextView textViewName = listviewitem.findViewById(R.id.textViewName);
        TextView textViewImageUrl = listviewitem.findViewById(R.id.textViewImageUrl);

        Hero hero = heroList.get(position);

        textViewName.setText(hero.getName());
        textViewImageUrl.setText(hero.getImageUrl());

        return listviewitem;
    }
}
