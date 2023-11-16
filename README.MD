# Çekiliş Uygulama Modeli

## Sınıflar

- **Person (Kişi)**: Çekilişte yer alacak katılımcıları temsil eder. Properties olarak "name" (isim, string) ve "ID" (
  kimlik numarası, int) özelliklerine sahiptir.
- **Lottery (Çekiliş)**: Çekilişi temsil eder. Katılımcılar (Person) listesini ve haftanın günlerini içerir.
- **Main (Ana)**: Uygulamanın ana sınıfıdır. Lottery sınıfının fonksiyonlarını çağırır.

## İlişkiler

- Main sınıfı, Lottery sınıfına bir referans tutar.
- Person sınıfı, Lottery sınıfı tarafından kullanılır.

## Metotlar

### Person (Kişi):

- `getName()`: Kişinin adını (string) döndürür.
- `getID()`: Kişinin kimlik numarasını (int) döndürür.

### Lottery (Çekiliş):

- `addParticipant()`: Çekilişe yeni bir katılımcı ekler.
- `selectRandomPerson()`: Çekilişten rastgele bir katılımcı seçer.
- `distributeParticipantsToWeekDays()`: Katılımcıları haftanın günlerine rastgele dağıtır.

### Main (Ana):

- `displayMenu()`: Kullanıcıya menüyü gösterir ve seçim yapmasını sağlar.
- `startLottery()`: Kullanıcının çekilişi başlatmasını sağlar.
- `main()`: Uygulamayı başlatır.
- `printColorfulText(text, color)`: Renkli metinleri konsol ekranında görüntülemek için kullanılır.

## Ek Özellikler (Opsiyonel)

- **Çekiliş Tarihini ve Saatini Belirleme**: Çekilişin tarihini ve saatini kullanıcı tarafından belirleyebilir hale
  getirir, böylece çekilişi daha önceden planlayabilirsiniz.
- **Çekiliş Sonuçlarını Kaydetme**: Çekiliş sonuçlarını bir dosyaya kaydetme özelliğini ekler, bu da sonuçları daha
  sonra tekrar görüntülemenize olanak tanır.

## Menü Kullanımı

Kullanıcıya sunulan menü seçenekleri:

1. Yeni Katılımcı Ekle
2. Çekilişi Başlat
3. Sonuçları Kaydet
4. Çıkış

Kullanıcı, menüdeki seçeneklere uygun numaraları girerek istediği işlemi seçebilir.

- **Yeni Katılımcı Ekle**: Bir kişiyi çekilişe eklemek için kullanılır.
- **Çekilişi Başlat**: Çekilişi başlatmak için kullanılır. Bu seçenek, katılımcıları rastgele seçer ve haftanın
  günlerine dağıtır.
- **Sonuçları Kaydet**: Çekiliş sonuçlarını bir dosyaya kaydetmek için kullanılır.
- **Çıkış**: Uygulamadan çıkmak için kullanılır.

---

**Proje Adı:** SimpleLotteryApp
