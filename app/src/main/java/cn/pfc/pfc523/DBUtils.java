package cn.pfc.pfc523;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBUtils {
    public static MyHelper myHelper;
    public static SQLiteDatabase sqLiteDatabase;

    public static MyHelper cun(Context context){
        if(myHelper==null){
            myHelper=new MyHelper(context);
        }
        return myHelper;
    }
    public static SQLiteDatabase cha(Context context){
        if(sqLiteDatabase==null){
            sqLiteDatabase=cun(context).getWritableDatabase();
        }
        return sqLiteDatabase;
    }
}
