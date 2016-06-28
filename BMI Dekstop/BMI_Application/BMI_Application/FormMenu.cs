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
    public partial class FormMenu : Form
    {
        public FormMenu()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FormUser user = new FormUser();
            user.Show();
            this.Hide();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            FormUpdateAdmin admin = new FormUpdateAdmin();
            admin.Show();
            this.Hide();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            FormRecord record = new FormRecord();
            record.Show();
            this.Hide();
        }

        private void button1_MouseHover(object sender, EventArgs e)
        {
            label1.Text = "User Option";
        }

        private void button1_MouseLeave(object sender, EventArgs e)
        {
            label1.Text = "";
        }

        private void button4_MouseHover(object sender, EventArgs e)
        {
            label1.Text = "Admin Option";
        }

        private void button4_MouseLeave(object sender, EventArgs e)
        {
            label1.Text = "";
        }

        private void button2_MouseHover(object sender, EventArgs e)
        {
            label1.Text = "Record Option";
        }

        private void button2_MouseLeave(object sender, EventArgs e)
        {
            label1.Text = "";
        }

        private void FormMenu_Load(object sender, EventArgs e)
        {
            label1.Text = "";
        }

        private void button3_Click(object sender, EventArgs e)
        {
            FormAddAdmin admin = new FormAddAdmin();
            admin.Show();
            this.Hide();
        }

        private void button3_MouseHover(object sender, EventArgs e)
        {
            label1.Text = "Add Admin";
        }

        private void button3_MouseLeave(object sender, EventArgs e)
        {
            label1.Text = "";
        }
    }
}
