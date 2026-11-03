# 🏗️ Build Instructions - Video İndirici

## Yöntem 1: GitHub Actions ile Online Build (En Kolay) ⭐

1. **GitHub'a Yükle:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/KULLANICI_ADIN/video-indirici.git
   git push -u origin main
   ```

2. **APK İndir:**
   - GitHub reponuza gidin
   - "Actions" sekmesine tıklayın
   - Son workflow'u açın
   - "app-debug" artifact'ını indirin
   - ZIP'i açın, APK'yı telefonunuza yükleyin!

## Yöntem 2: Windows'ta Gradle ile Build

### Gereksinimler:
- JDK 17 (https://adoptium.net/)
- Android SDK (Android Studio ile veya command line tools)

### Build Adımları:

1. **JDK Kur ve PATH'e Ekle:**
   ```cmd
   setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17.0.x"
   setx PATH "%PATH%;%JAVA_HOME%\bin"
   ```

2. **Android SDK'yı Kur:**
   - Android Studio indir VEYA
   - Command line tools indir: https://developer.android.com/studio#command-tools

3. **SDK PATH Ayarla:**
   ```cmd
   setx ANDROID_HOME "C:\Users\KULLANICI_ADIN\AppData\Local\Android\Sdk"
   setx PATH "%PATH%;%ANDROID_HOME%\platform-tools;%ANDROID_HOME%\tools"
   ```

4. **Proje Klasöründe Build Et:**
   ```cmd
   cd C:\Users\sceasty\Documents\AndroidVideoDownloader
   gradlew.bat assembleDebug
   ```

5. **APK Konumu:**
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

## Yöntem 3: Online APK Builder (Hiçbir şey indirmeden)

Eğer hiçbir şey indirmek istemiyorsan:

1. **Kod'u ZIP'le**
2. **AppBuild.org** veya **ApkOnline.com** sitelerine git
3. ZIP'i yükle
4. Build et
5. APK'yı indir!

## 📱 APK Yükleme

1. APK'yı telefona at
2. Ayarlar > Güvenlik > Bilinmeyen Kaynaklardan Yükleme > İzin Ver
3. APK'ya tıkla ve kur!

## 🔧 Sorun Giderme

### "Java bulunamadı" Hatası:
```cmd
java -version
```
Çalışmıyorsa JDK'yı yeniden kur ve PATH'e ekle

### "Android SDK bulunamadı" Hatası:
`local.properties` dosyası oluştur:
```properties
sdk.dir=C\:\\Users\\KULLANICI_ADIN\\AppData\\Local\\Android\\Sdk
```

### "Gradle build failed" Hatası:
```cmd
gradlew.bat clean
gradlew.bat assembleDebug --stacktrace
```

## 🚀 Release APK (Opsiyonel)

Release APK için keystore gerekir:

```cmd
keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias

gradlew.bat assembleRelease
```

---

**İpucu:** En kolay yöntem GitHub Actions! Hiçbir şey indirmeden APK alabilirsin 🎉
