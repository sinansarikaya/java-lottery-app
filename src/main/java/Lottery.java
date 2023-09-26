import java.util.*;

public class Lottery {
    //TODO: fix it
    //   public Scanner inp = new Scanner(System.in);
    private List<Person> participants;
    private List<String> days;

    public Lottery() {

    }

    public Lottery(List<String> days) {
        this.participants = new ArrayList<Person>();
        this.days = days;
    }

    boolean isRun = true;

    public void addParticipant() {
        Scanner inp = new Scanner(System.in);
        String name = inp.nextLine();

        Person participant = new Person(0,name);

    }

    public static int selectRandomPerson() {
        Random random = new Random();
        int randNum = random.nextInt(11);

        return randNum;
    }

    public void listParticipants(){
        this.participants.forEach(t->t);
    }

    public static void distributeParticipantsToWeekDays() {

    }

    public static void saveResult() {

    }

    public void displayMenu() {

        Scanner inp = new Scanner(System.in);

        while (isRun) {
            System.out.println("MENU");
            System.out.println("1. Yeni Katılımcı Ekle");
            System.out.println("2. Çekilişi Başlat");
            System.out.println("3. Katılımcıları Listele");
            System.out.println("4. Katılımcıları Günlere Dağıt");
            System.out.println("5. Sonuçları Kaydet");
            System.out.println("0. Çıkış");

            int choice = inp.nextInt();

            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    System.out.println("selectRandomPerson() = " + selectRandomPerson());
                    break;
                case 3:
                    distributeParticipantsToWeekDays();
                    break;
                case 4:
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
        displayMenu();
    }

}