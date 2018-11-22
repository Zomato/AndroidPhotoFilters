LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := NativeImageProcessor
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	Android.mk \
	Application.mk \
	NativeImageProcessor.cpp \

LOCAL_C_INCLUDES += src/debug/jni
LOCAL_C_INCLUDES += src/main/jni

include $(BUILD_SHARED_LIBRARY)
