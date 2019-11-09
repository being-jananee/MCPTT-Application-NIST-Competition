package org.mcopenplatform.muoapi.mcopsdk;

import android.app.AlertDialog;
import org.mcopenplatform.muoapi.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActionItemAdapter extends RecyclerView.Adapter<ActionItemAdapter.ActionItemViewHolder> {

    private ArrayList<ActionItem> allItems;
    private Main2Activity activity;

    public ActionItemAdapter(Main2Activity ma, ArrayList<ActionItem> allItems) {
        this.allItems = allItems;
        this.activity = ma;
    }


    @Override
    public ActionItemAdapter.ActionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);
        return new ActionItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionItemAdapter.ActionItemViewHolder holder, int position) {
        ActionItem item = allItems.get(position);
        holder.content.setText(item.getContent());
        holder.name.setText(item.getUser());
        holder.timestamp.setText(item.getTimestamp());
        holder.tag.setText(item.getTag().toString());
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    public class ActionItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView content;
        private TextView timestamp;
        private TextView tag;

        public ActionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user);
            tag = itemView.findViewById(R.id.tag);
            content = itemView.findViewById(R.id.content);
            timestamp = itemView.findViewById(R.id.timestamp);

            itemView.setOnClickListener(activity);
            itemView.setOnLongClickListener(activity);

        }
    }
}
