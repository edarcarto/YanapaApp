package pe.edu.utp.yanapaapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.edu.utp.yanapaapp.R;
import pe.edu.utp.yanapaapp.interfaces.PlaceClickListener;
import pe.edu.utp.yanapaapp.net.response.Zone;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {
    private List<Zone> mData;
    private LayoutInflater mInflater;
    private Context context;

    public void setClickListener(PlaceClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private PlaceClickListener clickListener;

    public PlaceListAdapter(List<Zone> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @NonNull
    @Override
    public PlaceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_place, null);
        return new PlaceListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Zone> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iconImage;
        TextView address,name,status;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            address = itemView.findViewById(R.id.addressTextView);
            name = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusTextView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        void bindData(Zone zone){
            name.setText(zone.getNombrelocal());
            address.setText(zone.getDireccion());
            /*iconImage.setColorFilter(Color.parseColor(person.getColor()), PorterDuff.Mode.SRC_IN);
            //code.setText(String.valueOf(person.getCode()));
            status.setText(person.getStatus());*/
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
