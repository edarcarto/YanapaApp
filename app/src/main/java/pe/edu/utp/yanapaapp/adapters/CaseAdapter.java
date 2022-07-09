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
import pe.edu.utp.yanapaapp.interfaces.CaseClickListener;
import pe.edu.utp.yanapaapp.models.Case;

public class CaseAdapter extends RecyclerView.Adapter<CaseAdapter.ViewHolder> {
    private List<Case> mData;
    private LayoutInflater mInflater;
    private Context context;

    public void setClickListener(CaseClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private CaseClickListener clickListener;

    public CaseAdapter(List<Case> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @NonNull
    @Override
    public CaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_case, null);
        return new CaseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Case> items){ mData = items; }

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

        void bindData(Case _case){
            iconImage.setColorFilter(Color.parseColor(_case.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(_case.getName());
            address.setText(_case.getAddress());
            //code.setText(String.valueOf(person.getCode()));
            status.setText(_case.getStatus());
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
