import java.util.ArrayList;

public class Menu {
    private String sample = "";
    private String text1 ="Для выбора позиции наберите: 1-%d; наберите 0 для отмены и возврата в главное меню :\n";
    private String text2 = "Для выбора пункта меню, наберите 1-%d; наберите 0 для завершения работы приложения:\n";
    private String textAmount1 = "Введите не отрицательное целое значение для данной позиции: ";
    private String textAmount2 = "Введите не отрицательное целое значение для данной позиции, не меньше чем текущее значение (%d): ";
    private String textAddToy1 ="Для добавление игрушки в розыгрыш, выберите позицию из списка: 1-%d; наберите 0 для отмены и возврата в главное меню :\n";
    private String textAddToy2 = "Для добавления ещё одной игрушки в розыгрыш нажмите 1. Возврат в главное меню - 0: ";
    private String textRemoveToy1 ="Для удаления игрушки из розыгрыша, выберите позицию из списка: 1-%d; Отменнав и возврат в главное меню - 0 :\n";
    private String textRemoveToy2 = "Для удаления ещё одной игрушки в розыгрыш нажмите 1. Возврат в главное меню - 0: ";

    public String getText1(){
        return text1;
    }

    public String getText2(){
        return text2;
    }

    public String getTextAmount1(){
        return textAmount1;
    }

    public String getTextAmount2(){
        return textAmount2;
    }

    public String getTextAddToy1(){
        return textAddToy1;
    }

    public String getTextAddToy2(){
        return textAddToy2;
    }

    public String getTextRemoveToy1(){
        return textRemoveToy1;
    }

    public String getTextRemoveToy2(){
        return textRemoveToy2;
    }


    public void any(){
        System.out.print("\nВозврат в главное меню, для продолжения нажмите Enter: ");
        System.console().readLine();
        System.out.println("\n");
    }
    
    public boolean checkInputs(ArrayList<String> buttons, String input){
        for (String x : buttons) {
            if (input.equalsIgnoreCase(x)) return true;
        }
        return false;
    }

    public ArrayList<String> getButtons(int size){
        ArrayList<String> buttons = new ArrayList<>();
        buttons.add("0");
        for (int i = 1; i < size + 1; i++) {
            buttons.add(Integer.toString(i));
        }
        return buttons;
    }

    public int inputButtons(int size, String text){
        boolean flag = false;
        ArrayList<String> buttons = getButtons(size);

        while (flag == false){
            System.out.printf(text, size);
            sample = System.console().readLine();
            flag = checkInputs(buttons, sample);
            if (flag == false) System.out.println("Неверный формат данных.");
        }
        System.out.println("");
        return Integer.parseInt(sample);
    }

    public Double inputChance(){
        boolean flag = false;
        double num = 0;
        while (flag == false){
            try {
                System.out.print("Введите процентный шанс выпадения для данной позиции в диапазоне от 1 до 100 процентов: ");
                sample = System.console().readLine();
                num = Double.parseDouble(sample);
                if (num < 1 || num > 100) throw new Exception();
                else flag = true;
            } catch (Exception e) {
                System.out.print("\nНеверный формат данных. ");
            }
        }
        return num;
    }

    public int inputAmount(int amount, String text){
        boolean flag = false;
        int num = 0;
        while (flag == false){
            try {
                System.out.printf(text, amount);
                sample = System.console().readLine();
                num = Integer.parseInt(sample);
                if (num < 0 || num < amount) throw new Exception();
                else flag = true;
            } catch (Exception e) {
                System.out.print("Неверный формат данных. ");
            }
        }
        return num;
    }

    public void showMenu(){
        String menu = "1 - Загрузить список готовых игрушек из файла в хранилище.\n" +
                      "2 - Добавить новую позицию в хранилище вручную.\n" +
                      "3 - Показать список игрушек в хранилище.\n" + 
                      "4 - Изменить шанс выпадения игрушки.\n" +
                      "5 - Изменить кол-во игрушек для конкретной позиции в хранилище.\n" +
                      "6 - Показать список игрушек, участвующих в розыгрыше.\n" +
                      "7 - Добавить игрушку из хранилища в список для розыгрыша.\n" +
                      "8 - Удалить игрушку из списка розыгрыша.\n" +
                      "9 - Провести розыгрыш!\n" + 
                      "0 - Завершение работы.\n";
    System.out.println("МЕНЮ ->\n");                  
    System.out.println(menu);
    }
}