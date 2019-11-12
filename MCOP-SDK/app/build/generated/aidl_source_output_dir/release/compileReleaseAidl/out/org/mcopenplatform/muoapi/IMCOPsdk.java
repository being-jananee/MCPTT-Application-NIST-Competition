/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\muoapi\\IMCOPsdk.aidl
 */
package org.mcopenplatform.muoapi;
/**
 * AIDL definition {@link https://developer.android.com/guide/components/aidl.html}
 * Used as a callback for MCOP SDK server-client communication, and for MCPTT (Mission Critical Push to Talk) Services.
 * @version 0.1
 */
public interface IMCOPsdk extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.muoapi.IMCOPsdk
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.muoapi.IMCOPsdk";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.muoapi.IMCOPsdk interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.muoapi.IMCOPsdk asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.muoapi.IMCOPsdk))) {
return ((org.mcopenplatform.muoapi.IMCOPsdk)iin);
}
return new org.mcopenplatform.muoapi.IMCOPsdk.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
java.lang.String descriptor = DESCRIPTOR;
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(descriptor);
return true;
}
case TRANSACTION_getMCOPCapabilities:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getMCOPCapabilities();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_loginMCOP:
{
data.enforceInterface(descriptor);
boolean _result = this.loginMCOP();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unLoginMCOP:
{
data.enforceInterface(descriptor);
boolean _result = this.unLoginMCOP();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_authorizeUser:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.authorizeUser(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_makeCall:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.makeCall(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_hangUpCall:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.hangUpCall(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_acceptCall:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.acceptCall(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_updateEmergencyState:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.updateEmergencyState(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_floorControlOperation:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
boolean _result = this.floorControlOperation(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_updateGroupsInfo:
{
data.enforceInterface(descriptor);
boolean _result = this.updateGroupsInfo();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_updateGroupsAffiliation:
{
data.enforceInterface(descriptor);
boolean _result = this.updateGroupsAffiliation();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_groupAffiliationOperation:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.groupAffiliationOperation(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_changeSelectedContact:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.changeSelectedContact(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(descriptor);
org.mcopenplatform.muoapi.IMCOPCallback _arg0;
_arg0 = org.mcopenplatform.muoapi.IMCOPCallback.Stub.asInterface(data.readStrongBinder());
boolean _result = this.registerCallback(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.muoapi.IMCOPsdk
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getMCOPCapabilities() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getMCOPCapabilities, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
         * This method starts the MCPTT System Login procedure.
         * No input parameters.
         * In case of re-execution it restarts the login procedure.
         *
         * @return  Indicates if the Login procedure can be started.
         * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method)
         * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
         *
         */
@Override public boolean loginMCOP() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_loginMCOP, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * This method starts the MCPTT System unLogin procedure.
     * No input parameters.
     * In case of re-execution it restarts the unlogin procedure.
     *
     * @return  Indicates if the unLogin procedure can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
     *
     */
@Override public boolean unLoginMCOP() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_unLoginMCOP, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Once the authentication procedure has been carried out against a third party,
     * this method must be called to continue with the Login process started
     * with loginMCOP() method, defined in this AIDL.
     *
     * Before making the call to this method, the client must use the "request uri", obtained in the callback "org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack.authorizationRequestEvent",
     * to authenticate against a third party, so that the SDK can verify the authentication with the "url" input parameter.
     *
     * @return  Indicates if the Login procedure can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method and the errors)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
     * @param url. String with URL format where the parameters are indicated so that the SDK can authenticate the user.
     */
@Override public boolean authorizeUser(java.lang.String url) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(url);
mRemote.transact(Stub.TRANSACTION_authorizeUser, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     *
     * Method to make MCPTT type calls. Allowed call types are limited.
     * MCPTT services that a call can contain are listed in {@link org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum}
     *
     * @return Indicates whether the make call procedure could be started. In case of invalid MCPTT session, FALSE is returned.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum (Definition of the MCPTT Services a call may contain)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.CallEventExtras org.mcopenplatform.muoapi.ConstantsMCOP.CallEventExtras (Definition of the different asynchronous responses this method can have through the callback)
     * @param userID. String to identify the MCOP system user or group to call. If no identity is indicated, the selected contact will be called.
     * @param callType. Integer that contains a series of FLAGs indicating the services for the new MCPTT session.
     */
@Override public boolean makeCall(java.lang.String userID, int callType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userID);
_data.writeInt(callType);
mRemote.transact(Stub.TRANSACTION_makeCall, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Hang up an incoming, established or initiated MCPTT session.
     * @param sessionID. Identifier of the specific MCPTT session where the specific action is carried out.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
@Override public boolean hangUpCall(java.lang.String sessionID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(sessionID);
mRemote.transact(Stub.TRANSACTION_hangUpCall, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Accept an incoming MCPTT sesion.
     * @param sessionID. Identifier of the specific MCPTT session where the specific action is carried out.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
@Override public boolean acceptCall(java.lang.String sessionID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(sessionID);
mRemote.transact(Stub.TRANSACTION_acceptCall, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Update the emergency state of a particular session.
     * @param sessionID. Identifier of the specific MCPTT session to be updated.
     * @param emergencyType. New emergency state of the session. It must be type {@link org.mcopenplatform.muoapi.ConstantsMCOP.EmergencyTypeEnum}
     * @return Indicates whether the desired operation can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.EmergencyTypeEnum
     * @throws android.os.RemoteException
     */
@Override public boolean updateEmergencyState(java.lang.String sessionID, int emergencyType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(sessionID);
_data.writeInt(emergencyType);
mRemote.transact(Stub.TRANSACTION_updateEmergencyState, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Perform floor control operations over MCPTT sessions.
     * @param sessionID Identifier of the specific MCPTT session where the specific action is carried out.
     * @param requestType Type of action to be performed. It must be type {@link org.mcopenplatform.muoapi.ConstantsMCOP.FloorControlEventExtras.FloorControlOperationTypeEnum}
     * @param UserID To be used in Video sessions, indicating the specific user to receive video from.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
@Override public boolean floorControlOperation(java.lang.String sessionID, int requestType, java.lang.String UserID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(sessionID);
_data.writeInt(requestType);
_data.writeString(UserID);
mRemote.transact(Stub.TRANSACTION_floorControlOperation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Requests group information to the SDK,
     * generating a type {@link org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack#groupInfoEvent} event callback.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
@Override public boolean updateGroupsInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_updateGroupsInfo, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Requests group affiliation information to the SDK,
     * generating a type {@link org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack#groupAffiliationEvent} event callback.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
@Override public boolean updateGroupsAffiliation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_updateGroupsAffiliation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Perform a group affiliation operation.
     * @param groupMcpttId. Unequivocally identifies the group on which the operation is carried out. The group list will be received in the event {@link ConstantsMCOP.ActionsCallBack#groupAffiliationEvent}
     * @param affiliationOperation. Operation to perform on the indicated group. Allowed actions: {@link org.mcopenplatform.muoapi.ConstantsMCOP.GroupAffiliationEventExtras.AffiliationOperationTypeEnum}.
     * @return Indicates whether the desired operation can be started.
     * @See org.mcopenplatform.muoapi.ConstantsMCOP.GroupAffiliationEventExtras
     * @throws android.os.RemoteException
     */
@Override public boolean groupAffiliationOperation(java.lang.String groupMcpttId, int affiliationOperation) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(groupMcpttId);
_data.writeInt(affiliationOperation);
mRemote.transact(Stub.TRANSACTION_groupAffiliationOperation, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Select default contact (user or group).
     * @param groupID. Unequivocal identifier of the MCPTT group defined for default MCPTT sessions.
     * @return Indicates whether the desired operation can be started.
     * @See org.mcopenplatform.muoapi.ConstantsMCOP.SelectedContactChangeEventExtras
     * @throws android.os.RemoteException
     */
@Override public boolean changeSelectedContact(java.lang.String groupID) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(groupID);
mRemote.transact(Stub.TRANSACTION_changeSelectedContact, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Register the callback defined by AIDL so that the SDK can send asynchronous events to the user.
     * @param mcopCallBack. Callback where the different events are sent.
     * @return Indicates whether the desired operation can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack
     * @throws android.os.RemoteException
     */
@Override public boolean registerCallback(org.mcopenplatform.muoapi.IMCOPCallback mcopCallBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((mcopCallBack!=null))?(mcopCallBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getMCOPCapabilities = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_loginMCOP = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unLoginMCOP = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_authorizeUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_makeCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_hangUpCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_acceptCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_updateEmergencyState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_floorControlOperation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_updateGroupsInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_updateGroupsAffiliation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_groupAffiliationOperation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_changeSelectedContact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
}
public java.lang.String getMCOPCapabilities() throws android.os.RemoteException;
/**
         * This method starts the MCPTT System Login procedure.
         * No input parameters.
         * In case of re-execution it restarts the login procedure.
         *
         * @return  Indicates if the Login procedure can be started.
         * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method)
         * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
         *
         */
public boolean loginMCOP() throws android.os.RemoteException;
/**
     * This method starts the MCPTT System unLogin procedure.
     * No input parameters.
     * In case of re-execution it restarts the unlogin procedure.
     *
     * @return  Indicates if the unLogin procedure can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
     *
     */
public boolean unLoginMCOP() throws android.os.RemoteException;
/**
     * Once the authentication procedure has been carried out against a third party,
     * this method must be called to continue with the Login process started
     * with loginMCOP() method, defined in this AIDL.
     *
     * Before making the call to this method, the client must use the "request uri", obtained in the callback "org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack.authorizationRequestEvent",
     * to authenticate against a third party, so that the SDK can verify the authentication with the "url" input parameter.
     *
     * @return  Indicates if the Login procedure can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras (Definition of all the asynchronous response values of this method and the errors)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.AuthorizationRequestExtras org.mcopenplatform.muoapi.ConstantsMCOP.LoginEventExtras (Definition of all the asynchronous response values of this method and the errors)
     * @param url. String with URL format where the parameters are indicated so that the SDK can authenticate the user.
     */
public boolean authorizeUser(java.lang.String url) throws android.os.RemoteException;
/**
     *
     * Method to make MCPTT type calls. Allowed call types are limited.
     * MCPTT services that a call can contain are listed in {@link org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum}
     *
     * @return Indicates whether the make call procedure could be started. In case of invalid MCPTT session, FALSE is returned.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum org.mcopenplatform.muoapi.ConstantsMCOP.CallTypeEnum (Definition of the MCPTT Services a call may contain)
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.CallEventExtras org.mcopenplatform.muoapi.ConstantsMCOP.CallEventExtras (Definition of the different asynchronous responses this method can have through the callback)
     * @param userID. String to identify the MCOP system user or group to call. If no identity is indicated, the selected contact will be called.
     * @param callType. Integer that contains a series of FLAGs indicating the services for the new MCPTT session.
     */
public boolean makeCall(java.lang.String userID, int callType) throws android.os.RemoteException;
/**
     * Hang up an incoming, established or initiated MCPTT session.
     * @param sessionID. Identifier of the specific MCPTT session where the specific action is carried out.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
public boolean hangUpCall(java.lang.String sessionID) throws android.os.RemoteException;
/**
     * Accept an incoming MCPTT sesion.
     * @param sessionID. Identifier of the specific MCPTT session where the specific action is carried out.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
public boolean acceptCall(java.lang.String sessionID) throws android.os.RemoteException;
/**
     * Update the emergency state of a particular session.
     * @param sessionID. Identifier of the specific MCPTT session to be updated.
     * @param emergencyType. New emergency state of the session. It must be type {@link org.mcopenplatform.muoapi.ConstantsMCOP.EmergencyTypeEnum}
     * @return Indicates whether the desired operation can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.EmergencyTypeEnum
     * @throws android.os.RemoteException
     */
public boolean updateEmergencyState(java.lang.String sessionID, int emergencyType) throws android.os.RemoteException;
/**
     * Perform floor control operations over MCPTT sessions.
     * @param sessionID Identifier of the specific MCPTT session where the specific action is carried out.
     * @param requestType Type of action to be performed. It must be type {@link org.mcopenplatform.muoapi.ConstantsMCOP.FloorControlEventExtras.FloorControlOperationTypeEnum}
     * @param UserID To be used in Video sessions, indicating the specific user to receive video from.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
public boolean floorControlOperation(java.lang.String sessionID, int requestType, java.lang.String UserID) throws android.os.RemoteException;
/**
     * Requests group information to the SDK,
     * generating a type {@link org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack#groupInfoEvent} event callback.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
public boolean updateGroupsInfo() throws android.os.RemoteException;
/**
     * Requests group affiliation information to the SDK,
     * generating a type {@link org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack#groupAffiliationEvent} event callback.
     * @return Indicates whether the desired operation can be started.
     * @throws android.os.RemoteException
     */
public boolean updateGroupsAffiliation() throws android.os.RemoteException;
/**
     * Perform a group affiliation operation.
     * @param groupMcpttId. Unequivocally identifies the group on which the operation is carried out. The group list will be received in the event {@link ConstantsMCOP.ActionsCallBack#groupAffiliationEvent}
     * @param affiliationOperation. Operation to perform on the indicated group. Allowed actions: {@link org.mcopenplatform.muoapi.ConstantsMCOP.GroupAffiliationEventExtras.AffiliationOperationTypeEnum}.
     * @return Indicates whether the desired operation can be started.
     * @See org.mcopenplatform.muoapi.ConstantsMCOP.GroupAffiliationEventExtras
     * @throws android.os.RemoteException
     */
public boolean groupAffiliationOperation(java.lang.String groupMcpttId, int affiliationOperation) throws android.os.RemoteException;
/**
     * Select default contact (user or group).
     * @param groupID. Unequivocal identifier of the MCPTT group defined for default MCPTT sessions.
     * @return Indicates whether the desired operation can be started.
     * @See org.mcopenplatform.muoapi.ConstantsMCOP.SelectedContactChangeEventExtras
     * @throws android.os.RemoteException
     */
public boolean changeSelectedContact(java.lang.String groupID) throws android.os.RemoteException;
/**
     * Register the callback defined by AIDL so that the SDK can send asynchronous events to the user.
     * @param mcopCallBack. Callback where the different events are sent.
     * @return Indicates whether the desired operation can be started.
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack
     * @throws android.os.RemoteException
     */
public boolean registerCallback(org.mcopenplatform.muoapi.IMCOPCallback mcopCallBack) throws android.os.RemoteException;
}
