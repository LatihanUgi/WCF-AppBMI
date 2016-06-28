using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Web.Configuration;
using System.Text;
using System.Collections;

public partial class AdminWeb_EditDeleteAdmin : System.Web.UI.Page
{
    ServiceReference1.ServiceWebClient admin = new ServiceReference1.ServiceWebClient();
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack)
        {
            dataAdmin();
            if (Session["Username"] != "")
            {
                User.Text += Session["Username"];
            }
            else
            {
                Session.RemoveAll();
                Response.Redirect("Login.aspx");
            }
        }
    }
    protected void Logout_Click(object sender, EventArgs e)
    {
        Session.RemoveAll();
        Response.Redirect("Login.aspx");
    }

    private void dataAdmin()
    {
        //Building an HTML string.
        StringBuilder html = new StringBuilder();

        //Table start.
        html.Append("<table class='table table-bordered'>");

        html.Append("<tr>");
        html.Append("<th>Username</th>");
        html.Append("<th>Status</th>");
        html.Append("<th>Aksi</th>");
        html.Append("</tr>");


        //Building the Data rows.
        ArrayList tampiladmin = new ArrayList();
        tampiladmin = new ArrayList(admin.DataAdmin());

        for (int i = 0; i < tampiladmin.Count; i++)
        {
            html.Append("<tr>");
            html.Append("<td>" + tampiladmin[i++] as string + "</td>");
            html.Append("<td>" + tampiladmin[i] as string + "</td>");
            html.Append("<td><a href='" + tampiladmin[i] as string + "'>Ubah</a></td>");
            html.Append("</tr>");
        }

        //Table end.
        html.Append("</table>");

        //Append the HTML string to Placeholder.
        DataAdmin.Controls.Add(new Literal { Text = html.ToString() });
    }
}