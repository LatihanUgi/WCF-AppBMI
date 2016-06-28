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
    public partial class FormAddAdmin : Form
    {
        public FormAddAdmin()
        {
            InitializeComponent();
        }

        ServiceReference1.ServiceClient obj = new ServiceReference1.ServiceClient();
        private void FillDataGrid()
        {
            DataSet ds = obj.SemuaDataAdmin();
            dataGridView1.DataSource = ds.Tables[0];
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
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
                obj.AddAdmin(admin, password);
            }

            FillDataGrid();

            textBox2.Clear();
            textBox3.Clear();
        }

        private void FormAddAdmin_Load(object sender, EventArgs e)
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
