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
import pe.edu.utp.yanapaapp.interfaces.DonationClickListener;
import pe.edu.utp.yanapaapp.models.Donation;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {
    private List<Donation> mData;
    private LayoutInflater mInflater;
    private Context context;

    public void setClickListener(DonationClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private DonationClickListener clickListener;

    public DonationAdapter(List<Donation> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @NonNull
    @Override
    public DonationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_donation, null);
        return new DonationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Donation> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iconImage;
        TextView address,name,status;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            address = itemView.findViewById(R.id.codeTextView);
            name = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusTextView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        void bindData(Donation donation){
            iconImage.setColorFilter(Color.parseColor(donation.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(donation.getName());
            address.setText(donation.getAddress());
            //code.setText(String.valueOf(person.getCode()));
            status.setText(donation.getStatus());
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}