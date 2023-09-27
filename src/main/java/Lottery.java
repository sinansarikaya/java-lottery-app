import java.util.*;

public class Lottery {
    //TODO: fix it
    //   public Scanner inp = new Scanner(System.in);
    private List<Person> participants;
    List<String> days;
    List<String> defNames;

    public Lottery() {
        this.participants = new ArrayList<Person>();
        this.days = new ArrayList<>(Arrays.asList("Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi"));
        this.defNames = new ArrayList<>(Arrays.asList("Sinan", "Eda", "Emrullah", "Ümmü", "Cahit"));
    }

    boolean isRun = true;

    public void addParticipant() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Isim Giriniz...");
        String name = inp.nextLine();

        Person participant = new Person(name);
        this.participants.add(participant);
        System.out.println("Isim Eklendi...");
    }

    public void defaultNames() {
        for (String w : defNames) {
            Person participant1 = new Person(w);
            this.participants.add(participant1);
        }
    }

    public void selectRandomPerson() {
        Random random = new Random();
        int randNum = random.nextInt(this.participants.size());
        Person winner = participants.get(randNum);
        System.out.println("Winner :" + winner.getName());

    }

    public void listParticipants() {
        System.out.println("Katılımcı Listesi:");
        for (Person participant : participants) {
            System.out.println(participant.getName());
        }
        System.out.println(this.participants.size());

    }

    public void distributeParticipantsToWeekDays() {

        Collections.shuffle(this.participants);
        int dayIndex = 0;
        for (Person participant : participants) {
            String day = days.get(dayIndex);
            System.out.println(participant.getName() + " " + day);
            dayIndex = (dayIndex + 1) % days.size();
        }

    }

    public static void saveResult() {

    }

    public void displayMenu() {

        Scanner inp = new Scanner(System.in);

        while (isRun) {
            ColorPrinter.printColorfulText("MENU", ColorPrinter.GREEN);
            ColorPrinter.printColorfulText("1. Yeni Katılımcı Ekle", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("2. Çekilişi Başlat", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("3. Katılımcıları Listele", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("4. Katılımcıları Günlere Dağıt", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("5. Sonuçları Kaydet", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("0. Çıkış", ColorPrinter.YELLOW);


            int choice = inp.nextInt();

            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    selectRandomPerson();
                    break;
                case 3:
                    listParticipants();
                    break;
                case 4:
                    distributeParticipantsToWeekDays();
                    break;
                case 5:
                    saveResult();
                    break;
                case 0:
                    System.out.println("Çıkış yapıldı");
                    isRun = false;
                    break;
                default:
                    System.out.println("Hatalı giriş yaptınız..");
            }
        }
    }

    public void run() {
        defaultNames();
        displayMenu();
    }


}
