package id.sch.smktelkom_mlg.selinakusmiawati.miudr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shelin on 17/10/2017.
 */

public class MiudrAdapter extends RecyclerView.Adapter<MiudrAdapter.MiudrVH> {
    Context context;
    List<Miudr> miudrs;

    OnItemClickListener clickListener;

    public MiudrAdapter(Context context, List<Miudr> miudrs) {
        this.context = context;
        this.miudrs = miudrs;

    }

    public MiudrVH onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("show","up");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.miudr_item, parent, false);
        MiudrVH viewHolder = new MiudrVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MiudrAdapter.MiudrVH holder, int position) {

        Log.d("displaying","items");
        holder.nrp.setText(miudrs.get(position).getNRP());
        holder.desc.setText(miudrs.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return miudrs.size();
    }

    class MiudrVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nrp, desc;

        public MiudrVH(View itemView) {
            super(itemView);

            nrp = (TextView)itemView.findViewById(R.id.act_item_title);
            desc = (TextView) itemView.findViewById(R.id.act_item_desc);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener ) {
        this.clickListener = (OnItemClickListener) itemClickListener;
    }
}
