/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\iapi\\IMBMSGroupCommListener.aidl
 */
package org.mcopenplatform.iapi;
public interface IMBMSGroupCommListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.iapi.IMBMSGroupCommListener
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.iapi.IMBMSGroupCommListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.iapi.IMBMSGroupCommListener interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.iapi.IMBMSGroupCommListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.iapi.IMBMSGroupCommListener))) {
return ((org.mcopenplatform.iapi.IMBMSGroupCommListener)iin);
}
return new org.mcopenplatform.iapi.IMBMSGroupCommListener.Stub.Proxy(obj);
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
case TRANSACTION_notifySaiList:
{
data.enforceInterface(descriptor);
int[] _arg0;
_arg0 = data.createIntArray();
this.notifySaiList(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_notifyCellInfo:
{
data.enforceInterface(descriptor);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.notifyCellInfo(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_notifyMBMSGroupCommAvailability:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.notifyMBMSGroupCommAvailability(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_notifyOpenMBMSGroupCommResult:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
this.notifyOpenMBMSGroupCommResult(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_notifyCloseMBMSGroupCommResult:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
int _arg1;
_arg1 = data.readInt();
this.notifyCloseMBMSGroupCommResult(_arg0, _arg1);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.iapi.IMBMSGroupCommListener
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
/* Received regularly */
@Override public void notifySaiList(int[] sai) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeIntArray(sai);
mRemote.transact(Stub.TRANSACTION_notifySaiList, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/* Received regularly */
@Override public void notifyCellInfo(int mcc, int mns, int eci) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(mcc);
_data.writeInt(mns);
_data.writeInt(eci);
mRemote.transact(Stub.TRANSACTION_notifyCellInfo, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/* Received when the group comm is monitored */
@Override public void notifyMBMSGroupCommAvailability(long tmgi, int available, int quality) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
_data.writeInt(available);
_data.writeInt(quality);
mRemote.transact(Stub.TRANSACTION_notifyMBMSGroupCommAvailability, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/* Received after a request for opening a group comm */
@Override public void notifyOpenMBMSGroupCommResult(long tmgi, int result, java.lang.String netInterfaceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
_data.writeInt(result);
_data.writeString(netInterfaceName);
mRemote.transact(Stub.TRANSACTION_notifyOpenMBMSGroupCommResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/* Received after a request for closing a group comm */
@Override public void notifyCloseMBMSGroupCommResult(long tmgi, int result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
_data.writeInt(result);
mRemote.transact(Stub.TRANSACTION_notifyCloseMBMSGroupCommResult, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_notifySaiList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_notifyCellInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_notifyMBMSGroupCommAvailability = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_notifyOpenMBMSGroupCommResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_notifyCloseMBMSGroupCommResult = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
/* Received regularly */
public void notifySaiList(int[] sai) throws android.os.RemoteException;
/* Received regularly */
public void notifyCellInfo(int mcc, int mns, int eci) throws android.os.RemoteException;
/* Received when the group comm is monitored */
public void notifyMBMSGroupCommAvailability(long tmgi, int available, int quality) throws android.os.RemoteException;
/* Received after a request for opening a group comm */
public void notifyOpenMBMSGroupCommResult(long tmgi, int result, java.lang.String netInterfaceName) throws android.os.RemoteException;
/* Received after a request for closing a group comm */
public void notifyCloseMBMSGroupCommResult(long tmgi, int result) throws android.os.RemoteException;
}
