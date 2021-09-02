package co.edu.unipiloto.mccc.gestransporte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class datoUsuario {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuario";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, pass text, nombre text, ap text)";


    public datoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();

    }

    public boolean insertUsuario(Usuario u) {

        if (buscar(u.getUsuario) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("ap", u.getApellido());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }

    }

    public int buscar(String u) {
        int x = 0;
        lista = selectUsuario();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario() {
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p) {
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(u) && cr.getString(2).equals(p)) {
                    a++;
                }
            } while (cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista=selectUsuario();
        for(Usuario us:lista){
            if(us.getUsuario().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        } return null;
    }
    public Usuario getUsuarioById(int id){
        lista=selectUsuario();
        for(Usuario us:lista){
            if(us.getId()==id){
                return us;
            }
        } return null;
    }
}
