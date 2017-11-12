package com.rivuchk.rnd.notesapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.rivuchk.rnd.notesapp.data.NoteModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by Rivu on 11-11-2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    private final Subject<ViewBooleanWrapper> adapterViewClicks;
    ArrayList<NoteModel> noteList = new ArrayList<>();

    public NotesAdapter(Subject<ViewBooleanWrapper> adapterViewClicks) {
        this.adapterViewClicks = adapterViewClicks;
    }

    public void setNoteList(@NonNull List<NoteModel> noteList) {
        this.noteList.clear();
        this.noteList.addAll(noteList);
        notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bindView(noteList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtNote;
        boolean isExpanded = false;

        public NoteViewHolder(View itemView) {
            super(itemView);
            txtNote = itemView.findViewById(R.id.txtNote);
        }

        void bindView(NoteModel note, int position) {
            txtNote.setText(note.getNote());

            txtNote.setMaxLines(3);
            txtNote.setEllipsize(TextUtils.TruncateAt.END);

            ViewBooleanWrapper wrapper = new ViewBooleanWrapper();
            wrapper.textView = txtNote;
            wrapper.isExpanded= false;

            RxView.clicks(itemView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(v->{
                        adapterViewClicks.onNext(wrapper);
                    });
        }
    }

    public class ViewBooleanWrapper {
        public TextView textView;
        public boolean isExpanded;
    }
}
