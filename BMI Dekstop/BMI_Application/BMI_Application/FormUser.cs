using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Collections;
using System.IO;
using System.Threading;
using System.Text.RegularExpressions;

namespace BMI_Application
{
    public partial class FormUser : Form
    {
        ServiceReference1.ServiceClient bok = new ServiceReference1.ServiceClient();
        public FormUser()
        {
            InitializeComponent();
        }
        private void FillDataGrid()
        {
            DataSet ds = bok.SemuaDataUser();
            dataGridView1.DataSource = ds.Tables[0];
        }
        private void button2_Click(object sender, EventArgs e)
        {
            string username = textBox1.Text;
            string gender = comboBox1.SelectedItem.ToString();
            string fullname = textBox3.Text;
            string dob = textBox4.Text;
            string phone = textBox5.Text;
            string email = textBox6.Text;
            string password = textBox2.Text;
            string doj = textBox7.Text;
            

            if (password == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox2, "Password field cannot be empty");
            }
            else if (fullname == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox3, "Fullname field cannot be empty");
            }
            else if (dob == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox4, "Date of Birth field cannot be empty");
            }
            else if (gender == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(comboBox1, "Gender field cannot be empty");
            }
            else if (phone == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox5, "Phone Number field cannot be empty");
            }
            else if (email == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox6, "Email field cannot be empty");
            }
            else if (doj == "")
            {
                errorProvider1.Clear();
                errorProvider1.SetError(textBox7, "Date of Join field cannot be empty");
            }
            else
            {
                int i = bok.UpdateUser(username, password, fullname, dob, gender, phone, email, doj);
                if (i == 1)
                {
                    FillDataGrid();

                    textBox1.Text = "";
                    textBox2.Text = "";
                    textBox3.Text = "";
                    textBox4.Text = "";
                    comboBox1.Text = "";
                    textBox5.Text = "";
                    textBox6.Text = "";
                    textBox7.Text = "";
                }
                else
                {
                    MessageBox.Show("Gagal Update!");
                }
            }

            
        }

        private void button3_Click(object sender, EventArgs e)
        {
            string username = textBox1.Text;
            bok.DeleteUser(username);
            FillDataGrid();
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
            textBox4.Text = "";
            comboBox1.Text = "";
            textBox5.Text = "";
            textBox6.Text = "";
            textBox7.Text = "";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string username = textBox1.Text;
            DataSet ds = bok.DataUser(username);
            dataGridView1.DataSource = ds.Tables[0];
            string[] det = bok.UserByID(username);
            if (det != null)
            {
                textBox2.Text = det[6].ToString();
                textBox3.Text = det[2].ToString();
                textBox4.Text = det[3].ToString();
                comboBox1.Text = det[1].ToString();
                textBox5.Text = det[4].ToString();
                textBox6.Text = det[5].ToString();
                textBox7.Text = det[7].ToString();
                MessageBox.Show("Data Found");
            }
            else
            {
                MessageBox.Show("Data Tidak Ditemukan");
            }
        }

        private void Form1_Load(object sender, EventArgs e)
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
