//package edu.ccny.cs.kyaw.pillverifier.adapters;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.github.snowdream.android.widget.SmartImageView;
//
//import java.util.ArrayList;
//
//import edu.ccny.cs.kyaw.pillverifier.R;
//import edu.ccny.cs.kyaw.pillverifier.model.ModelDrugGridView;
//
///**
// * Created by kyawthan on 12/8/14.
// */
//public class AdapterMealListView {
//    private final Activity context;
//    ArrayList<ModelDrugGridView> arrayListMeal;
//
//    static class ViewHolder {
//        static public TextView name;
//
//        SmartImageView imageView;
//    }
//
//    public void setArrayListMeal(ArrayList<ModelDrugGridView> arrayListMeal) {
//        this.arrayListMeal = arrayListMeal;
//        notifyDataSetChanged();
//    }
//
//    public AdapterMealListView(Activity context) {
//        this.context = context;
//        arrayListMeal = new ArrayList<ModelDrugGridView>();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            LayoutInflater inflater = context.getLayoutInflater();
//            convertView = inflater.inflate(R.layout.grid_item, null);
//            viewHolder = new ViewHolder();
//            viewHolder.name = (TextView) convertView.findViewById(R.id.textViewRxString);
//
//            viewHolder.imageView = (SmartImageView) convertView.findViewById(R.id.imageViewDrug);
//
//            convertView.setTag(viewHolder);
//
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.name.setText(arrayListMeal.get(position).name+"\n");
//        viewHolder.createAt.setText(arrayListMeal.get(position).created_at+"\n");
//        viewHolder.imageView.setImageUrl(arrayListMeal.get(position).imageUrl, null);
//        return convertView;
//    }
//
//    @Override
//    public int getCount() {
//        return arrayListMeal.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//}
