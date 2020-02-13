package ling.sport.originalSources;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class countCycle {
    private double EARTH_RADIUS = 6378.137;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static String sql;
    countCycle cc = new countCycle();
    DatabaseInformation d = new DatabaseInformation();
    public double rad(double d) {
        return d * Math.PI / 180.0;
    }
    public double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }
    public double countD(String id) {
        double i = 0;
        countCycle cc = new countCycle();
        ArrayList<String> array = new ArrayList();
        try {
            conn = d.getconn();
            sql = "SELECT COUNT(*) FROM historybd where run='false' and id=" + "'" + id + "'" + " order by set_time ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(10) + "," + rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            d.close(conn, ps, rs);
        }
        for (int j = 0; j < array.size() && array.get(j + 1) != null; j++) {

            String[] a = array.get(j).split(",");

            String[] b = array.get(j + 1).split(",");

            i = i + cc.getDistance(Double.valueOf(a[0]), Double.valueOf(a[1]),
                    Double.valueOf(b[0]), Double.valueOf(b[1]));
        }
        return i;
    }
    public int cycleNum(String id) {
        int i = 0;
        double lat, lng;
        ArrayList<String> array = new ArrayList();
        try {
            conn = d.getconn();
            sql = "SELECT COUNT(*) FROM historybd	 where run='false' order by set_time ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(10) + "," + rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            d.close(conn, ps, rs);
        }
        String[] s = array.get(1).split(",");
        lat = Double.valueOf(s[0]);
        lng = Double.valueOf(s[1]);

        for (int j = 0; j < array.size(); j++) {
            String[] a = array.get(j).split(",");
            if (cc.getDistance(Double.valueOf(a[0]), Double.valueOf(a[0]), lat, lng) <= 30) {
            }
        }
        if (cc.countD(id) >= 400) {
            i++;
        }
        return i;
    }
}
