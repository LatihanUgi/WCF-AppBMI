using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.ServiceModel.Web;
using System.Data.SqlClient;
using System.Data;
using System.Web.Configuration;
using System.Collections;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "User" in code, svc and config file together.
public class User : IUser
{
    private SqlConnection connection;
    /* public int AddUser(string username, string gender, string fullname, string dob, string phonenumber, string email, string password, string doj)
     {

         int i = 0;
         //int ubah = 1;
         Koneksi conn = new Koneksi();
         SqlConnection connection = conn.con();
         connection.Open();
         try
         {
             SqlCommand command = new SqlCommand("insert into tb_user (username, gender, fullname, dob, phonenumber, email, password, doj) values ('"
                + username + "','" + gender + "','" + fullname + "','" + dob + "','" + phonenumber + "','"
              + email + "','" + password + "', '" + doj + "');", connection);
             int n = command.ExecuteNonQuery();
             if (n != 0)
             {
                 i = 1;
             }
             else
             {
                 i = 0;
             }
             connection.Close();
         }
         catch (Exception ex)
         {
             i = 0;
         }
         finally
         {
             connection.Close();
         }
         return i;
     }  */

    public string AddUser(string username, string gender, string fullname, string dob, string phonenumber, string email, string password, string doj)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        string Status;
        connection.Open();
        /*SqlCommand cmd = new SqlCommand("insert into tb_user (username, gender, fullname, dob, phonenumber, email, password, doj) values (@Ads_username, @Ads_gender, @Ads_fullname, @Ads_dob, @Ads_phonenumber, @Ads_email, @Ads_password, @Ads_doj)", connection);
        cmd.Parameters.AddWithValue("@Ads_username", reguser.Ads_User);
        cmd.Parameters.AddWithValue("@Ads_gender", reguser.Ads_Gender);
        cmd.Parameters.AddWithValue("@Ads_fullname", reguser.Ads_Fullname);
        cmd.Parameters.AddWithValue("@Ads_dob", reguser.Ads_Dob);
        cmd.Parameters.AddWithValue("@Ads_phonenumber", reguser.Ads_PhoneNumber);
        cmd.Parameters.AddWithValue("@Ads_email", reguser.Ads_Email);
        cmd.Parameters.AddWithValue("@Ads_password", reguser.Ads_Password);
        cmd.Parameters.AddWithValue("@Ads_dpj", reguser.Ads_Doj);

        int result = cmd.ExecuteNonQuery();
        if (result == 1)
        {
            Status = "registered successfully";
        }
        else
        {
            Status = "could not be registered";
        }*/

