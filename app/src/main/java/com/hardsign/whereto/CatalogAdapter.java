package com.hardsign.whereto;

/**
 * Created by User on 08.11.2015.
 */
        import java.util.ArrayList;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;


public class CatalogAdapter extends BaseAdapter{
    Context cont;
    LayoutInflater lInflater;
    ArrayList<ListData> objects;

    CatalogAdapter(Context context, ArrayList<ListData> mylist){
        cont= context;
        objects= mylist;
        lInflater=(LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return objects.size();
    }

    public Object getItem(int position){
        return objects.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
// используем созданные, но не используемые view
        View view= convertView;
        if(view==null){
//получаем LayoutInflater для работы с layout-ресурсами
            view= lInflater.inflate(R.layout.my_item, parent,false);
        }

        ListData p=((ListData) getItem(position));

// заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.textView1)).setText(p.name);
        ((TextView) view.findViewById(R.id.textView2)).setText("Цена: "+p.price+" рублей");
        ((ImageView) view.findViewById(R.id.imageView1)).setImageResource(p.image);
        return view;
    }
}

