package outils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import model.Profil;

/**
 *   Class : Base de donnée local
 */
public class DataBase extends OrmLiteSqliteOpenHelper {



    private static final String DATABASE_NAME    = "quiz.db";
    private static final int    DATABASE_VERSION = 1;



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Profil.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Profil.class,true);
            onCreate(db, connectionSource);
        } catch (SQLException | java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public Dao.CreateOrUpdateStatus createOrUpdateEtudiant(Profil obj) throws SQLException, java.sql.SQLException {
        Dao<Profil, ?> dao = (Dao<Profil, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }


    public String createEtudiant(Profil profil)   {
        try {

            this.createOrUpdateEtudiant(profil);

            return profil.getIne();
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
