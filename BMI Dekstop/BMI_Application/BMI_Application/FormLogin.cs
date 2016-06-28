using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace BMI_Application
{
    public partial class FormLogin : Form
    {
        public FormLogin()
        {
            InitializeComponent();
        }
        
        private void button2_Click(object sender, EventArgs e)
        {
            ServiceReference1.ServiceClient obj = new ServiceReference1.ServiceClient();
            string user = textBox1.Text;
            string pswd = textBox2.Text;
            int i = obj.login(user, pswd);
            if(i == 1)
            {
                FormMenu menu = new FormMenu();
                menu.Show();
                this.Hide();
            }
            else
            {
                MessageBox.Show("Gagal Login");
            }
            //Koneksi con = new Koneksi();
            //SqlConnection sqlcon = con.BukaKoneksi();
            //SqlDataAdapter sda = new SqlDataAdapter("SELECT COUNT(*) FROM tb_admin WHERE admin ='" + textBox1.Text + "' AND password='" + textBox2.Text + "'", sqlcon);
            //DataTable dtAdmin = new DataTable();
            //sda.Fill(dtAdmin);
            //string admin = textBox1.Text;
            //string password = textBox2.Text;
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
        }
    }
}
