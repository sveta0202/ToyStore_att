public class Main {    
    public static void main(String[] args) {
        boolean flag = false;
        ToyStore toyStore = new ToyStore();
        Menu menu = new Menu();

        while (flag == false){
            menu.showMenu();
            int select = menu.inputButtons(9, menu.getText2());
            switch(select) {
                case 1:
                    toyStore.importFromFile();
                    break;
                case 2:
                    toyStore.inputNewToy();
                    menu.any();
                    break;
                case 3:
                    toyStore.showStorage();
                    menu.any();
                    break;
                case 4:
                    toyStore.changeChance();
                    break;
                case 5:
                    toyStore.changeAmount();
                    break;
                case 6:
                    toyStore.showRaffleList();
                    menu.any();
                    break;
                case 7:
                    toyStore.addToRaffle();
                    break;
                case 8:
                    toyStore.removeFromRaffle();
                    break;
                case 9:
                    toyStore.raffleTime();
                    break;
                case 0:
                    System.out.println("Завершение работы приложения.");
                    flag = true;
                    break;
            }
        }


    }
}