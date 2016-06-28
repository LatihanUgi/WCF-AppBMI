using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;

namespace BMI_Application
{
    class Koneksi
    {
        public SqlConnection BukaKoneksi()
        {
            SqlConnection sqlcon = new SqlConnection(@"Integrated Security=true;Initial Catalog=BMI;Data Source=ASUS\USERR");
            return sqlcon;
        }
    }
}
