package cn.hihiwjc.app.xjblog.biz.data.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class DeleteTask extends DatabaseTask<Void, Void, Integer> {

    private String table;
    private String where;
    private String[] whereArgs;

    public DeleteTask(Context context, String table, String where, String[] whereArgs, DatabaseTaskCallback<Integer> callback) {
        super(context, callback);

        this.table = table;
        this.where = where;
        this.whereArgs = whereArgs;
    }

    @Override
    protected Integer exec() throws Exception {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(table, where, whereArgs);
    }
}
