/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\iapi\\IMBMSGroupComm.aidl
 */
package org.mcopenplatform.iapi;
public interface IMBMSGroupComm extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.iapi.IMBMSGroupComm
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.iapi.IMBMSGroupComm";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.iapi.IMBMSGroupComm interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.iapi.IMBMSGroupComm asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.iapi.IMBMSGroupComm))) {
return ((org.mcopenplatform.iapi.IMBMSGroupComm)iin);
}
return new org.mcopenplatform.iapi.IMBMSGroupComm.Stub.Proxy(obj);
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
case TRANSACTION_registerApplication:
{
data.enforceInterface(descriptor);
org.mcopenplatform.iapi.IMBMSGroupCommListener _arg0;
_arg0 = org.mcopenplatform.iapi.IMBMSGroupCommListener.Stub.asInterface(data.readStrongBinder());
this.registerApplication(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startMBMSGroupCommMonitoring:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
int[] _arg1;
_arg1 = data.createIntArray();
int[] _arg2;
_arg2 = data.createIntArray();
int _arg3;
_arg3 = data.readInt();
this.startMBMSGroupCommMonitoring(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_stopMBMSGroupCommMonitoring:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
this.stopMBMSGroupCommMonitoring(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_openGroupComm:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
int[] _arg1;
_arg1 = data.createIntArray();
int[] _arg2;
_arg2 = data.createIntArray();
this.openGroupComm(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_closeGroupComm:
{
data.enforceInterface(descriptor);
long _arg0;
_arg0 = data.readLong();
this.closeGroupComm(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.iapi.IMBMSGroupComm
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
@Override public void registerApplication(org.mcopenplatform.iapi.IMBMSGroupCommListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerApplication, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startMBMSGroupCommMonitoring(long tmgi, int[] sai, int[] frequencies, int qci) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
_data.writeIntArray(sai);
_data.writeIntArray(frequencies);
_data.writeInt(qci);
mRemote.transact(Stub.TRANSACTION_startMBMSGroupCommMonitoring, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopMBMSGroupCommMonitoring(long tmgi) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
mRemote.transact(Stub.TRANSACTION_stopMBMSGroupCommMonitoring, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void openGroupComm(long tmgi, int[] sai, int[] frequencies) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
_data.writeIntArray(sai);
_data.writeIntArray(frequencies);
mRemote.transact(Stub.TRANSACTION_openGroupComm, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void closeGroupComm(long tmgi) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(tmgi);
mRemote.transact(Stub.TRANSACTION_closeGroupComm, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerApplication = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_startMBMSGroupCommMonitoring = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_stopMBMSGroupCommMonitoring = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_openGroupComm = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_closeGroupComm = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public void registerApplication(org.mcopenplatform.iapi.IMBMSGroupCommListener listener) throws android.os.RemoteException;
public void startMBMSGroupCommMonitoring(long tmgi, int[] sai, int[] frequencies, int qci) throws android.os.RemoteException;
public void stopMBMSGroupCommMonitoring(long tmgi) throws android.os.RemoteException;
public void openGroupComm(long tmgi, int[] sai, int[] frequencies) throws android.os.RemoteException;
public void closeGroupComm(long tmgi) throws android.os.RemoteException;
}
