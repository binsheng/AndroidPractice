#include <jni.h>
#include <string>
extern "C"
jint
Java_com_dev_bins_jniwithcmake_MainActivity_add(JNIEnv *env, jobject instance, jint x, jint y) {
    return x+y;
}

extern "C"
jstring
Java_com_dev_bins_jniwithcmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "JniWithCMake";
    return env->NewStringUTF(hello.c_str());
}
