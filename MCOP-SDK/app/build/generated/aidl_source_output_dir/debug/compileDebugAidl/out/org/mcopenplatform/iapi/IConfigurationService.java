/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\iapi\\IConfigurationService.aidl
 */
package org.mcopenplatform.iapi;
public interface IConfigurationService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.iapi.IConfigurationService
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.iapi.IConfigurationService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.iapi.IConfigurationService interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.iapi.IConfigurationService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.iapi.IConfigurationService))) {
return ((org.mcopenplatform.iapi.IConfigurationService)iin);
}
return new org.mcopenplatform.iapi.IConfigurationService.Stub.Proxy(obj);
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
case TRANSACTION_getErrorCode:
{
data.enforceInterface(descriptor);
int _result = this.getErrorCode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getErrorStr:
{
data.enforceInterface(descriptor);
java.lang.String _result = this.getErrorStr();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_registerNotificationReceiver:
{
data.enforceInterface(descriptor);
org.mcopenplatform.iapi.McopMessenger _arg0;
if ((0!=data.readInt())) {
_arg0 = org.mcopenplatform.iapi.McopMessenger.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.registerNotificationReceiver(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_checkCapability:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _result = this.checkCapability(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_checkCapabilityList:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String[] _result = this.checkCapabilityList(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_readConfigurationFile:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
byte[] _result = this.readConfigurationFile(_arg0, _arg1);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_writeConfigurationFile:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
byte[] _arg2;
_arg2 = data.createByteArray();
boolean _result = this.writeConfigurationFile(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_checkPackageAuth:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.checkPackageAuth(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_checkUidAuth:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.checkUidAuth(_arg0);
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
private static class Proxy implements org.mcopenplatform.iapi.IConfigurationService
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
@Override public int getErrorCode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getErrorCode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getErrorStr() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getErrorStr, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/*** Notifications *********************************************************//**
     * Register a messenger instance to receive notifications from the MCOP
     * service. Note that you may receive a notification even while this function
     * is still executing.
     *
     * <p><b>This function is platform-specific.</b> On Android, McopMessenger
     * is just a wrapped instance of Messenger. You must do the relevant setup to
     * receive messages yourself.
     *
     * @param m  Messenger instance to receive the notifications
     */
@Override public void registerNotificationReceiver(org.mcopenplatform.iapi.McopMessenger m) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((m!=null)) {
_data.writeInt(1);
m.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_registerNotificationReceiver, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int checkCapability(int cap) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(cap);
mRemote.transact(Stub.TRANSACTION_checkCapability, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String[] checkCapabilityList(int cap) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(cap);
mRemote.transact(Stub.TRANSACTION_checkCapabilityList, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/*** Configuration / provisioning data access ******************************//**
     * Read a configuration file from the SIM or from the ME (Mobile Equipment).
     *
     * @param   storage configuration file storage
     * @param   type    configuration file type
     * @return  an array of byte data, or null if not present
     * @see     Constants.Configuration.Storage
     * @see     Constants.Configuration.FileType
     */
@Override public byte[] readConfigurationFile(int storage, int type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(storage);
_data.writeInt(type);
mRemote.transact(Stub.TRANSACTION_readConfigurationFile, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Write a configuration file to the SIM or to the ME.
     *
     * @param   storage configuration file storage
     * @param   type    configuration file type
     * @param   data    an array of byte data to write
     * @return  true if write was successful, false otherwise
     * @see     Constants.Configuration.Storage
     * @see     Constants.Configuration.FileType
     */
@Override public boolean writeConfigurationFile(int storage, int type, byte[] data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(storage);
_data.writeInt(type);
_data.writeByteArray(data);
mRemote.transact(Stub.TRANSACTION_writeConfigurationFile, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/*** Security and authentication *******************************************//**
     * Check if the given package is trusted by ConfigurationService.
     *
     * @param  pkgname Full name of the package
     * @return true if trusted, false otherwise
     */
@Override public boolean checkPackageAuth(java.lang.String pkgname) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(pkgname);
mRemote.transact(Stub.TRANSACTION_checkPackageAuth, _data, _reply, 0);
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
     * Check if the given UID is trusted by ConfigurationService.
     *
     * @param  uid UID to check
     * @return true if trusted, false otherwise
     */
@Override public boolean checkUidAuth(int uid) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(uid);
mRemote.transact(Stub.TRANSACTION_checkUidAuth, _data, _reply, 0);
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
static final int TRANSACTION_getErrorCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getErrorStr = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerNotificationReceiver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_checkCapability = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_checkCapabilityList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_readConfigurationFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_writeConfigurationFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_checkPackageAuth = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_checkUidAuth = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
}
public int getErrorCode() throws android.os.RemoteException;
public java.lang.String getErrorStr() throws android.os.RemoteException;
/*** Notifications *********************************************************//**
     * Register a messenger instance to receive notifications from the MCOP
     * service. Note that you may receive a notification even while this function
     * is still executing.
     *
     * <p><b>This function is platform-specific.</b> On Android, McopMessenger
     * is just a wrapped instance of Messenger. You must do the relevant setup to
     * receive messages yourself.
     *
     * @param m  Messenger instance to receive the notifications
     */
public void registerNotificationReceiver(org.mcopenplatform.iapi.McopMessenger m) throws android.os.RemoteException;
public int checkCapability(int cap) throws android.os.RemoteException;
public java.lang.String[] checkCapabilityList(int cap) throws android.os.RemoteException;
/*** Configuration / provisioning data access ******************************//**
     * Read a configuration file from the SIM or from the ME (Mobile Equipment).
     *
     * @param   storage configuration file storage
     * @param   type    configuration file type
     * @return  an array of byte data, or null if not present
     * @see     Constants.Configuration.Storage
     * @see     Constants.Configuration.FileType
     */
public byte[] readConfigurationFile(int storage, int type) throws android.os.RemoteException;
/**
     * Write a configuration file to the SIM or to the ME.
     *
     * @param   storage configuration file storage
     * @param   type    configuration file type
     * @param   data    an array of byte data to write
     * @return  true if write was successful, false otherwise
     * @see     Constants.Configuration.Storage
     * @see     Constants.Configuration.FileType
     */
public boolean writeConfigurationFile(int storage, int type, byte[] data) throws android.os.RemoteException;
/*** Security and authentication *******************************************//**
     * Check if the given package is trusted by ConfigurationService.
     *
     * @param  pkgname Full name of the package
     * @return true if trusted, false otherwise
     */
public boolean checkPackageAuth(java.lang.String pkgname) throws android.os.RemoteException;
/**
     * Check if the given UID is trusted by ConfigurationService.
     *
     * @param  uid UID to check
     * @return true if trusted, false otherwise
     */
public boolean checkUidAuth(int uid) throws android.os.RemoteException;
}
