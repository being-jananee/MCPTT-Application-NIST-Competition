/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Jithin\\Desktop\\MCOP-SDK\\app\\src\\main\\aidl\\org\\mcopenplatform\\muoapi\\IMCOPCallback.aidl
 */
package org.mcopenplatform.muoapi;
/**
 * AIDL definition {@link https://developer.android.com/guide/components/aidl.html}
 * Used as a callback for MCOP SDK server-client communication, and for MCPTT (Mission Critical Push to Talk) Services.
 * @version 0.1
 */
public interface IMCOPCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mcopenplatform.muoapi.IMCOPCallback
{
private static final java.lang.String DESCRIPTOR = "org.mcopenplatform.muoapi.IMCOPCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mcopenplatform.muoapi.IMCOPCallback interface,
 * generating a proxy if needed.
 */
public static org.mcopenplatform.muoapi.IMCOPCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mcopenplatform.muoapi.IMCOPCallback))) {
return ((org.mcopenplatform.muoapi.IMCOPCallback)iin);
}
return new org.mcopenplatform.muoapi.IMCOPCallback.Stub.Proxy(obj);
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
case TRANSACTION_handleOnEvent:
{
data.enforceInterface(descriptor);
java.util.List<android.content.Intent> _arg0;
_arg0 = data.createTypedArrayList(android.content.Intent.CREATOR);
this.handleOnEvent(_arg0);
reply.writeNoException();
return true;
}
default:
{
return super.onTransact(code, data, reply, flags);
}
}
}
private static class Proxy implements org.mcopenplatform.muoapi.IMCOPCallback
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
/**
     *
     * Callback to be listened to by the client. It provides active events, responses to methods
     * executed by the client, asynchronous events from the MCPTT system, and errors produced.
     *
     * @return
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack (Types of actions that each of the callback events can have)
     * @param actionList Intent list. Each component in the list contains an event.
     */
@Override public void handleOnEvent(java.util.List<android.content.Intent> actionList) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeTypedList(actionList);
mRemote.transact(Stub.TRANSACTION_handleOnEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_handleOnEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     *
     * Callback to be listened to by the client. It provides active events, responses to methods
     * executed by the client, asynchronous events from the MCPTT system, and errors produced.
     *
     * @return
     * @see org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack org.mcopenplatform.muoapi.ConstantsMCOP.ActionsCallBack (Types of actions that each of the callback events can have)
     * @param actionList Intent list. Each component in the list contains an event.
     */
public void handleOnEvent(java.util.List<android.content.Intent> actionList) throws android.os.RemoteException;
}
