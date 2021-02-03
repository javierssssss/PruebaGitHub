package com.example.pruebagithub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DataAccessPersona {
private PersistenciaDatos datos;
private SQLiteDatabase sql;
private Context contexto;

    public DataAccessPersona(PersistenciaDatos datos, SQLiteDatabase sql, Context contexto) {
        this.datos = datos;
        this.sql = sql;
        this.contexto = contexto;
    }

    public DataAccessPersona(Context contexto) {
        this.contexto = contexto;
    }

    public void openDatabase(){
        datos = new PersistenciaDatos(contexto,"DBTESTER",null,1);
        sql= datos.getWritableDatabase();
    }
    public void closeDatabase(){
        sql.close();
    }
    public long insert(Persona persona){
        openDatabase();
        long conteo=0;
        try{
            ContentValues valores = new ContentValues();
            valores.put("CEDULA",persona.cedula);
            valores.put("CLAVE",persona.clave);
            conteo= sql.insert("PERSONAS",null,valores);
            /*if (conteo>0){
              //  Toast.makeText(this," Persona agregada ",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(this," No se agregó ",Toast.LENGTH_LONG).show();
            }*/
        }catch(Exception e ){
            //Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            throw e;
        }
        finally{
            sql.close();
        }
        return conteo;
    }


    public Persona getPersonaByID(String cedula,String clave){
        openDatabase();
    Persona persona =null;
    try{
        String sentencia = "SELECT CEDULA,CLAVE FROM PERSONAS WHERE CEDULA=? AND CLAVE=?";//WHERE CEDULA = "+cedula; //+ " AND CLAVE= "+clave;
        Cursor cursor=sql.rawQuery(sentencia,new String[] { cedula,clave });
        if(cursor.moveToFirst()){
            persona=new Persona();
            persona.cedula=cursor.getString(0);
            persona.clave=cursor.getString(1);

        }
        cursor.close();
    }catch(Exception e){
       // Toast.makeText(contexto,e.getMessage(),Toast.LENGTH_LONG).show();
        throw e;
    }
    finally {
        sql.close();
    }

        return persona;
    }

    public ArrayList<Persona> selectPersonas(){
        openDatabase();
    ArrayList<Persona> personas = null;//new ArrayList<Persona>();
    try{
        Cursor cursor=sql.rawQuery("SELECT CEDULA,CLAVE FROM PERSONAS" ,null);
        personas = new ArrayList<Persona>();
        if(cursor.moveToFirst()){
            do{
                Persona persona=new Persona();
                persona.cedula=cursor.getString(0);
                persona.clave=cursor.getString(1);
                personas.add(persona);

            }while(cursor.moveToNext());

        }
    }catch(Exception e){
        throw e;
    }
    finally{
        closeDatabase();
    }
    return personas;
    }

    public long ActualizarPersona(Persona persona,int codigo){
    openDatabase();
    long conteo=0;
    try{
        ContentValues valores = new ContentValues();
        valores.put("CEDULA",persona.cedula);
        valores.put("CLAVE",persona.clave);
       /// valores.put("CODIGO",persona.clave);

        conteo= sql.update("PERSONAS",valores,"CODIGO="+codigo,null);

    }catch(Exception e){
        throw e;
    }
    finally{
        closeDatabase();
    }
    return conteo;
    }

    public int EliminarPersona(int codigo){
        openDatabase();
        int conteo=0;
        try{
            conteo=sql.delete("PERSONAS","CODIGO="+codigo,null);
        }catch(Exception e){
            throw e;
        }
        finally{
            closeDatabase();
        }
        return conteo;
    }
}
