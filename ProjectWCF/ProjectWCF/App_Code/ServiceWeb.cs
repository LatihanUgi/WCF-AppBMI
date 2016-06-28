using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Data.SqlClient;
using System.ServiceModel.Web;
using System.Collections;
using System.Data;
using System.Web.Configuration;
using System.Collections;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "ServiceWeb" in code, svc and config file together.
public class ServiceWeb : IServiceWeb
{
    private SqlConnection connection;
	/*public int Login(string username, string password)
    {
        int status = 0;
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        koneksi = new SqlConnection(strConn);
        koneksi.Open();
        SqlCommand cmd = new SqlCommand("select * from tb_admin where username =@username and password=@password", koneksi);
        cmd.Parameters.AddWithValue("@username", username);
        cmd.Parameters.AddWithValue("@password", password);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataTable dt = new DataTable();
        da.Fill(dt);
        if (dt.Rows.Count > 0)
        {
            SqlCommand cmd1 = new SqlCommand("select * from tb_admin where username =@username1", koneksi);
            cmd1.Parameters.AddWithValue("@username1", username);
            SqlDataAdapter da1 = new SqlDataAdapter(cmd1);
            DataTable dt1 = new DataTable();
            da.Fill(dt1);
            SqlDataReader dr = cmd1.ExecuteReader();
            if (dr.Read())
            {
                string statusadmin = dr.GetString(3);
                if (statusadmin == "Aktif")
                {
                    status = 1;
                }
                else
                {
                    status = 0;
                }
            }
        }
        else
        {
            status = 0;
        }
        return status;
	}

    public ArrayList TampilAdmin()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        koneksi = new SqlConnection(strConn);
        koneksi.Open();
        ArrayList listadmin = new ArrayList();
        string sql = "select * from tb_admin order by id_admin desc";
        SqlCommand sqlcon = new SqlCommand(sql, koneksi);
        using (koneksi)
        {
            SqlDataReader dr = sqlcon.ExecuteReader();
            while (dr.Read())
            {
                listadmin.Add(dr.GetString(1));
                listadmin.Add(dr.GetString(3));
            }
        }
        koneksi.Close();
        return listadmin;
    }

    public ArrayList DataAdmin()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        koneksi = new SqlConnection(strConn);
        koneksi.Open();
        ArrayList listadmin = new ArrayList();
        string sql = "select * from tb_admin order by id_admin desc";
        SqlCommand sqlcon = new SqlCommand(sql, koneksi);
        using (koneksi)
        {
            SqlDataReader dr = sqlcon.ExecuteReader();
            while (dr.Read())
            {
                listadmin.Add(dr.GetString(1));
                listadmin.Add(dr.GetString(3));
            }
        }
        koneksi.Close();
        return listadmin;
    }


    public int TambahAdmin(string user, string password, string statusadmin)
    {
        int status = 0;
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        koneksi = new SqlConnection(strConn);
        koneksi.Open();
        using (koneksi)
        {
            string sql = "insert into tb_admin (username, password, status) values (@username, @password, @status)";
            SqlCommand sqlcom = new SqlCommand(sql, koneksi);
            using (sqlcom)
            {
                sqlcom.Parameters.Add(new SqlParameter("@username", user));
                sqlcom.Parameters.Add(new SqlParameter("@password", password));
                sqlcom.Parameters.Add(new SqlParameter("@status", statusadmin));
                //sqlcom.ExecuteNonQuery();
                int statustambah = sqlcom.ExecuteNonQuery();
                if (statustambah != 0)
                {
                    status = 1;
                }
                else
                {
                    status = 0;
                }
            }
            //HttpResponse.ReferenceEquals("KatBerita.aspx");
        }
        koneksi.Close();
        return status;
    }*/

    /*public List<DataArtikel> GetAllArtikel()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_artikel", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataArtikel> a = new List<DataArtikel>();
        //int i = 1;
        while (dr.Read())
        {
            a.Add(new DataArtikel { Id_Artikel = dr.GetInt32(0), Judul = dr.GetString(1), Artikel = dr.GetString(2), Gambar = dr.GetString(3), Tanggal = dr.GetString(4), Source = dr.GetString(5) });

        }
        return a;
        connection.Close();
    }*/
}
