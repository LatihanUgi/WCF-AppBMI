using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Data.SqlClient;
using System.ServiceModel.Web;
using System.Collections;
using System.Data;
using System.Web.Configuration;
using System.Collections;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IServiceWeb" in both code and config file together.
[ServiceContract]
public interface IServiceWeb
{
	/*[OperationContract]
	int Login(string username, string password);

    [OperationContract]
    ArrayList TampilAdmin();

    [OperationContract]
    ArrayList DataAdmin();

    [OperationContract]
    int TambahAdmin(string user, string password, string statusadmin);*/

    /*[OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetAllArtikel")]
    List<DataArtikel> GetAllArtikel();*/
}

/*[DataContract]
public class DataArtikel
{

    int a_Id_Artikel = 0;
    string a_Judul = string.Empty;
    string a_Artikel = string.Empty;
    string a_Gambar = string.Empty;
    string a_Tanggal = string.Empty;
    string a_Source = string.Empty;

    // Record DataUser
    [DataMember]
    public int Id_Artikel
    {
        get { return a_Id_Artikel; }
        set { a_Id_Artikel = value; }
    }
    [DataMember]
    public string Judul
    {
        get { return a_Judul; }
        set { a_Judul = value; }
    }
    [DataMember]
    public string Artikel
    {
        get { return a_Artikel; }
        set { a_Artikel = value; }
    }
    [DataMember]
    public string Gambar
    {
        get { return a_Gambar; }
        set { a_Gambar = value; }
    }
    [DataMember]
    public string Tanggal
    {
        get { return a_Tanggal; }
        set { a_Tanggal = value; }
    }
    [DataMember]
    public string Source
    {
        get { return a_Source; }
        set { a_Source = value; }
    }
}*/
