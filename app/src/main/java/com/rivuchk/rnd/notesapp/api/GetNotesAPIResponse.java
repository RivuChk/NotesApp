package com.rivuchk.rnd.notesapp.api;

import com.google.gson.annotations.SerializedName;
import com.rivuchk.rnd.notesapp.data.NoteModel;

import java.util.ArrayList;

/**
 * Created by Rivu on 11-11-2017.
 */

public class GetNotesAPIResponse extends BaseAPIResponse {
    @SerializedName("data") ArrayList<NoteModel> notes;

    public ArrayList<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NoteModel> notes) {
        this.notes = notes;
    }
}
