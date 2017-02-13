library xpjni;

{$mode objfpc}{$H+}

uses
  Classes, sysutils, jni2, jni_utils, android;

function JNI_OnLoad(vm:PJavaVM;reserved:pointer):jint; stdcall;
begin
  LOGE('JNI_Load');
  Result := JNI_VERSION_1_6;
end;


function Java_com_rarnu_xpjni_demo_NativeMethod_callJniMethod(env: PJNIEnv; jobj: jobject; x: jint; y: jint): jint; stdcall;
begin
  Result := x + y;
end;

exports
  JNI_OnLoad,
  Java_com_rarnu_xpjni_demo_NativeMethod_callJniMethod;

begin
end.

