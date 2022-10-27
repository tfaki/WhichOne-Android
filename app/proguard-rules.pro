# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#OKHTTP
 -keepattributes Signature
 -keepattributes *Annotation*
 -keep class okhttp3.** { *; }
 -keep interface okhttp3.** { *; }
 -dontwarn okhttp3.**
 -dontwarn Java.nio.file.*

 #Gson
  -keep class com.google.gson.stream.** { *; }

 #Retrofit
  -keep class com.google.gson.** { *; }
  -keep public class com.google.gson.** {public private protected *;}
  -keep class com.google.inject.** { *; }
  -keep class org.apache.http.** { *; }
  -keep class org.apache.james.mime4j.** { *; }
  -keep class javax.inject.** { *; }
  -keep class javax.xml.stream.** { *; }
  -keep class retrofit.** { *; }
  -keep class com.google.appengine.** { *; }
  -keepattributes *Annotation*
  -keepattributes Signature
  -dontwarn com.squareup.okhttp.*
  -dontwarn javax.xml.stream.**
  -dontwarn com.google.appengine.**
  -dontwarn java.nio.file.**
  -dontwarn org.codehaus.**
  -keep,allowobfuscation,allowshrinking interface retrofit2.Call
  -keep,allowobfuscation,allowshrinking class retrofit2.Response
  -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

  -keep class com.loftymr.whichone.feature.screen.splash.SplashActivity
  -keep class com.loftymr.whichone.feature.screen.main.MainActivity
  -keepclasseswithmembers class com.loftymr.whichone.data.model.** { *; }

