-keep class com.github.codandotv.craftd.** { *; }
-keepnames class com.github.codandotv.craftd.** { *; }

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.github.codandotv.craftd.**$$serializer { *; }
-keepclassmembers class com.github.codandotv.craftd.** {
    *** Companion;
}
-keepclasseswithmembers class com.github.codandotv.craftd.** {
    kotlinx.serialization.KSerializer serializer(...);
}
