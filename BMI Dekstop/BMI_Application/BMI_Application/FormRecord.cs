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
    public partial class FormRecord : Form
    {
        ServiceReference1.ServiceClient obj = new ServiceReference1.ServiceClient();
        public FormRecord()
        {
            InitializeComponent();
        }
        private void FillDataGrid()
        {
            DataSet ds = obj.SemuaDataRecord();
            dataGridView1.DataSource = ds.Tables[0];
        }

        private void FormRecord_Load(object sender, EventArgs e)
        {
            FillDataGrid();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            FormMenu menu = new FormMenu();
            menu.Show();
            this.Hide();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string username = textBox1.Text;
            DataSet ds = obj.DataRecord(username);
            dataGridView1.DataSource = ds.Tables[0];
            string[] det = obj.RecordByID(username);

        }
    }
}
