package cn.hihiwjc.app.xjblog.biz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/02/11.
 */
public class WordPressDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordpress.db";

    private static final int VERSION_INITIAL = 100;

    private static final int VERSION_CURRENT = VERSION_INITIAL;

    public WordPressDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_CURRENT);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordPressContract.Blogs.SCHEMA);
        db.execSQL(WordPressContract.Authors.SCHEMA);
        db.execSQL(WordPressContract.Posts.SCHEMA);
        db.execSQL(WordPressContract.Taxonomies.SCHEMA);
        db.execSQL(WordPressContract.Metas.SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
