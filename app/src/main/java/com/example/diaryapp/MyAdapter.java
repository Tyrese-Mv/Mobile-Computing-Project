package com.example.diaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    RealmResults<Notes> notesList;

    public MyAdapter(Context context, RealmResults<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        assert notes != null;
        holder.titleOutput.setText(notes.getTitle());
        holder.descriptionOutput.setText(notes.getDescription());

        String formatedTime = DateFormat.getDateTimeInstance().format(notes.createdTime);
        holder.timeOutput.setText(formatedTime);

        holder.itemView.setOnLongClickListener(v -> {

            PopupMenu menu = new PopupMenu(context,v);
            menu.getMenu().add("DELETE");
            menu.setOnMenuItemClickListener(item -> {
                if(item.getTitle().equals("DELETE")){

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    notes.deleteFromRealm();
                    realm.commitTransaction();
                    Toast.makeText(context, "Diary Entry Deleted",Toast.LENGTH_SHORT).show();
                }
                return true;
            });
            menu.show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutnput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
