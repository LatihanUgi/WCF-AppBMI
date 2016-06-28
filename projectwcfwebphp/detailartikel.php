<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Artikel Detail | Hidup Sehat Dot Com</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/blog-post.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color:Green;">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php">Hidup Sehat Dot Com</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="index.php">Home</a>
                    </li>
                    <li>
                        <a href="about.php">About</a>
                    </li>
                    <li>
                        <a href="artikel.php">Artikel</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->

				<?php
				$url = "http://localhost/ProjectWCF/User.svc/GetArtikel/Detail/".$_GET['id']."";
				$json = file_get_contents($url);
				$obj = json_decode($json,TRUE);
				foreach($obj['GetArtikelResult'] as $page) {
					?>
                <!-- Title -->
                <h1><?php echo $page['Judul']; ?></h1>

                <br />
                <hr />
                <!-- Author -->

                <!-- Post Content -->
                <!-- Preview Image -->
                <img class="img-responsive" src="http://localhost/ProjectWCFWeb/photoartikel/<?php echo $page['Gambar'] ?>" style="height:300px !important; width:100% !important;" alt="">
				<p><?php echo $page['Tanggal']; ?></p>
				<p><?php echo $page['Artikel']; ?>
				
				<br>
				<p>Source : <a href="<?php echo $page['Source']; ?>" target="_blank"><?php echo $page['Source']; ?></a></p>
				<?php
					}
				?>


            </div>

            <!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">
                <!-- Blog Categories Well -->

                <!-- Side Widget Well -->
                <div class="well">
				<h3>New Artikel</h3>
				<hr>
				<?php
				$url = "http://localhost/ProjectWCF/User.svc/GetNewArtikel";
				$json = file_get_contents($url);
				$obj = json_decode($json,TRUE);
				foreach($obj['GetNewArtikelResult'] as $page) {
					?>
                <!-- Preview Image -->
                <img class="img-responsive" src="http://localhost/ProjectWCFWeb/photoartikel/<?php echo $page['Gambar'] ?>" style="height:100px !important; width:100% !important;" alt="">
                <h4><?php echo $page['Judul']; ?></h4>
				<p><?php echo $page['Tanggal']; ?></p>
                <a href="detailartikel.php?id=<?php echo $page['Id_Artikel']; ?>"><p>Lihat Selengkapnya</p></a>
                <hr>
				<?php
					}
				?>
                </div>

            </div>

        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
				<img src="images/logo.png" width="200px" height="50px"/>
                    <p>Copyright &copy; 2016 Hidup Sehat Dot Com</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>