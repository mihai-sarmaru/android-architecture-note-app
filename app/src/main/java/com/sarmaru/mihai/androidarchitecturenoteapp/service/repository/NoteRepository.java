package com.sarmaru.mihai.androidarchitecturenoteapp.service.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sarmaru.mihai.androidarchitecturenoteapp.service.database.NoteDatabase;
import com.sarmaru.mihai.androidarchitecturenoteapp.service.model.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao notDao;
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.notDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao notDao;
        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.notDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao notDao;
        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.notDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao notDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.notDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notDao.deleteAllNotes();
            return null;
        }
    }
}
