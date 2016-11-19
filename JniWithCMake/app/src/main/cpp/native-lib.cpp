#include <jni.h>
#include <string>

extern "C" {

JNIEXPORT jint JNICALL
Java_com_dev_bins_jniwithcmake_MainActivity_add(JNIEnv *env, jobject instance, jint x, jint y) {
    return x + y;
}

jstring
Java_com_dev_bins_jniwithcmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "JniWithCMake";
    return env->NewStringUTF(hello.c_str());
}

}


