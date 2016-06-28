using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;
using System.Web.Configuration;
using System.Text;
using System.Collections;

public partial class AdminWeb_AddAdmin : System.Web.UI.Page
{
    ServiceReference1.ServiceWebClient admin = new ServiceReference1.ServiceWebClient();
    protected void Page_Load(object sender, EventArgs e)
    {
        displaydataadmin();
        if (Session["Username"] != "")
        {
            User.Text += Session["Username"];
        }
        else
        {
            Session.RemoveAll();
            Response.Redirect("Login.aspx");
        }

        //if (!IsPostBack)
        //{
        //    Response.Cache.SetCacheability(HttpCacheability.NoCache);
        //    Response.Cache.SetExpires(DateTime.Now.AddSeconds(-1));
        //    Response.Cache.SetNoStore();
        //}

        Response.Cache.SetNoStore();
        Response.Cache.SetCacheability(HttpCacheability.NoCache);
    }
    public void displaydataadmin()
    {

        //Building an HTML string.
        StringBuilder html = new StringBuilder();

        //Table start.
        html.Append("<table class='table table-bordered'>");

        html.Append("<tr>");
        html.Append("<th>Username</th>");
        html.Append("<th>Status</th>");
        html.Append("</tr>");


        //Building the Data rows.
        ArrayList tampiladmin = new ArrayList();
        tampiladmin = new ArrayList(admin.TampilAdmin());

        for (int i = 0; i < tampiladmin.Count; i++)
        {
            html.Append("<tr>");
            html.Append("<td>" + tampiladmin[i++] as string + "</td>");
            html.Append("<td>" + tampiladmin[i] as string + "</td>");
            html.Append("</tr>");
        }

            //Table end.
            html.Append("</table>");

            //Append the HTML string to Placeholder.
            DataAdmin.Controls.Add(new Literal { Text = html.ToString() });
    }

    protected void simpanKat_Click(object sender, EventArgs e)
    {
        string statusadmin = "Aktif";
       int status = admin.TambahAdmin(txtuser.Text, txtpswd1.Text, statusadmin);
        if(status == 1)
        {
            Response.Write("<script>alert('Data Success Added');</script>");
            Response.Redirect("AddAdmin.aspx");
        }
        else
        {
            Response.Write("<script>alert('Data Failed Added');</script>");
            Response.Redirect("AddAdmin.aspx");
        }
    }
}