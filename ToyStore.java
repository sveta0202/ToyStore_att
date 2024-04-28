import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Date;

public class ToyStore {
    private ArrayList<Toy>storage = new ArrayList<>();
    private ArrayList<Toy>raffle = new ArrayList<>();
    private DataWork dataWork = new DataWork();
    private Menu menu = new Menu();


    public void importFromFile() {
        storage.clear();
        raffle.clear();
        storage.addAll(dataWork.readTxt());
        System.out.println("Данные из файла загружены, прозведена замена всех данных со склада, очищен список для розыгрыша");
        menu.any();
    }

    public void inputNewToy(){
        System.out.print("Введите название игрушки для текущей записи: ");
        String sampleString = System.console().readLine();
        int sampleAmount = menu.inputAmount(0, menu.getTextAmount1());
        Double sampleChance = menu.inputChance();
        storage.add(new Toy(sampleString, sampleAmount, sampleChance));
        System.out.println("Запись успешно добавлена в храналище");
    } 

    public void showRaffleList(){
        int counter = 1;
        System.out.println("Список игрушек, участвующих в розыгрыше:");
        if (raffle.size() == 0) System.out.println("Список пуст, добавьте игрушки для розыгрыша в список");
        else {
            for(Toy toy: raffle) {
                System.out.println(Integer.toString(counter) + ". " + toy);
                counter++;
            }
        }
    }

    public void showStorage(){
        int counter = 1;
        System.out.println("Склад продукции, с доступными для розыгрыша игрушками:");
        if (storage.size() == 0) System.out.println("Склад пуст, импортируйте данные из файла, либо добавьте продукцию вручную");
        else {
            for (Toy toy : storage) {
                System.out.println(Integer.toString(counter) + ". " + toy);
                counter++;
            }
        }
    }

    public int changeChance(){
        showStorage();
        if (storage.size() == 0) {
            menu.any();
            return 0;
        } else {
            System.out.println("\nДля изменения шанса выпадения, выберите позицию из списка:");
            int option = menu.inputButtons(storage.size(), menu.getText1());
            if (option == 0) {
                menu.any();
                return 1;
            }
            Double chance = menu.inputChance();
            storage.get(option - 1).setChance(chance);
            System.out.println("Шанс выпадения для выбранной записи был изменён");
            menu.any();
            return 1;
        }
    }

    public int changeAmount(){
        showStorage();
        if (storage.size() == 0) {
            menu.any();
            return 0;
        } else {
            System.out.println("\nДля увеличения кол-ва игрушек, выберите позицию из списка");
            int option = menu.inputButtons(storage.size(), menu.getText1());
            if (option == 0) {
                menu.any();
                return 1;
            }
            int amountSample = storage.get(option - 1).getAmount();

            int newAmount = menu.inputAmount(amountSample, menu.getTextAmount2());
            storage.get(option - 1).setAmount(newAmount);
            System.out.println("Кол-во игрушек для выбранной позиции было успешно изменено");
            menu.any();
            return 1;
        }
    }

    public int addToRaffle(){
        boolean flag = false;
        while (flag == false) {
            if (storage.size() == 0) {
                System.out.println("В хранилище нет игрушек, добавьте их из файла, либо загрузите данные вручную");
                menu.any();
                return 0;
            }
            else {
                showStorage();
                System.out.println("");
                int option = menu.inputButtons(storage.size(), menu.getTextAddToy1());
                if (option == 0) {
                    menu.any();
                    return 1;
                } else {
                    raffle.add(storage.get(option-1));
                    System.out.println("Игрушка успешно добавлена в список для розыгрыша.");
                    showRaffleList();
                    System.out.println("");
                    int option2 = menu.inputButtons(1, menu.getTextAddToy2());
                    if (option2 == 0) flag = true;
                }

            }
        }
        menu.any();
        return 1;
    }

    public int removeFromRaffle(){
        boolean flag = false;
        while (flag == false) {
            if (raffle.size() == 0) {
                System.out.println("Список на розыгрыш пуст - нет игрушек для удаления.");
                menu.any();
                return 0;
            }
            else {
                showRaffleList();
                System.out.println("");
                int option = menu.inputButtons(raffle.size(), menu.getTextRemoveToy1());
                if (option == 0) {
                    menu.any();
                    return 1;
                } else {
                    raffle.remove(option-1);
                    System.out.println("Игрушка успешно удалена из списка на розыгрыш.");
                    showRaffleList();
                    System.out.println("");
                    if (raffle.size() == 0) {
                        menu.any();
                        return 0;
                    }
                    int option2 = menu.inputButtons(1, menu.getTextRemoveToy2());
                    if (option2 == 0) flag = true;
                }

            }
        }
        menu.any();
        return 1;
    }

    public int raffleTime(){
        if (raffle.size() == 0) {
            System.out.println("Список на розыгрыш пуст - невозможно провести розыгрыш");
            menu.any();
            return 0;
        }
        else {
            String record;
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            Date date = new Date(); 
            String check = formatter.format(date);
            int toyCounter = 0;
            record = String.format("Розыгрыш игрушек, дата проведения: %s", check);
            dataWork.saveTxt(record);
            System.out.println(record);
            while(!raffle.isEmpty()){
                if (raffle.get(0).getAmount() <= 0){
                    record = String.format("%s - нельзя разыграть позицию, осталось 0 игрушек на складе", raffle.get(0));
                    System.out.println(record);
                    raffle.remove(0);
                } else {
                    int purchaseCounter = rafflePos(raffle.get(0).getChance());
                    int oldAmmount = raffle.get(0).getAmount();
                    raffle.get(0).setAmount(oldAmmount - 1);
                    record = String.format("%s - игрушка розыграна при покупке №: %d", raffle.get(0), purchaseCounter);
                    System.out.println(record);
                    raffle.remove(0);
                    toyCounter++;
                } 
                dataWork.saveTxt(record);
            }
            record = String.format("Розыгрыш успешно завершён, кол-во разыгранных игрушек: %d\n", toyCounter);
            dataWork.saveTxt(record);
            System.out.println(record);
            menu.any();
            return 1;
        }
    }

    public int rafflePos(double chance){
        int counter = 1;
        boolean flag = false;
        Random random = new Random();
        Double check;
        while (flag == false){
            check = random.nextDouble();
            if (check <= chance){
                flag = true;
                return counter;
            }
            else counter++;
        }
        return 0;
    }

}