package demo.frap.favourites.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import demo.frap.favourites.R;
import demo.frap.favourites.models.DataModelfav;

/**
 * Created by Ramandeep on 8/12/17.
 */

public class RecyclerViewAdapterFav extends RecyclerView.Adapter<RecyclerViewAdapterFav.ViewHolder>  {
    Context context;
    List<DataModelfav> dataModelfavs;





    public RecyclerViewAdapterFav(List<DataModelfav> getDataAdapter, Context context){

        super();

        this.dataModelfavs = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        RecyclerViewAdapterFav.ViewHolder viewHolder = new RecyclerViewAdapterFav.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        DataModelfav dataModelfav1 =  dataModelfavs.get(position);
        long value = Long.parseLong(dataModelfav1.getViewCount());

        holder.type.setText(dataModelfav1.getType());
        holder.viewCount.setText(formatNumberExample(value));
        Picasso.with(context).load(dataModelfav1.getImage_url()).into(holder.profile_Image);
        holder.title.setText(dataModelfav1.getTitle());
        holder.description.setText(dataModelfav1.getDescription());

    }



    @Override
    public int getItemCount() {

        return dataModelfavs.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public com.mikhaellopez.circularimageview.CircularImageView profile_Image;
        public TextView viewCount;
        public TextView type;
        public Button select;



        public ViewHolder(final View itemView) {

            super(itemView);



            title = (TextView) itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);
            profile_Image=(com.mikhaellopez.circularimageview.CircularImageView) itemView.findViewById(R.id.profile_image);
            viewCount=(TextView)itemView.findViewById(R.id.view_count);
            type=(TextView)itemView.findViewById(R.id.type);
            select=(Button)itemView.findViewById(R.id.select);
            select.setVisibility(View.INVISIBLE);




        }


    }



    public static String formatNumberExample(Number number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
