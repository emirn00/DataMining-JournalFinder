# Journal Finder - Data Mining Project

Bu proje, akademik makale özetlerini (abstract) analiz ederek en uygun dergi önerilerini sunan uçtan uca bir Veri Madenciliği sistemidir. Sistem, 23.000'den fazla gerçek akademik kayıt üzerinde TF-IDF ve Cosine Similarity algoritmalarını kullanarak anlamsal analiz yapar.

## Proje Yapısı

Proje üç ana bileşenden oluşmaktadır:
1. Frontend: Angular tabanlı kullanıcı arayüzü.
2. Backend: Spring Boot (Java) tabanlı orkestrasyon servisi.
3. Analiz Motoru: FastAPI (Python) tabanlı NLP ve Veri Madenciliği motoru.

## Gereksinimler

- Java 21 veya üzeri
- Python 3.10 veya üzeri
- Node.js ve npm (Angular için)
- SQLite3

## Kurulum ve Çalıştırma

Sistemin tam fonksiyonel çalışması için üç bileşenin de aynı anda çalışıyor olması gerekmektedir.

### 1. Analiz Motorunu Başlatma (Python)
Analiz motoru, sistemin "beyni"dir ve tüm veri madenciliği hesaplamalarını yapar.

cd notebooks
pip install -r requirements.txt
python recommender_api.py

*Not: Servis varsayılan olarak http://localhost:5001 adresinde çalışır.*

### 2. Backend Servisini Başlatma (Java)
Backend, arayüzden gelen istekleri doğrular ve Python servisi ile iletişim kurar.

cd backend
./gradlew bootRun

*Not: Servis varsayılan olarak http://localhost:8080 adresinde çalışır.*

### 3. Kullanıcı Arayüzünü Başlatma (Angular)
cd frontend
npm install
npm start

*Not: Arayüz varsayılan olarak http://localhost:4200 adresinde açılır.*

## Veri Madenciliği Metodolojisi

Bu projenin temelini oluşturan bilimsel süreçler `notebooks/journal_finder.ipynb` dosyasında belgelenmiştir. Temel adımlar şunlardır:

- Veri Ön İşleme: Metinlerin temizlenmesi ve stop-word'lerin ayıklanması.
- Vektörleştirme (TF-IDF): Kelimelerin metin içindeki önem derecelerine göre sayısal vektörlere dönüştürülmesi.
- Benzerlik Analizi: Cosine Similarity kullanılarak girdi metni ile veritabanı arasındaki en yakın dergilerin bulunması.
- Kümeleme: K-Means algoritması ile makalelerin konu kümelerine (Topic Clusters) ayrılması.

## Önemli Notlar

- Veritabanı dosyası (CompSciencePub.sqlite) projenin kök dizininde bulunmalıdır.
- Sistem performansı için Python servisinin startup aşamasında modelin eğitilmesi (23k kayıt için) birkaç saniye sürebilir.
- Raporlama ve istatistiksel analizler için Jupyter Notebook dosyası kullanılmalıdır.
