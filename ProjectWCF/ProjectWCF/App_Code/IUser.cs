using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.ServiceModel.Web;
using System.Collections;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IUser" in both code and config file together.
[ServiceContract]
public interface IUser
{
    //To Insert or POST Records  
    //[OperationContract()]
    //[WebInvoke(UriTemplate = "/AddUser/username/gender/fullname/dob/phonenumber/email/password/doj", Method = "POST", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json, BodyStyle = WebMessageBodyStyle.Wrapped)]
    /*[FaultContract(typeof(IUser))]
    [WebGet(UriTemplate="/AddUser/{username}/{gender}/{fullname}/{dob}/{phonenumber}/{email}/{password}/{doj}", ResponseFormat=WebMessageFormat.Json)]
    int AddUser(string username, string gender, string fullname, string dob, string phonenumber, string email, string password, string doj);*/

    /*[OperationContract]
    [WebGet(ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "/AddUser/username={username}&gender={gender}&fullname={fullname}&dob={dob}&phonenumber={phonenumber}&email={email}&password={password}&doj={doj}")]
    int AddUser(string username, string gender, string fullname, string dob, string phonenumber, string email, string password, string doj);*/

    [OperationContract]
    [WebInvoke(Method = "GET",
        BodyStyle = WebMessageBodyStyle.Wrapped,
        ResponseFormat = WebMessageFormat.Json,
        RequestFormat = WebMessageFormat.Json,
        UriTemplate = "AddUser/{username}/{gender}/{fullname}/{dob}/{phonenumber}/{email}/{password}/{doj}")]
    string AddUser(string username, string gender, string fullname, string dob, string phonenumber, string email, string password, string doj);

    [OperationContract]
    [WebInvoke(Method = "GET",
        BodyStyle = WebMessageBodyStyle.Wrapped,
        ResponseFormat = WebMessageFormat.Json,
        RequestFormat = WebMessageFormat.Json,
        UriTemplate = "AddRecord/{username}/{status}/{bbi}/{height}/{weight}/{date}")]
    string AddRecord(string username, string status, string bbi, string height, string weight, string date);
    /*[OperationContract]
        [WebInvoke(Method = "GET",
        BodyStyle = WebMessageBodyStyle.Wrapped,
        RequestFormat = WebMessageFormat.Json, 
        ResponseFormat = WebMessageFormat.Json,
        UriTemplate = "Login/{username}/{password}")]
     string Login(string username,string password);*/


    [OperationContract]
    [WebInvoke(Method = "GET",
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "Login?username={username}&password={password}")]
    string Login(string username, string password);

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetRecord/{username}")]
    List<Record> GetRecord(string username);

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetUser/{username}")]
    List<DataUser> GetUser(string username);

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetAllUser")]
    List<DataUser> GetAllUser();

    /*[OperationContract]
    [WebInvoke(Method = "GET", 
    //BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json, 
    ResponseFormat = WebMessageFormat.Json, 
    UriTemplate = "GetUser/{username}")]
    void GetUser(string username);*/

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetArtikel/Detail/{id}")]
    List<DataArtikel> GetArtikel(string id);

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetAllArtikel")]
    List<DataArtikel> GetAllArtikel();

    [OperationContract]
    [WebInvoke(Method = "GET",
    BodyStyle = WebMessageBodyStyle.Wrapped,
    RequestFormat = WebMessageFormat.Json,
    ResponseFormat = WebMessageFormat.Json,
    UriTemplate = "GetNewArtikel")]
    List<DataArtikel> GetNewArtikel();
}

[DataContract]
public class Record
{
    string r_Status = string.Empty;
    string r_Bbi = string.Empty;
    string r_Tinggi = string.Empty;
    string r_Berat = string.Empty;
    string r_Tanggal = string.Empty;

    //Record BMI User
    [DataMember]
    public string Status
    {
        get { return r_Status; }
        set { r_Status = value; }
    }
    [DataMember]
    public string Bbi
    {
        get { return r_Bbi; }
        set { r_Bbi = value; }
    }
    [DataMember]
    public string Tinggi
    {
        get { return r_Tinggi; }
        set { r_Tinggi = value; }
    }
    [DataMember]
    public string Berat
    {
        get { return r_Berat; }
        set { r_Berat = value; }
    }
    [DataMember]
    public string Tanggal
    {
        get { return r_Tanggal; }
        set { r_Tanggal = value; }
    }


}

[DataContract]
public class DataUser
{

    string u_Username = string.Empty;
    string u_Gender = string.Empty;
    string u_Fullname = string.Empty;
    string u_Dob = string.Empty;
    string u_Phonenumber = string.Empty;
    string u_Email = string.Empty;
    string u_Password = string.Empty;

    // Record DataUser
    [DataMember]
    public string Username
    {
        get { return u_Username; }
        set { u_Username = value; }
    }
    [DataMember]
    public string Gender
    {
        get { return u_Gender; }
        set { u_Gender = value; }
    }
    [DataMember]
    public string Fullname
    {
        get { return u_Fullname; }
        set { u_Fullname = value; }
    }
    [DataMember]
    public string Dob
    {
        get { return u_Dob; }
        set { u_Dob = value; }
    }
    [DataMember]
    public string Phonenumber
    {
        get { return u_Phonenumber; }
        set { u_Phonenumber = value; }
    }
    [DataMember]
    public string Email
    {
        get { return u_Email; }
        set { u_Email = value; }
    }
    [DataMember]
    public string Password
    {
        get { return u_Password; }
        set { u_Password = value; }
    }
}

[DataContract]
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
}
