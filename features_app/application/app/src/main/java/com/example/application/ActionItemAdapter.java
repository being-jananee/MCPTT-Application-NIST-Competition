package com.example.application;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.Domain.ActionItem;

import java.util.ArrayList;
import java.util.Locale;

public class ActionItemAdapter extends RecyclerView.Adapter<ActionItemAdapter.ActionItemViewHolder> {

    private ArrayList<ActionItem> allItems;
    private MainActivity mainActivity;
    TextToSpeech t1;

    public ActionItemAdapter(MainActivity ma, ArrayList<ActionItem> allItems) {
        this.allItems = allItems;
        this.mainActivity = ma;
        this.t1 = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
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
        holder.name.setText(item.getUserId());
        holder.timestamp.setText(item.getTimestamp());
        holder.tag.setText(item.getTag().getName());
        holder.mapButton.setVisibility(item.getLatitude() != null ? View.VISIBLE : View.GONE);
        holder.mapButton.setTag(position);
        if(item.getCompleted() != null) {
            holder.s.setChecked(item.getCompleted());
        } else {
            holder.s.setChecked(false);
        }
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
        private Switch s;
        private Button ttsButton;
        private Button mapButton;

        public ActionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userId);
            tag = itemView.findViewById(R.id.tag);
            content = itemView.findViewById(R.id.content);
            timestamp = itemView.findViewById(R.id.timestamp);
            s = itemView.findViewById(R.id.switch2);
            ttsButton = itemView.findViewById(R.id.button1);
            mapButton = itemView.findViewById(R.id.button2);
            mapButton.setOnClickListener(mainActivity);
            itemView.setOnLongClickListener(mainActivity);
            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ActionItem item = allItems.get(getAdapterPosition());
                    item.setCompleted(isChecked);
                    mainActivity.updateEvent(item);
                }
            });
            ttsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toSpeak = content.getText().toString();
                    Toast.makeText(mainActivity, toSpeak,Toast.LENGTH_SHORT).show();
                    String utteranceId = this.hashCode()+"";
                    t1.speak(toSpeak, 0, null, utteranceId);
                }
            });
        }
    }
}
