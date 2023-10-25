import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lottery {
    //TODO: fix it
    //   public Scanner inp = new Scanner(System.in);


    private List<Map<String, String>> participantData; // Data structure to store participant data
    private List<Person> participants;
    List<String> days;
    public static List<String> defNames;
    private LotteryRepository repo = new LotteryRepository();
    private int counter = 0;


    public void counterArtir() {
        this.counter++;
    }

    public void counterAzalt() {
        this.counter--;
        // counter 0 dan kucuk olamaz if ile
    }

    public void countersifirla() {
        this.counter = 0;
    }


    public Lottery() {
        this.participants = new ArrayList<Person>();
        this.days = new ArrayList<>(Arrays.asList("Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi"));
//      this.defNames = new ArrayList<>(Arrays.asList("Eda", "Emrullah", "Ümmü", "Cahit", "Nurullah", "Ertugrul"));
        this.defNames = new ArrayList<>();

        repo.getAll();
    }

    boolean isRun = true;

    public void addParticipant() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Isim Giriniz...");
        String name = inp.nextLine();
        repo.saveParticipant(name);
        String lastParticipant = defNames.stream().reduce((first, second) -> second).orElse(null);
        Person participant = new Person(lastParticipant);
        this.participants.add(participant);
        System.out.println("Isim Eklendi...");
    }

    private void deleteParticipant() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Silmek istediğiniz kişi ID'sini Giriniz.");
        int delId = inp.nextInt();

        repo.delete(delId);
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
        System.out.println("Winner : " + winner.getName());
    }

    public void distributeParticipantsToWeekDays() {
        LocalDate dateNow = LocalDate.now();

        DateTimeFormatter dtft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtfd = DateTimeFormatter.ofPattern("EEEE");

        Collections.shuffle(this.participants);

        int dayIndex = 0;

        //Initialize the participantData list
        participantData = new ArrayList<>();

        for (Person participant : participants) {
            if (dtfd.format(dateNow).equals("Sunday") || dtfd.format(dateNow).equals("Pazar")) {
                dateNow = dateNow.plusDays(1);
            }
            String formattedMyCurrentDate = dtft.format(dateNow);
            String formattedMyCurrentDay = dtfd.format(dateNow);

            String day = days.get(dayIndex);
            System.out.println(participant.getName() + " " + formattedMyCurrentDay);

            // Add participant data to the list
            Map<String, String> data = new LinkedHashMap<>();
            data.put("Day", formattedMyCurrentDay);
            data.put("Name", participant.getName());
            data.put("Date", formattedMyCurrentDate);

            participantData.add(data);
            dateNow = dateNow.plusDays(1);

//            dayIndex = (dayIndex + 1) % days.size();
        }
    }

    public void saveResult() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();

        createHeaderRow(ws);
        int rowNumber = 1;

        // Iterate through participant data and write to Excel
        for (Map<String, String> data : participantData) {
            Row row = ws.createRow(rowNumber++);
            int columnNumber = 0;
            for (String key : data.keySet()) {
                Cell cell = row.createCell(columnNumber++);
                cell.setCellValue(data.get(key));
            }
            repo.saveDays(data.get("Name"), data.get("Day"), data.get("Date"));
        }

        try (FileOutputStream outputStream = new FileOutputStream("./participantWorkbook.xlsx")) {
            wb.write(outputStream);
            ColorPrinter.printColorfulText("Sonuçlar excel dosyasına kayıt edildi", ColorPrinter.GREEN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void createHeaderRow(XSSFSheet ws) {
        Row row = ws.createRow(0);
        List<String> headers = Arrays.asList("Day", "Name", "Date");
        for (int i = 0; i < headers.size(); i++) {
            Cell headerCell = row.createCell(i);
            headerCell.setCellValue(headers.get(i));
        }
    }

    public void listDistributedDays() {
        int numberOfParticipants = participants.size();
        repo.getDays(numberOfParticipants);
    }

    public void displayMenu() throws SQLException {

        Scanner inp = new Scanner(System.in);

        while (isRun) {
            ColorPrinter.printColorfulText("MENU", ColorPrinter.PINK);
            ColorPrinter.printColorfulText("1. Yeni Katılımcı Ekle", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("2. Katılımcı Sil", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("3. Çekilişi Başlat", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("4. Katılımcıları Listele", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("5. Katılımcıları Günlere Dağıt", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("6. Dağıtılmış Günleri Listele", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("7. Sonuçları Kaydet", ColorPrinter.YELLOW);
            ColorPrinter.printColorfulText("0. Çıkış", ColorPrinter.YELLOW);

            int choice = inp.nextInt();

            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    deleteParticipant();
                    break;
                case 3:
                    selectRandomPerson();
                    break;
                case 4:
                    repo.getAll();
                    break;
                case 5:
                    distributeParticipantsToWeekDays();
                    break;
                case 6:
                    listDistributedDays();
                    break;
                case 7:
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

    public void run() throws SQLException {
        defaultNames();
        displayMenu();
    }


}
