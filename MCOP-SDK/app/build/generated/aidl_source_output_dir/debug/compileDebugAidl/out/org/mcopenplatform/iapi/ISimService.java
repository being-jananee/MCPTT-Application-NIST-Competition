/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\iapi\\ISimService.aidl
 */
package org.mcopenplatform.iapi;
public interface ISimService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.iapi.ISimService
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.iapi.ISimService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.iapi.ISimService interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.iapi.ISimService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.iapi.ISimService))) {
return ((org.mcopenplatform.iapi.ISimService)iin);
}
return new org.mcopenplatform.iapi.ISimService.Stub.Proxy(obj);
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
case TRANSACTION_getDeviceIdentity:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getDeviceIdentity(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getSubscriberIdentity:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getSubscriberIdentity(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getImpi:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getImpi(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getImpu:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String[] _result = this.getImpu(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getDomain:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getDomain(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getPcscf:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
java.lang.String[] _result = this.getPcscf(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getPcscfPco:
{
data.enforceInterface(descriptor);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _result = this.getPcscfPco(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getAuthentication:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
java.lang.String _result = this.getAuthentication(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
reply.writeString(_result);
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.iapi.ISimService
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
@Override public java.lang.String getDeviceIdentity(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getDeviceIdentity, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getSubscriberIdentity(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getSubscriberIdentity, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getImpi(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getImpi, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String[] getImpu(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getImpu, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getDomain(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getDomain, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String[] getPcscf(int slotId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
mRemote.transact(Stub.TRANSACTION_getPcscf, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String[] getPcscfPco(java.lang.String type) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(type);
mRemote.transact(Stub.TRANSACTION_getPcscfPco, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getAuthentication(int slotId, int appType, int authType, java.lang.String data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(slotId);
_data.writeInt(appType);
_data.writeInt(authType);
_data.writeString(data);
mRemote.transact(Stub.TRANSACTION_getAuthentication, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
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
static final int TRANSACTION_getDeviceIdentity = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getSubscriberIdentity = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getImpi = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getImpu = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getDomain = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getPcscf = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getPcscfPco = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getAuthentication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
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
public java.lang.String getDeviceIdentity(int slotId) throws android.os.RemoteException;
public java.lang.String getSubscriberIdentity(int slotId) throws android.os.RemoteException;
public java.lang.String getImpi(int slotId) throws android.os.RemoteException;
public java.lang.String[] getImpu(int slotId) throws android.os.RemoteException;
public java.lang.String getDomain(int slotId) throws android.os.RemoteException;
public java.lang.String[] getPcscf(int slotId) throws android.os.RemoteException;
public java.lang.String[] getPcscfPco(java.lang.String type) throws android.os.RemoteException;
public java.lang.String getAuthentication(int slotId, int appType, int authType, java.lang.String data) throws android.os.RemoteException;
}
