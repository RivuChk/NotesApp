package com.rivuchk.rnd.notesapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rivu on 11-11-2017.
 */

public class NoteModel implements Serializable {
    @SerializedName("id") String noteID;
    @SerializedName("note") String note;
    @SerializedName("created_date") String createdDateTime;

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
