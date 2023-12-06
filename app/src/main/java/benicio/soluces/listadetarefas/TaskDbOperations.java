package benicio.soluces.listadetarefas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDbOperations {
    private TaskDbHelper dbHelper;

    public TaskDbOperations(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public void addTask(String task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.COLUMN_TASK, task);
        db.insert(TaskContract.TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(
                TaskContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}

