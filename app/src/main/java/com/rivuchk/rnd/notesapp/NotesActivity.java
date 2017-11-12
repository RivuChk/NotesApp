package com.rivuchk.rnd.notesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.rivuchk.rnd.notesapp.api.APIClient;
import com.rivuchk.rnd.notesapp.api.APIService;
import com.rivuchk.rnd.notesapp.data.NoteModel;
import com.rivuchk.rnd.notesapp.utils.Utils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class NotesActivity extends AppCompatActivity {


    private ArrayList<NoteModel> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Subject<NotesAdapter.ViewBooleanWrapper> adapterViewClicks = PublishSubject.create();
        adapterViewClicks
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wrapper -> {
                    if (!wrapper.isExpanded) {
                        wrapper.textView.setMaxLines(30);
                        wrapper.textView.setEllipsize(TextUtils.TruncateAt.END);
                        wrapper.isExpanded = true;
                    } else {
                        wrapper.textView.setMaxLines(3);
                        wrapper.textView.setEllipsize(TextUtils.TruncateAt.END);
                        wrapper.isExpanded = false;
                    }
                });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RxView.clicks(fab)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view->{
                    Snackbar.make(fab, "Add Notes is not implemented yet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                });

        RecyclerView rvNotes = findViewById(R.id.rvNotes);

        NotesAdapter adapter = new NotesAdapter(adapterViewClicks);
        rvNotes.setAdapter(adapter);

        APIClient.getAPIService(APIClient.LOG_REQ_RES_BODY_HEADERS)
                .getNotes()
                .compose(new Utils.BackgroundLoaderSingle<>())
                .subscribe(notesResponse->{
                    adapter.setNoteList(notesResponse.getNotes());
                    noteList = notesResponse.getNotes();
                });

        EditText edNotesSearch = findViewById(R.id.edNotesSearch);
        RxTextView.textChanges(edNotesSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(text->{
                    if(noteList!=null) {
                        if (text.toString().trim().isEmpty()) {
                            adapter.setNoteList(noteList);
                        } else {
                            Observable.fromIterable(noteList)
                                    .filter(noteModel ->
                                            noteModel.getNote().toLowerCase()
                                                    .contains(text.toString().toLowerCase()))
                                    .toList()
                                    .subscribe(noteList -> {
                                        adapter.setNoteList(noteList);
                                    });
                        }
                    }
                });
    }

}
