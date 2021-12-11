package usa.app.appmovilproyectos1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static final String dataBaseName = "dbFavoritos";
    private static final int dataVersion = 1;

    public DataBase(Context context){
        super(context,dataBaseName,null,dataVersion);
        sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FAVORITOS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,DESCRIPTION TEXT," +
                "PRICE INTEGER,IMAGEN INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FAVORITOS");
    }
    //Funciones personalizadas

    public Cursor getFavoritos()
    {
        Cursor datosFav = sqLiteDatabase.rawQuery("SELECT * FROM FAVORITOS",null);
        return datosFav;
    }

    public void deleteFavoritos(String name)
    {
        sqLiteDatabase.execSQL("DELETE FROM FAVORITOS WHERE NAME='"+name+"'");
    }

    public void agregarFavoritos(String name, String descrip, Integer price, Integer image)
    {
        ContentValues cValues = new ContentValues();
        cValues.put("NAME",name);
        cValues.put("DESCRIPTION",descrip);
        cValues.put("PRICE",price);
        cValues.put("IMAGEN",image);
        sqLiteDatabase.insert("FAVORITOS",null,cValues);
    }
}
