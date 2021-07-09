package JDBCVsPostGresql;

import java.sql.*;

public class ExamplePostgre {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String jdbcUrl =
                "jdbc:postgresql://5193a109-0528-4782-a7cf-017b652f3c34:5432/vvoqdhdb"
        ;
        String username = "vvoqdhdb";
        String password = "UdDSnZeRgrE1iRpDxwG4kBdKt1DSBuav";

        try {
            Connection db = DriverManager.getConnection(jdbcUrl,username,password);
            String sql = "select * from game";
            PreparedStatement ps = db.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getInt("id"));
                System.out.print("Column 2 returned ");
                System.out.println(rs.getString("name"));
                System.out.print("Column 3 returned ");
                System.out.println(rs.getString("title"));
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}