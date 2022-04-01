package com.example.worldcinema.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.R;
import com.example.worldcinema.UI.Collections.CollectionCreate;
import com.example.worldcinema.UI.Collections.ItemIcons;

import java.util.ArrayList;
import java.util.List;

public class IconAdapter extends ArrayAdapter<ItemIcons> {

    private List<ItemIcons> iconsList = new ArrayList<>();
    private int customLayoutId;
    private Context context;

    private LinearLayout linearLayout;
    public IconAdapter(@NonNull Context context, int resource, @NonNull List<ItemIcons> _iconsList){
        super(context, resource, _iconsList);
        this.context = context;
        iconsList = _iconsList;
        customLayoutId = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(customLayoutId, null);
        }

        ImageView imageView = v.findViewById(R.id.img_icon);
        ItemIcons iconItem  =  iconsList.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CollectionCreate.class);
                intent.putExtra("icon", iconItem.getIcon_id());
                context.startActivity(intent);
            }
        });
        imageView.setImageResource(iconItem.getIcon_id());

        return v;
    }
}
