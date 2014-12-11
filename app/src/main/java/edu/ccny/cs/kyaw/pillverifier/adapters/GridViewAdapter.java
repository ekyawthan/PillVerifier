package edu.ccny.cs.kyaw.pillverifier.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;


import com.loopj.android.image.SmartImageView;


import java.util.ArrayList;

import edu.ccny.cs.kyaw.pillverifier.R;
import edu.ccny.cs.kyaw.pillverifier.model.ModelDrugGridView;

/**
 * Created by kyawthan on 12/7/14.
 */
public class GridViewAdapter extends BaseAdapter {

    private String TAG = getClass().getSimpleName().toUpperCase();

    private final Activity context;
    ArrayList<ModelDrugGridView> viewArrayList;

    public GridViewAdapter(Activity context){
        this.context = context;
        this.viewArrayList = new ArrayList<>();
    }

    public void setViewArrayList(ArrayList<ModelDrugGridView> list){

        this.viewArrayList = list;
        notifyDataSetChanged();
    }

//    public static class  ViewHolder {
//        SmartImageView imageView;
//        TextView textViewRx;
//    }
    @Override
    public int getCount() {
        return viewArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textViewRx = (TextView) convertView.findViewById(R.id.textViewRxString);
            viewHolder.imageView = (SmartImageView) convertView.findViewById(R.id.imageViewDrug);
            convertView.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) convertView.getTag();
        Log.e(TAG, viewArrayList.get(position).imageUrl);

        viewHolder.textViewRx.setText(viewArrayList.get(position).rxString);
        viewHolder.imageView.setImageUrl(viewArrayList.get(position).imageUrl);


        return convertView;
    }
}
