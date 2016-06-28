using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace BMI_Application
{
    public partial class FormUpdateAdmin : Form
    {
        ServiceReference1.ServiceClient obj = new ServiceReference1.ServiceClient();
        public FormUpdateAdmin()
        {
            InitializeComponent();
        }
        private void FillDataGrid()
        {
            DataSet ds = obj.SemuaDataAdmin();
            dataGridView1.DataSource = ds.Tables[0];
        }
        private void button3_Click(object sender, EventArgs e)
        {
            string id = textBox1.Text;
            DataSet ds = obj.DataAdmin(id);
            //int i = obj.cekidadmin(id);
            string[] det = obj.AdminByID(id);
            if(det != null)
            {
                textBox2.Text = det[0].ToString();
                textBox3.Text = det[1].ToString();
                MessageBox.Show("Berhasil!");
            }
            else
            {
                MessageBox.Show("Gagal!");
            }
            //DataSet ds = obj.DataAdmin(id);
            //dataGridView1.DataSource = ds.Tables[0];
            //string[] det = obj.AdminByID(id);
            //if (det != null)
            //{
            //    textBox2.Text = det[1].ToString();
            //    textBox3.Text = det[2].ToString();
            //    MessageBox.Show("Data Found");
            //}
            //else
            //{
            //    MessageBox.Show("Data Tidak Ditemukan");
            //}
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string id = textBox1.Text;
            string admin = textBox2.Text;
            string password = textBox3.Text;

            if (admin == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox2, "Admin field cannot be empty");
            }
            else if (password == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox3, "Password field cannot be empty");
            }
            else
            {
                obj.UpdateAdmin(id, admin, password);
            }

            FillDataGrid();

            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string id = textBox1.Text;
            obj.DeleteAdmin(id);
            FillDataGrid();
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
        }

        private void FormUpdateAdmin_Load(object sender, EventArgs e)
        {
            FillDataGrid();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            FormMenu menu = new FormMenu();
            menu.Show();
            this.Hide();
        }
    }
}
