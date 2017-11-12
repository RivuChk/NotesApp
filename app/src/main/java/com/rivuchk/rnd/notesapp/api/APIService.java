package com.rivuchk.rnd.notesapp.api;

import com.rivuchk.rnd.notesapp.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rivu on 11-11-2017.
 */

public interface APIService {
    @POST(Constants.GET_NOTES)
    Single<GetNotesAPIResponse> getNotes();

    @FormUrlEncoded
    @POST(Constants.EDIT_NOTES)
    Single<BaseAPIResponse> editNote(
            @Field("note_id") String todoID,
            @Field("note") String todo
    );

    @FormUrlEncoded
    @POST(Constants.ADD_NOTES)
    Single<BaseAPIResponse> addNote(@Field("new_note") String note);
}
