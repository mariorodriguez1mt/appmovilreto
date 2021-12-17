package usa.app.appmovilproyectos1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                "IDC INTEGER," +
                "NAME TEXT,DESCRIPTION TEXT," +
                "PRICE INTEGER,IMAGEN TEXT)");
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

    /**public String verificarProducto(String name){
        Cursor cursor = getFavoritos();
        int a = 0;
        cursor.moveToFirst();
        //Log.e("Primero",cursor.getString(1));
        while(cursor.moveToNext()){
            Log.e("Stringname",cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
            if (cursor.getString(1) == name){
                a = 1;
            }
        }
        //Log.e("a = ",""+a);
        if(a==1){
            return "Encontrado";
        }
        else{
            return "No Encontrado";
        }
    }**/

    public void deleteFavoritos(Integer idc)
    {
        sqLiteDatabase.execSQL("DELETE FROM FAVORITOS WHERE IDC="+idc);
    }

    public void deleteTodo(){
        sqLiteDatabase.execSQL("DELETE FROM FAVORITOS");
    }

    public void agregarFavoritos(Integer idc, String name, String descrip, Integer price, String image)
    {
        ContentValues cValues = new ContentValues();
        cValues.put("IDC",idc);
        cValues.put("NAME",name);
        cValues.put("DESCRIPTION",descrip);
        cValues.put("PRICE",price);
        cValues.put("IMAGEN",image);
        sqLiteDatabase.insert("FAVORITOS",null,cValues);
    }
}
