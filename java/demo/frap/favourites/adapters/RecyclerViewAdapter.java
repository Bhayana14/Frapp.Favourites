package demo.frap.favourites.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import demo.frap.favourites.R;
import demo.frap.favourites.activities.MainActivity;
import demo.frap.favourites.interfaces.RecyclerViewClickListener;
import demo.frap.favourites.models.Common;
import demo.frap.favourites.models.DataModel;

/**
 * Created by Ramandeep on 8/12/17.
 */

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    Context context;
    List<DataModel> DataModels;
    private int selectedPos = 0;
    int positions=-2;



    private static RecyclerViewClickListener itemListener;

    public RecyclerViewAdapter(Context context, RecyclerViewClickListener itemListener) {

        this.context = context;
        this.itemListener = itemListener;



    }



    public RecyclerViewAdapter(List<DataModel> getDataAdapter, Context context){

        super();

        this.DataModels = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        if(Common.highlight.isEmpty())
        {


        }
        else {
            for(int elem : Common.highlight){
                if(position==elem)
                {

                    holder.st_hand_cardview.setBackgroundColor(Color.parseColor("#ff0000"));

                }
            }

        }


        DataModel dataModels1 =  DataModels.get(position);
        holder.title.setText(dataModels1.getTitle());
        holder.description.setText(dataModels1.getDescription());
        // example String
        long value = Long.parseLong(dataModels1.getViewCount());
        if(dataModels1.getType().equals("internship"))
        {
            holder.type.setTextColor(Color.parseColor("#32CD32"));
        }
        else
        {
            holder.type.setTextColor(Color.parseColor("#DC143C"));

        }
        holder.type.setText(dataModels1.getType());
        holder.viewCount.setText(formatNumberExample(value));
        Picasso.with(context).load(dataModels1.getImage_url()).into(holder.profile_Image);

    }


    @Override
    public int getItemCount() {

        return DataModels.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView description;
        public com.mikhaellopez.circularimageview.CircularImageView profile_Image;
        public TextView viewCount;
        public TextView type;
        public Button select;
        public CardView st_hand_cardview;


        public ViewHolder(final View itemView) {

            super(itemView);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            title = (TextView) itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);
            profile_Image=(com.mikhaellopez.circularimageview.CircularImageView) itemView.findViewById(R.id.profile_image);
            viewCount=(TextView)itemView.findViewById(R.id.view_count);
            st_hand_cardview=(CardView)itemView.findViewById(R.id.st_hand_cardview);

            type=(TextView)itemView.findViewById(R.id.type);
            select=(Button)itemView.findViewById(R.id.select);
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ka",(Common.highlight.contains(Common.highlight)+""));
                    if(!Common.highlight.contains(getAdapterPosition())) {


                        st_hand_cardview.setBackgroundColor(Color.parseColor("#ff0000"));
                        Common.highlight.add(getAdapterPosition());

                        Common.fav_title.add(DataModels.get(getAdapterPosition()).getTitle());
                        Common.fav_image.add(DataModels.get(getAdapterPosition()).getImage_url());
                        Common.fav_type.add(DataModels.get(getAdapterPosition()).getType());
                        Common.fav_desc.add(DataModels.get(getAdapterPosition()).getDescription());
                        Common.fav_viewcount.add(DataModels.get(getAdapterPosition()).getViewCount());
                       context.startActivity(new Intent(context, MainActivity.class));

                    }



                }
            });



        }

        @Override
        public void onClick(View v)
        {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());


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
