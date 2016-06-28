using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service" in code, svc and config file together.
public class Service : IService
{
    SqlConnection sqlcon = new SqlConnection(@"Data Source = UGIISPOYOWIDODO; Initial Catalog = ProjectWCF; User ID = sa; Password = 12345ugi");

    public string[] UserByID(string username)
    {
        string[] data = new string[8];
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();

                string sql = "select * from tb_user where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);

                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@username", username);
                    SqlDataReader dr = sqlcom.ExecuteReader();
                    if (dr.Read())
                    {
                        data[0] = dr.GetString(0);
                        data[1] = dr.GetString(1);
                        data[2] = dr.GetString(2);
                        data[3] = dr.GetString(3);
                        data[4] = dr.GetString(4);
                        data[5] = dr.GetString(5);
                        data[6] = dr.GetString(6);
                        data[7] = dr.GetString(7);
                    }
                    sqlcon.Close();
                }
            }
        }
        catch (Exception e)
        {
        }
        return data;
    }

    public int UpdateUser(string username, string password, string fullname, string dob, string gender, string phone, string email, string doj)
    {
        int result = 0;
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "update tb_user set gender = @gender, fullname = @fullname, dob = @dob, phonenumber = @phonenumb, email = @email, password = @password, doj = @doj where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@password", password);
                    sqlcom.Parameters.AddWithValue("@fullname", fullname);
                    sqlcom.Parameters.AddWithValue("@dob", dob);
                    sqlcom.Parameters.AddWithValue("@gender", gender);
                    sqlcom.Parameters.AddWithValue("@phonenumb", phone);
                    sqlcom.Parameters.AddWithValue("@email", email);
                    sqlcom.Parameters.AddWithValue("@doj", doj);
                    sqlcom.Parameters.AddWithValue("@username", username);
                    result = sqlcom.ExecuteNonQuery();

                    if (result != 0)
                    {
                        result = 1;
                    }
                }
                sqlcon.Close();
            }
        }
        catch (Exception e)
        {

        }
        return result;
    }

    public void DeleteUser(string username)
    {
        

        try
        {
            using (sqlcon)
            {
                    sqlcon.Open();
                    string sql = "delete from tb_user where username = @username";
                    string sql1 = "delete from tb_record where username = @username";
                    SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                    SqlCommand sqlcom1 = new SqlCommand(sql1, sqlcon);
                    using (sqlcom)
                    {
                        sqlcom.Parameters.AddWithValue("@username", username);
                        int result = sqlcom.ExecuteNonQuery();
                        if (result != 0)
                        {
                        }
                    }
                    using (sqlcom1)
                    {
                        sqlcom1.Parameters.AddWithValue("@username", username);
                        int result = sqlcom1.ExecuteNonQuery();
                        if (result != 0)
                        {
                        }
                    }
                    sqlcon.Close();
                
            }
        }
        catch (Exception e)
        {
        }
    }

    public DataSet SemuaDataUser()
    {
        DataSet ds = new DataSet();


        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "select * from tb_user";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    SqlDataAdapter sqlda = new SqlDataAdapter();
                    sqlda.SelectCommand = sqlcom;
                    sqlda.Fill(ds);
                }
                sqlcon.Close();
            }
        }
        catch (Exception e)
        {
        }
        return ds;
    }

    public DataSet DataUser(string username)
    {
        DataSet ds = new DataSet();
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "select * from tb_user where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@username", username);
                    SqlDataAdapter da = new SqlDataAdapter(sqlcom);
                    da.Fill(ds);
                }
                sqlcon.Close();
            }
        }
        catch (Exception x)
        {
        }
        return ds;
    }

    public string[] RecordByID(string id)
    {
        string[] data = new string[7];
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();

                string sql = "select * from tb_record where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);

                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@username", id);
                    SqlDataReader dr = sqlcom.ExecuteReader();
                    if (dr.Read())
                    {
                        data[0] = dr.GetString(0);
                        data[1] = dr.GetString(1);
                        data[2] = dr.GetString(2);
                        data[3] = dr.GetString(3);
                        data[4] = dr.GetString(4);
                        data[5] = dr.GetString(5);
                        data[6] = dr.GetString(6);
                    }
                    sqlcon.Close();
                }
            }
        }
        catch (Exception e)
        {
        }
        return data;
    }

    public void UpdateRecord(string id, string username, string gender, string height, string weight, string status, string date)
    {
        
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "update tb_record set  username = @username, gender = @gender, height = @height, weight = @weight, status = @status, date = @date where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@id_record", id);
                    sqlcom.Parameters.AddWithValue("@gender", gender);
                    sqlcom.Parameters.AddWithValue("@height", height);
                    sqlcom.Parameters.AddWithValue("@weight", weight);
                    sqlcom.Parameters.AddWithValue("@status", status);
                    sqlcom.Parameters.AddWithValue("@date", date);
                    sqlcom.Parameters.AddWithValue("@username", username);
                    int result = sqlcom.ExecuteNonQuery();

                    if (result != 0)
                    {
                    }
                }
                sqlcon.Close();
            }
        }
        catch (Exception e)
        {
        }
    }

    public void DeleteRecord(string id)
    {
        
        try
        {
            using (sqlcon)
            {
                
                    sqlcon.Open();
                    string sql = "delete from tb_record where id_record = @id_record";
                    SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                    using (sqlcom)
                    {
                        sqlcom.Parameters.AddWithValue("@id_record", id);
                        int result = sqlcom.ExecuteNonQuery();
                        if (result != 0)
                        {
                        }
                    }
                    sqlcon.Close();
            }
        }
        catch (Exception e)
        {
        }
    }
    public DataSet SemuaDataRecord()
    {
        DataSet ds = new DataSet();

        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "select * from tb_record";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    SqlDataAdapter sqlda = new SqlDataAdapter();
                    sqlda.SelectCommand = sqlcom;
                    sqlda.Fill(ds);
                }
                sqlcon.Close();
            }
        }
        catch (Exception e)
        {
        }
        return ds;
    }

    public DataSet DataRecord(string id)
    {
        string[] dataktg = new string[7];
        
        DataSet ds = new DataSet();
        try
        {
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "select * from tb_record where username = @username";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@username", id);
                    SqlDataAdapter da = new SqlDataAdapter(sqlcom);
                    da.Fill(ds);
                }
                sqlcon.Close();
            }
        }
        catch (Exception x)
        {
        }
        return ds;
    }

    public string[] AdminByID(string id)
    {
        string[] data = new string[3];

        try
        {
            using (sqlcon)
            {
                sqlcon.Open();

                string sql = "select * from tb_admin where id_admin = @id_admin";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                sqlcom.Parameters.AddWithValue("@id_admin", id);
                SqlDataReader dr = sqlcom.ExecuteReader();
                if (dr.Read())
                {
                    //data[0] = dr.GetString(0);
                    data[0] = dr.GetString(1);
                    data[1] = dr.GetString(2);
                }
                sqlcon.Close();
            }
        }
        catch (Exception e)
        {
        }
        return data;
    }

        public void UpdateAdmin(string id, string admin, string password)
        {
            

            try
            {
                using (sqlcon)
                {
                    sqlcon.Open();
                    string sql = "update tb_admin set username = @admin, password = @password where id_admin = @id_admin";
                    SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                    using (sqlcom)
                    {
                        sqlcom.Parameters.AddWithValue("@admin", admin);
                        sqlcom.Parameters.AddWithValue("@password", password);
                        sqlcom.Parameters.AddWithValue("@id_admin", id);
                        int result = sqlcom.ExecuteNonQuery();

                        if (result != 0)
                        {
                            
                        }
                    }
                    sqlcon.Close();
                }
            }
            catch (Exception e)
            {
                
            }
        }

        public void DeleteAdmin(string id)
        {
            

            try
            {
                using (sqlcon)
                {
                    
                        sqlcon.Open();
                        string sql = "delete from tb_admin where id_admin = @id_admin";
                        SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                        using (sqlcom)
                        {
                            sqlcom.Parameters.AddWithValue("@id_admin", id);
                            int result = sqlcom.ExecuteNonQuery();
                            if (result != 0)
                            {
                                
                            }
                        }
                        sqlcon.Close();
                    
                }
            }
            catch (Exception e)
            {
               
            }
        }
        public DataSet SemuaDataAdmin()
        {
            DataSet ds = new DataSet();

            
            try
            {
                using (sqlcon)
                {
                    sqlcon.Open();
                    string sql = "select * from tb_admin";
                    SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                    using (sqlcom)
                    {
                        SqlDataAdapter sqlda = new SqlDataAdapter();
                        sqlda.SelectCommand = sqlcom;
                        sqlda.Fill(ds);
                    }
                    sqlcon.Close();
                }
            }
            catch (Exception e)
            {
                
            }
            return ds;
        }

        public DataSet DataAdmin(string id)
        {
            string[] dataktg = new string[3];
            
            DataSet ds = new DataSet();
            try
            {
                using (sqlcon)
                {
                    sqlcon.Open();
                    string sql = "select * from tb_admin where id_admin = @id_admin";
                    SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                    using (sqlcom)
                    {
                        sqlcom.Parameters.AddWithValue("@id_admin", id);
                        SqlDataAdapter da = new SqlDataAdapter(sqlcom);
                        da.Fill(ds);
                    }
                    sqlcon.Close();
                }
            }
            catch (Exception x)
            {
                
            }
            return ds;
        }
        public string AutoIDAdmin()
        {
           
            string id = null;
            using (sqlcon)
            {
                sqlcon.Open();
                string sql = "SELECT TOP 1 id_admin from tb_admin ORDER BY id_admin DESC";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    SqlDataReader dr = sqlcom.ExecuteReader();
                    while (dr.Read())
                    {
                        id = dr.GetString(0);
                        string sub = id.Substring(3);
                        int a = Convert.ToInt16(sub) + 1;
                        if (a < 10)
                        {
                            id = "ADM00" + a;
                        }
                        else if (a < 100)
                        {
                            id = "ADM0" + a;
                        }
                        else if (a < 1000)
                        {
                            id = "ADM" + a;
                        }
                    }
                }
                sqlcon.Close();
            }
            return id;

        }

        public void AddAdmin(string admin, string password)
        {
            

            try
            {
                sqlcon.Open();
                string sql = "insert into tb_admin values (@admin, @password)";
                SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
                using (sqlcom)
                {
                    sqlcom.Parameters.AddWithValue("@admin", admin);
                    sqlcom.Parameters.AddWithValue("@password", password);
                    int result = sqlcom.ExecuteNonQuery();

                    if (result != 0)
                    {
                        
                    }

                }
                sqlcon.Close();
            }
            catch (Exception e)
            {
                
            }
        }
        public int login(string user, string pswd)
        {
            int i = 0;
            sqlcon.Open();
            string sql = "SELECT * FROM tb_admin WHERE username = @username AND password = @password";
            SqlCommand sqlcom = new SqlCommand(sql, sqlcon);
            sqlcom.Parameters.AddWithValue("@username", user);
            sqlcom.Parameters.AddWithValue("@password", pswd);
            SqlDataReader reader = sqlcom.ExecuteReader();
            if (reader.Read())
            {
                //try
                //{
                //    SqlCommand sqlupdate = new SqlCommand("update tb_cek_regis set status = 1 where serial_number = @serial_number", connection);
                //    using (sqlupdate)
                //    {
                //        sqlupdate.Parameters.AddWithValue("@serial_number", regis);
                //        int result = sqlupdate.ExecuteNonQuery();
                //        if (result != 0)
                //        {
                //            i = 1;
                //        }
                //        else
                //        {
                //            i = 0;
                //            //MessageBox.Show("Data Peminjaman Gagal Diubah!");
                //        }
                //    }
                //}
                //catch (Exception)
                //{
                //    MessageBox.Show("Gagal");
                //}
                //finally
                //{
                //    connection.Close();
                //}
                i = 1;
            }
            else
            {

                //MessageBox.Show("Data Tidak Ditemukan!");
                i = 0;
            }
            //DataTable dtAdmin = new DataTable();
            //using (sqlcom)
            //{
            //    sqlcom.Parameters.AddWithValue("@username", user);
            //    sqlcom.Parameters.AddWithValue("@password", pswd);
            //    int result = sqlcom.ExecuteNonQuery();

            //    if (dtAdmin.Rows[0][0].ToString() == "1")
            //    {
            //        i = 1;
            //    }
            //    else
            //    {
            //        i = 0;
            //    }

            //}
            sqlcon.Close();

            return i;
            //sda.Fill(dtAdmin);
            //if (dtAdmin.Rows[0][0].ToString() == "1")
            //{
            //    MessageBox.Show("Selamat Datang " + textBox1.Text + "!");
            //    this.Hide();
            //    new FormMenu().Show();
            //}
            //else
            //{
            //    MessageBox.Show("Akun Pengguna atau Kata Sandi Tidak Tepat!");
            //    textBox1.Text = "";
            //    textBox2.Text = "";
            //}
            //sda.Fill(dtAdmin);
        }

        public int cekidadmin(string id)
        {
            int i = 0;
            try
            {
                SqlDataAdapter adapter = new SqlDataAdapter();
                //int ubah = 1;
                SqlParameter sqlP;
                sqlcon.Open();
                SqlCommand command = new SqlCommand("SELECT * FROM tb_admin WHERE id_admin = @serial_number", sqlcon);
                sqlP = command.Parameters.Add("@serial_number", id);
                sqlP.Value = id;
                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    i = 1;
                }
                else
                {

                    //MessageBox.Show("Data Tidak Ditemukan!");
                    i = 0;
                }
                sqlcon.Close();
            }
            catch (SqlException)
            {

            }
            return i;
        }
    }

