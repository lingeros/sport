package ling.sport.originalSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SurperAdminOper {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;
    DatabaseInformation d = new DatabaseInformation();

    private static SurperAdminOper surperAdminOperInstance = null;

    private SurperAdminOper(){

    }

    public static SurperAdminOper getInstance(){
        if(surperAdminOperInstance == null){
            surperAdminOperInstance = new SurperAdminOper();
        }
        return surperAdminOperInstance;
    }
    public boolean select(String s)//
    {
        boolean judge = false;
        try {
            conn = d.getconn();
            sql = "select * from surperadmin";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String ss = rs.getString(1);
                judge = ss.equals(s);
            }

        } catch (Exception e) {
            DebugPrint.DPrint(e);
        } finally {
            d.close(conn, ps, rs);
        }
        return judge;
    }

    public void add(String s) {
        try {
            conn = d.getconn();
            sql = "INSERT INTO surperadmin(admin_key)VALUES(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            int i = ps.executeUpdate();
            if (i != 0) {
                DebugPrint.DPrint("SurperAdminOper add success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            d.close(conn, ps, rs);
        }

    }

    public void set_surperadmin() {
        try {
            conn = d.getconn();
            sql = "create table surperadmin(			\r\n" +
                    "admin_key varchar(64)PRIMARY KEY\r\n" +
                    ")";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            int t = ps.executeUpdate();
            DebugPrint.DPrint(t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            d.close(conn, ps, rs);
        }
    }


}
