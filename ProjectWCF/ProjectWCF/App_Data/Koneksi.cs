using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;

/// <summary>
/// Summary description for Koneksi
/// </summary>
public class Koneksi
{
        public System.Data.SqlClient.SqlConnection con()
        {
            SqlConnection connection = new SqlConnection("Data Source = JAM5\AREUS_STUDIO; Initial Catalog = Jam5; User ID = sa; Password = Sahabatjam5");
            return connection;
        }
}