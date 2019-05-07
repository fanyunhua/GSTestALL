package cn.pfc.pfc523.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "sju", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table3="create table table3(" +
                "car_id int," +
                "money int," +
                "ren varchar(50)," +
                "atime varchar(50))";
        String env = "create table env (dt varchar(30),co2 int,pm int ,g int, s int,w int,road int )";
        db.execSQL(table3);
        db.execSQL(env);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
