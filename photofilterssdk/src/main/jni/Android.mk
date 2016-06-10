LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NativeImageProcessor
LOCAL_SRC_FILES := NativeImageProcessor.cpp

include $(BUILD_SHARED_LIBRARY)
