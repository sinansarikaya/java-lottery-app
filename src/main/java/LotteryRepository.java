import java.sql.*;
//TODO: Bu sinif static olabilir mi? Neden static degil.
public class LotteryRepository {
    private Connection conn;
    private Statement st;
    private PreparedStatement prst;

    private void setConnection() {
        try {
            this.conn = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/lottery", "techpront", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setStatement() {
        try {
            this.st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setPreparedStatement(String sql) {
        try {
            this.prst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveParticipant(String name) {
        String sql = "INSERT INTO names(name) VALUES(?)";
        setConnection();
        setPreparedStatement(sql);
        try {
            prst.setString(1, name); // 1 numaralı parametreye name değişkenini ekler
            prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void getAll(){
        setConnection();
        setStatement();
        int counter = 0;
        try {
            ResultSet rst = st.executeQuery("SELECT * FROM names");
            ColorPrinter.printColorfulText("Katılımcı Listesi:", ColorPrinter.MAGENTA);

            while (rst.next()) {
                int userID = rst.getInt("ID");
                System.out.println(userID + " " + rst.getString("name"));
                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
                System.out.println("Toplam : " + counter + " kişi");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void delete(int id) {
        setConnection();
        String deleteQuery = "DELETE FROM names WHERE id = ?";
        setPreparedStatement(deleteQuery);
        try {
            prst.setInt(1, id); // 1 numaralı parametreye delId'yi ekler
            int rowsAffected = prst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("İşlem başarıyla gerçekleşti. ID : " + id);
                // ArrayList'den de silme işlemi
                //this.defNames.removeIf(name -> name.getId() == delId); // TODO: Person classina id ekleyip proje yapisini id tabanli yap
            } else {
                System.out.println("İsim silinemedi.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
