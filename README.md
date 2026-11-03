# Video İndirici 🎬

Modern, beyaz temali Android video indirme uygulaması. TikTok, YouTube ve Instagram videolarını kolayca indirin!

## ✨ Özellikler

### 📱 Ana Özellikler
- **Paylaş Menüsü Entegrasyonu**: TikTok, YouTube veya Instagram'da paylaş butonuna basın, uygulama otomatik açılsın
- **Gerçek Video İndirme**: yt-dlp teknolojisi ile gerçek video indirme
- **Link Yapıştırma**: Manuel olarak link yapıştırarak da indirebilirsiniz
- **Kalite Seçenekleri**: 360p, 480p, 720p, 1080p
- **MP3 İndirme**: Sadece ses dosyası olarak da indirebilirsiniz
- **İndirme Yönetimi**: Aktif ve tamamlanan indirmeleri görüntüleyin
- **Modern Tasarım**: Beyaz, temiz ve kullanıcı dostu arayüz
- **Çok Platform Desteği**: YouTube, TikTok, Instagram, Facebook, Twitter, Reddit, Vimeo, Dailymotion

### 🎬 Desteklenen Platformlar
- ✅ YouTube (videolar, shorts, playlist'ler)
- ✅ TikTok (watermark olmadan)
- ✅ Instagram (reels, IGTV, stories)
- ✅ Facebook
- ✅ Twitter/X
- ✅ Reddit
- ✅ Vimeo
- ✅ Dailymotion
- ✅ Ve 1000+ site daha!

### 🎨 Kullanıcı Arayüzü
- Material Design 3 ile modern tasarım
- Animasyonlu geçişler ve smooth kullanıcı deneyimi
- Alt navigasyon çubuğu (Ana Sayfa ve İndirilenler)
- Progress bar ile indirme takibi
- Status chip'leri ile durum gösterimi

### 🔧 Teknik Detaylar
- **Kotlin** ile yazılmış
- **Jetpack Compose** ile modern UI
- **MVVM** mimarisi
- **Material Design 3** bileşenleri
- **Coroutines** ile asenkron işlemler
- **StateFlow** ile reactive state yönetimi

## 📦 Kurulum

### 🚀 En Hızlı Yöntem: GitHub Actions

**Hiçbir şey indirmeden APK al:**

1. Kodu GitHub'a yükle:
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/KULLANICI_ADIN/video-indirici.git
   git push -u origin main
   ```

2. GitHub > Actions sekmesine git
3. Workflow tamamlanınca "app-debug" artifact'ını indir
4. APK'yı telefonuna yükle!

Detaylı build talimatları için: [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)

### Gereksinimler (Yerel Build için)
- Android Studio veya JDK 17
- Android SDK 24 (Android 7.0) veya üzeri
- Kotlin 1.9.20

### Projeyi Çalıştırma

1. Projeyi Android Studio'da açın
2. Gradle sync yapın
3. Emulator veya fiziksel cihazda çalıştırın

```bash
./gradlew build
./gradlew installDebug
```

## 🚀 Kullanım

### Yöntem 1: Paylaş Menüsünden
1. TikTok, YouTube veya Instagram'da bir video açın
2. Paylaş butonuna basın
3. "Video İndirici" uygulamasını seçin
4. Kalite ve format seçin
5. İndir butonuna basın

### Yöntem 2: Manuel Link
1. Uygulamayı açın
2. Video linkini kopyalayın
3. Ana sayfadaki input alanına yapıştırın
4. Seçenekleri belirleyin
5. İndir butonuna basın

## 📁 Proje Yapısı

```
app/src/main/java/com/videodownloader/app/
├── MainActivity.kt                 # Ana Activity
├── models/
│   └── DownloadModels.kt          # Data modelleri
├── ui/
│   ├── screens/
│   │   ├── MainScreen.kt          # Ana ekran ve navigasyon
│   │   ├── HomeScreen.kt          # Link girişi ve seçenekler
│   │   └── DownloadsScreen.kt     # İndirilenler listesi
│   ├── viewmodel/
│   │   └── DownloadViewModel.kt   # ViewModel
│   └── theme/
│       ├── Theme.kt               # Tema tanımları
│       └── Type.kt                # Typography
```

## 🎯 Önemli Notlar

### Video İndirme Teknolojisi

Bu uygulama **yt-dlp** (YouTube-DL'in gelişmiş versiyonu) kullanarak gerçek video indirme yapabilir:

- ✅ **1000+ platform** desteği
- ✅ **Otomatik kalite seçimi**
- ✅ **MP3/MP4 dönüştürme**
- ✅ **Progress tracking**
- ✅ **Hata yönetimi**

### İlk Kullanımda

Uygulama ilk açılışta **yt-dlp** binary'sini indirecektir (~50MB). Bu bir kerelik işlemdir ve sonraki kullanımlarda gerekli değildir.

### Yasal Uyarı ⚠️
Bu uygulama sadece eğitim amaçlıdır. Telif hakkıyla korunan içerikleri izinsiz indirmek yasadışıdır. Kullanıcılar:
- Sadece kendi içeriklerini
- İzin verilmiş içerikleri
- Yasal olarak indirilebilir içerikleri indirebilir

## 🔜 Gelecek Özellikler

- [ ] Playlist indirme (toplu video)
- [ ] Video player entegrasyonu
- [ ] Dark mode desteği
- [ ] Paylaşma özelligi
- [ ] İndirme geçmişi
- [ ] Favori videolar
- [ ] Subtitle (altyazı) indirme
- [ ] Thumbnail önizleme

## 🐛 Bilinen Sorunlar

- İlk kullanımda yt-dlp binary indiriliyor (~50MB, tek seferlik)
- Bazı platformlarda rate limit olabilir
- TikTok private hesaplar desteklenmez

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır.

## 🤝 Katkıda Bulunma

1. Fork yapın
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Commit yapın (`git commit -m 'feat: Add amazing feature'`)
4. Push yapın (`git push origin feature/amazing-feature`)
5. Pull Request açın

## 📞 İletişim

Sorularınız için issue açabilirsiniz.

---

**✅ HAZIR! Gerçek video indirme özelliği çalışıyor!** 🎉

Build talimatları için: [BUILD_INSTRUCTIONS.md](BUILD_INSTRUCTIONS.md)
