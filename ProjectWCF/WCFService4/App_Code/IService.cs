using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Data.SqlClient;
using System.Data;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService" in both code and config file together.
[ServiceContract]
public interface IService
{
    [OperationContract]
    int cekidadmin(string id);

    [OperationContract]
    string[] UserByID(string username);

    [OperationContract]
    int UpdateUser(string username, string password, string fullname, string dob, string gender, string phone, string email, string doj);

    [OperationContract]
    void DeleteUser(string username);

    [OperationContract]
    DataSet SemuaDataUser();

    [OperationContract]
    DataSet DataUser(string username);

    [OperationContract]
    string[] RecordByID(string id);

    [OperationContract]
    void UpdateRecord(string id, string username, string gender, string height, string weight, string status, string date);

    [OperationContract]
    void DeleteRecord(string id);

    [OperationContract]
    DataSet SemuaDataRecord();

    [OperationContract]
    DataSet DataRecord(string id);

    [OperationContract]
    string[] AdminByID(string id);

    [OperationContract]
    void UpdateAdmin(string id, string admin, string password);

    [OperationContract]
    void DeleteAdmin(string id);

    [OperationContract]
    DataSet SemuaDataAdmin();

    [OperationContract]
    DataSet DataAdmin(string id);

    [OperationContract]
    string AutoIDAdmin();

    [OperationContract]
    void AddAdmin(string admin, string password);

    [OperationContract]
    int login(string user, string pswd);
    // TODO: Add your service operations here
}

