/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\iapi\\IConnectivityService.aidl
 */
package org.mcopenplatform.iapi;
public interface IConnectivityService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.iapi.IConnectivityService
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.iapi.IConnectivityService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.iapi.IConnectivityService interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.iapi.IConnectivityService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.iapi.IConnectivityService))) {
return ((org.mcopenplatform.iapi.IConnectivityService)iin);
}
return new org.mcopenplatform.iapi.IConnectivityService.Stub.Proxy(obj);
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
case TRANSACTION_createAPN:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.createAPN(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_deleteAPN:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.deleteAPN(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_activateAPN:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.activateAPN(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_blockNonMCCalls:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
this.blockNonMCCalls(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.iapi.IConnectivityService
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
/*** Connectivity **********************************************************//**
     * Create a MC PTT APN. If the APN can not be created for any reason
     * an error is returned. You can query the list of acceptable APN
     * types via checkCapabilityList.
     *
     * <p>If your program crashes or for some other reason disconnects from the
     * underlying MCOP service all created APNs will be deleted automatically.
     *
     * <p>Note that this call may take some time, so do not call from a thread
     * that can not afford to block.
     *
     * @param  name  Name of the new APN
     * @param  type  Type of the new APN, f.ex. "ims"
     * @return       true if creation was successful, false otherwise
     */
@Override public boolean createAPN(java.lang.String name, java.lang.String type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(type);
mRemote.transact(Stub.TRANSACTION_createAPN, _data, _reply, 0);
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
     * Delete a previously created MC PTT APN. You can only delete APNs that
     * you previously have created via createAPN.
     *
     * @param  name  Name of APN to delete
     * @return       true if APN was deleted, false otherwise
     */
@Override public boolean deleteAPN(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_deleteAPN, _data, _reply, 0);
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
     * Activate a previously created MC PTT APN. After this call returns
     * successfully the APN will be fully established and ready to carry data.
     *
     * @param  name  Name of the APN to activate
     * @return       true if APN was succesfully activated, false otherwise
     */
@Override public boolean activateAPN(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_activateAPN, _data, _reply, 0);
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
     * Control what non-MC calls are allowed. If your application disconnects
     * from the MCOP service the state is automatically set to NONE.
     *
     * @param block  One of the BLOCK_CALLS integer constants.
     * @see   Constants.Connectivity.CallBlock
     */
@Override public void blockNonMCCalls(int block) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(block);
mRemote.transact(Stub.TRANSACTION_blockNonMCCalls, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getErrorCode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getErrorStr = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerNotificationReceiver = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_checkCapability = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_checkCapabilityList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_createAPN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_deleteAPN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_activateAPN = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_blockNonMCCalls = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
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
/*** Connectivity **********************************************************//**
     * Create a MC PTT APN. If the APN can not be created for any reason
     * an error is returned. You can query the list of acceptable APN
     * types via checkCapabilityList.
     *
     * <p>If your program crashes or for some other reason disconnects from the
     * underlying MCOP service all created APNs will be deleted automatically.
     *
     * <p>Note that this call may take some time, so do not call from a thread
     * that can not afford to block.
     *
     * @param  name  Name of the new APN
     * @param  type  Type of the new APN, f.ex. "ims"
     * @return       true if creation was successful, false otherwise
     */
public boolean createAPN(java.lang.String name, java.lang.String type) throws android.os.RemoteException;
/**
     * Delete a previously created MC PTT APN. You can only delete APNs that
     * you previously have created via createAPN.
     *
     * @param  name  Name of APN to delete
     * @return       true if APN was deleted, false otherwise
     */
public boolean deleteAPN(java.lang.String name) throws android.os.RemoteException;
/**
     * Activate a previously created MC PTT APN. After this call returns
     * successfully the APN will be fully established and ready to carry data.
     *
     * @param  name  Name of the APN to activate
     * @return       true if APN was succesfully activated, false otherwise
     */
public boolean activateAPN(java.lang.String name) throws android.os.RemoteException;
/**
     * Control what non-MC calls are allowed. If your application disconnects
     * from the MCOP service the state is automatically set to NONE.
     *
     * @param block  One of the BLOCK_CALLS integer constants.
     * @see   Constants.Connectivity.CallBlock
     */
public void blockNonMCCalls(int block) throws android.os.RemoteException;
}
