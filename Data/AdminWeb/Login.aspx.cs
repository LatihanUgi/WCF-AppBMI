using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

public partial class AdminWeb_Login : System.Web.UI.Page
{
    ServiceReference1.ServiceWebClient ceklogin = new ServiceReference1.ServiceWebClient();
    protected void Page_Load(object sender, EventArgs e)
    {
    }

    protected void btnSubmit_Click(object sender, EventArgs e)
    {
        string username = txtUserName.Text;
        string password = txtPWD.Text;
        int login = ceklogin.Login(username, password);
        if (login == 1)
        {
                    Session["Username"] = username;
                    //Session["Nama"] = user;

            Response.Redirect("Dashbord.aspx");
        }
        else
        {
            ClientScript.RegisterStartupScript(Page.GetType(), "validation", "<script language='javascript'>alert('Invalid Username and Password!')</script>");
        }
    }
}