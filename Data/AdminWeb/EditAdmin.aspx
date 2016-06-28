<%@ Page Language="C#" AutoEventWireup="true" CodeFile="EditAdmin.aspx.cs" Inherits="AdminWeb_EditDeleteAdmin" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Update Admin | Administrator</title>

    <link rel="shortcut icon" href="images/ico.ico"/>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
#Logout {
    border:none;
    outline:none;
    margin-top:10px;
}
	</style>

</head>
<body>
    <div id="wrapper">

    <form id="form1" runat="server">
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background:#000; border-bottom:1px solid #FFF;">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="Dashbord.aspx">Dashbord Web</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li style="background:#000;">
                    <a><i class="fa fa-user">
                    <asp:Label ID="User" runat="server"></asp:Label>
                    </i> </a>
                    </li>
                        <li>
                            <a href="Logout.aspx"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                        
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav" id="menu">
                    <li class="active">
                        <a href="Dashbord.aspx">Dashbord</a>
                    </li>
                   <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Admin <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li class="active" style="background:#000;">
                                <a href="AddAdmin.aspx">Add Admin</a>
                            </li>
                            <li class="active" style="background:#000;">
                                <a href="EditAdmin.aspx">Edit Admin</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="javascript:;" data-toggle="collapse" data-target="#news"><i class="fa fa-fw fa-arrows-v"></i> Artikel <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="news" class="collapse">
                            <li class="active" style="background:#000;">
                                <a href="AddArtikel.aspx">Add Artikel</a>
                            </li>
                            <li class="active" style="background:#000;">
                                <a href="EditDeleteArtikel.aspx">Edit Delete Artikel</a>
                            </li class="active">
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">
                            <!-- Page Heading -->
                <div class="row">
                <div class="col-lg-12">
                    <div class="col-lg-4">
                        
                        
                        
<div class="popup-wrapper" id="popup">
 <div class="popup-container">
		<div class="popup-form">
                            <asp:TextBox ID="txtuser" placeholder="Username.." runat="server" Width="250px" CssClass="form-control"></asp:TextBox>
<asp:RequiredFieldValidator ID="RequiredFieldValidator2" ErrorMessage="Can not be empty!" ForeColor="Red" ControlToValidate="txtuser" runat="server" />
                           
                        <br />
                            <asp:TextBox ID="txtpswd1" type="password" placeholder="Password.." runat="server" Width="250px" CssClass="form-control"></asp:TextBox>
<asp:RequiredFieldValidator ID="RequiredFieldValidator3" ErrorMessage="Can not be empty!" ForeColor="Red" ControlToValidate="txtpswd1" runat="server" />
                           
                        <br />
                            <asp:TextBox ID="txtpswd2" type="password" placeholder="Re-Password.." runat="server" Width="250px" CssClass="form-control"></asp:TextBox>
                        <br />
                        <%--<asp:HiddenField runat="server" ID="txtId" />
                        <asp:Button class="btn btn-default" id="simpanKat" runat="server" Text="Simpan" OnClick="simpanKat_Click"/>--%>
                        <button type="reset" class="btn btn-default">Batal</button>
                        </div>
		<a class="popup-close" href="#closed"><a class="popup-close" href="AddAdmin.aspx">X</a></a>
	</div>
</div>
	

                </div>
                <div class="col-lg-6" style="margin-top:5px; margin-left:20px;">
            <h3>Admin Data</h3>
            <hr />
            <div>
            <asp:PlaceHolder ID="DataAdmin" runat="server"></asp:PlaceHolder>
            </div>
                    </div>
                </div>
            </div>
            </div>
            <!-- /.container-fluid -->
    
          </form>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>

    </form>
</body>
</html>