        try
        {
            SqlCommand command = new SqlCommand("insert into tb_user (username, gender, fullname, dob, phonenumber, email, password, doj) values ('"
               + username + "','" + gender + "','" + fullname + "','" + dob + "','" + phonenumber + "','"
             + email + "','" + password + "', '" + doj + "');", connection);
            int n = command.ExecuteNonQuery();
            if (n != 0)
            {
                Status = "registered successfully";
            }
            else
            {
                Status = "could not be registered";
            }
            connection.Close();
        }
        catch (Exception ex)
        {
            Status = ex.Message;
        }
        finally
        {
            connection.Close();
        }
        return Status;
    }

    public string AddRecord(string username, string status, string bbi, string height, string weight, string date)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        string Status;
        connection.Open();
        /*SqlCommand cmd = new SqlCommand("insert into tb_user (username, gender, fullname, dob, phonenumber, email, password, doj) values (@Ads_username, @Ads_gender, @Ads_fullname, @Ads_dob, @Ads_phonenumber, @Ads_email, @Ads_password, @Ads_doj)", connection);
        cmd.Parameters.AddWithValue("@Ads_username", reguser.Ads_User);
        cmd.Parameters.AddWithValue("@Ads_gender", reguser.Ads_Gender);
        cmd.Parameters.AddWithValue("@Ads_fullname", reguser.Ads_Fullname);
        cmd.Parameters.AddWithValue("@Ads_dob", reguser.Ads_Dob);
        cmd.Parameters.AddWithValue("@Ads_phonenumber", reguser.Ads_PhoneNumber);
        cmd.Parameters.AddWithValue("@Ads_email", reguser.Ads_Email);
        cmd.Parameters.AddWithValue("@Ads_password", reguser.Ads_Password);
        cmd.Parameters.AddWithValue("@Ads_dpj", reguser.Ads_Doj);

        int result = cmd.ExecuteNonQuery();
        if (result == 1)
        {
            Status = "registered successfully";
        }
        else
        {
            Status = "could not be registered";
        }*/

        try
        {
            SqlCommand command = new SqlCommand("insert into tb_record (username, status, bbi, height, weight, date) values ('"
               + username + "','" + status + "','" + bbi + "','" + height + "','" + weight + "','"
             + date + "');", connection);
            int n = command.ExecuteNonQuery();
            if (n != 0)
            {
                Status = "record has successfully added";
            }
            else
            {
                Status = "could not be added record";
            }
            connection.Close();
        }
        catch (Exception ex)
        {
            Status = ex.Message;
        }
        finally
        {
            connection.Close();
        }
        return Status;
    }
    public string Login(string username, string password)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        //string Status;
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_user where username='" + username + "' AND password='" + password + "'", connection);
        SqlDataReader reader = command.ExecuteReader();

        if (reader.Read())
        {
            return "1";
        }
        else
        {
            return "0";
        }
        connection.Close();
    }

    public List<Record> GetRecord(string username)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_record where username='" + username + "' order by id_record desc", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<Record> r = new List<Record>();
        //int i = 1;
        while (dr.Read())
        {
            r.Add(new Record { Status = dr.GetString(2), Bbi = dr.GetString(3), Tinggi = dr.GetString(4), Berat = dr.GetString(5), Tanggal = dr.GetString(6) });

        }
        return r;
        connection.Close();
    }

    public List<DataUser> GetUser(string username)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_user where username='" + username + "'", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataUser> u = new List<DataUser>();
        //int i = 1;
        if (dr.Read())
        {
            u.Add(new DataUser { Username = dr.GetString(0), Gender = dr.GetString(1), Fullname = dr.GetString(2), Dob = dr.GetString(3), Phonenumber = dr.GetString(4), Email = dr.GetString(5), Password = dr.GetString(6) });

        }
        return u;
        connection.Close();
    }

    public List<DataUser> GetAllUser()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_user", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataUser> u = new List<DataUser>();
        //int i = 1;
        while (dr.Read())
        {
            u.Add(new DataUser { Username = dr.GetString(0), Gender = dr.GetString(1), Fullname = dr.GetString(2), Dob = dr.GetString(3), Phonenumber = dr.GetString(4), Email = dr.GetString(5), Password = dr.GetString(6) });

        }
        return u;
        connection.Close();
    }

    /*public void GetUser(string username)
    {
    string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
    connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_user where username='" + username +"'", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataUser> u = new List<DataUser>(); 
        //int i = 1;
            if (dr.Read())
                 {
//u.Add(new DataUser { 
string Username = dr.GetString(0);
string Gender = dr.GetString(1);
string Fullname = dr.GetString(2);
string Dob = dr.GetString(3);
string Phonenumber = dr.GetString(4);
string Email = dr.GetString(5);
string Password = dr.GetString(6);
					
                }
                    //return u;
        connection.Close();
    }*/

    public List<DataArtikel> GetAllArtikel()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_artikel order by id_artikel desc", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataArtikel> a = new List<DataArtikel>();
        //int i = 1;
        while (dr.Read())
        {
            a.Add(new DataArtikel { Id_Artikel = dr.GetInt32(0), Judul = dr.GetString(1), Artikel = dr.GetString(2), Gambar = dr.GetString(3), Tanggal = dr.GetString(4), Source = dr.GetString(5) });

        }
        return a;
        connection.Close();
    }

    public List<DataArtikel> GetArtikel(string id)
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT * FROM tb_artikel where id_artikel = '" + id +"'", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataArtikel> a = new List<DataArtikel>();
        //int i = 1;
        while (dr.Read())
        {
            a.Add(new DataArtikel { Id_Artikel = dr.GetInt32(0), Judul = dr.GetString(1), Artikel = dr.GetString(2), Gambar = dr.GetString(3), Tanggal = dr.GetString(4), Source = dr.GetString(5) });

        }
        return a;
        connection.Close();
    }
    public List<DataArtikel> GetNewArtikel()
    {
        string strConn = WebConfigurationManager.ConnectionStrings["projectwcf"].ConnectionString;
        connection = new SqlConnection(strConn);
        connection.Open();
        SqlCommand command = new SqlCommand("SELECT Top 3 * FROM tb_artikel order by id_artikel desc", connection);
        SqlDataReader dr = command.ExecuteReader();
        List<DataArtikel> a = new List<DataArtikel>();
        //int i = 1;
        while (dr.Read())
        {
            a.Add(new DataArtikel { Id_Artikel = dr.GetInt32(0), Judul = dr.GetString(1), Artikel = dr.GetString(2), Gambar = dr.GetString(3), Tanggal = dr.GetString(4), Source = dr.GetString(5) });

        }
        return a;
        connection.Close();
    }
}