import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalTime;
public class BikePartUI{
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        FileInputStream inputfile = new FileInputStream("WarehouseDB.txt");

        ArrayList inventory = new ArrayList();
        Scanner inputfilescanner = new Scanner (inputfile);
        boolean inputfilecontains = true;
        while(inputfilecontains){
            if(inputfilescanner.hasNextLine()) {
                inventory.add(inputfilescanner.nextLine());
            }
            else {
                inputfilecontains = false;
            }
        }
        ArrayList deliveryparts = new ArrayList();
        int choice;
        boolean programrunning = true;
        while(programrunning){
            System.out.println("1: Read File");
            System.out.println("2: Enter Part");
            System.out.println("3: Sell Part");
            System.out.println("4: Display Part");
            System.out.println("5: Sort Name");
            System.out.println("6: Sort Number");
            System.out.println("7: Quit Program");
            choice = input.nextInt();
            if(choice == 1){
                    FileInputStream readfile = new FileInputStream("inventory.txt");

                    Scanner readinputfile = new Scanner(readfile);
                    boolean readingfile = true;
                    while (readingfile) {
                        if (readinputfile.hasNextLine()) {
                            String currentline = readinputfile.nextLine();
                            System.out.println(currentline);
                            System.out.println(inventory.get(2));
                            ArrayList newarraylist = BikePartAdd(inventory,currentline);
                            inventory = newarraylist;
                            System.out.println(inventory.get(2));
                        }
                        if (readinputfile.hasNextLine() == false) {
                                readingfile = false;
                        }
                        }
                    readfile.close();
                    readinputfile.close();
                    }

            else if(choice == 2) {
                System.out.println("Enter Part(String Part name, int Part Number, double Part Price, double Part Sale Price, boolean whether price on sale, int quantity)");
                String newpart = input.next();
                newpart += input.nextLine();
                ArrayList newarraylist =BikePartAdd(inventory,newpart);
                inventory = newarraylist;
            }
            else if(choice == 3){
                String partname = "";
                boolean founditem = false;
                System.out.println("Enter Part Number:");
                String sellpart = input.next();
                for(int i = 0; i < inventory.size(); i++){
                    String currentline = (String) inventory.get(i);
                    String price = "";
                    String sale = "";
                    String[] currentlinearray = currentline.split(",");
                    String trimmedpartnum = (String) currentlinearray[1].trim();
                    if(trimmedpartnum.equals(sellpart)){
                        if (Boolean.getBoolean(currentlinearray[4])) {
                            price = currentlinearray[3];
                            sale = "The item is on sale.";
                        }
                        else {
                            price = currentlinearray[2];
                            sale = "The item is not on sale.";
                        }
                        }
                        System.out.println(currentlinearray[0] + ", " + currentlinearray[1] + ", the price is $" + price);
                        System.out.println(sale);
                        partname = currentlinearray[0];
                        BikePart currentpart = BikePartConverter(currentline);
                        currentpart.setQuantity(currentpart.getQuantity()-1);
                        String bikepartstring = currentpart.getPartName() + "," + currentpart.getPartNumber() + "," + currentpart.getPrice()
                                + "," + currentpart.getSalesPrice() + "," + currentpart.isOnSale() + "," + currentpart.getQuantity();
                        inventory.set(i,bikepartstring);
                        System.out.println("A " + partname + " was sold at " + LocalTime.now().toString());
                        founditem = true;
                        i = inventory.size() + 1;

                    }
                    if(founditem = false){
                        System.out.println("Error: Item not found");
                    }

                }


            else if(choice == 4){
                System.out.println("Enter part name:");
                String displaypart = input.next();
                displaypart += input.nextLine();
                boolean pricefound = false;
                for(int i= 0; i < inventory.size();i++){
                    String currentline = (String) inventory.get(i);
                    String[] currentlinearray = currentline.split(",");
                    String trimmedname = currentlinearray[0].trim();
                    String sale = "";
                    String price = "";
                    if(trimmedname.equals(displaypart)){
                        if(Boolean.getBoolean(currentlinearray[4])){
                            price = "price is $" + currentlinearray[3];
                        }
                        else {
                            price = "price is $" + currentlinearray[2];
                        }
                        System.out.println(currentlinearray[0] + ", " + currentlinearray[1] + ", " + price);
                        pricefound = true;
                        i = inventory.size() + 1;
                    }


                    }
                if(pricefound == false){
                    System.out.println("Error: Item not found");
                }
            }
            else if(choice == 5){
                inventory.sort(null);
                for(int i = 0; i < inventory.size();i++){
                    System.out.println(inventory.get(i));
                }
            }
            else if(choice == 6){
                ArrayList numbers = new ArrayList();
                ArrayList orderedparts = new ArrayList();
                for(int i =0;i < inventory.size();i++){
                    String currentline = (String) inventory.get(i);
                    String[] currentlinearray = currentline.split(",");
                    int partnumber = Integer.parseInt(currentlinearray[1].trim());
                    numbers.add(partnumber);

                }
                numbers.sort(null);
                for(int i = 0; i < numbers.size();i++){
                    for(int z = 0; z < inventory.size(); z++){
                        String currentline = (String) inventory.get(z);
                        String[] currentlinearray = currentline.split(",");
                        String partnumber = currentlinearray[1].trim();
                        String currentnumber = numbers.get(i).toString();
                        System.out.println(partnumber + " " + numbers.get(i));
                        System.out.println(partnumber.equals(currentnumber));
                        if(partnumber.equals(currentnumber)){
                            orderedparts.add(currentline);
                            z = inventory.size() + 10;
                        }
                    }
                }
                for(int i = 0; i < orderedparts.size(); i++){
                    System.out.println(orderedparts.get(i));
                }

            }
            else if(choice == 7){
                FileOutputStream outfile = new FileOutputStream("InvenT.txt");
                PrintWriter output = new PrintWriter(outfile);
                for(int i = 0; i < inventory.size(); i++){
                    output.println(inventory.get(i));
                }
                input.close();
                inputfile.close();
                inputfilescanner.close();
                output.flush();
                output.close();
                programrunning = false;
            }
            else {
                System.out.println("Not one of the choices, please pick one from the list");
            }


        }
    }
    public static BikePart BikePartConverter(String newline) throws IOException{
        String[] newlinearray = newline.split(",");
        Scanner newpartline = new Scanner(newline);
        String bikepartname = newlinearray[0];
        String bikepartnumber = newlinearray[1];
        String bikepartprice = newlinearray[2];
        String bikepartsaleprice = newlinearray[3];
        boolean bikepartonsale = Boolean.getBoolean(newlinearray[4]);
        String trimint = newlinearray[5].trim();
        int bikepartquantity = Integer.parseInt(trimint);
        BikePart newbikepart = new BikePart(bikepartname,bikepartnumber,bikepartprice,bikepartsaleprice,bikepartonsale,bikepartquantity);
        newpartline.close();
        return newbikepart;
    }
    public static ArrayList BikePartAdd(ArrayList currentarray, String newbikepart){
        boolean matches = false;
        ArrayList newone = new ArrayList();
        for(int i = 0; i < currentarray.size(); i++) {
            String currentline = (String) currentarray.get(i);
            String[] currentlinearray = currentline.split(",");
            String[] newbikepartarray = newbikepart.split(",");
            if(currentlinearray[0].equals(newbikepartarray[0])){
                currentlinearray[2] = newbikepartarray[2];
                currentlinearray[3] = newbikepartarray[3];
                currentlinearray[4] = newbikepartarray[4];
                String trimint1 = currentlinearray[5].trim();
                String trimint2 = newbikepartarray[5].trim();
                int quantitybikepart = Integer.parseInt(trimint1);
                int quantitynewbikepart = Integer.parseInt(trimint2);
                int result = quantitybikepart + quantitynewbikepart;
                currentlinearray[5] = Integer.toString(result);
                String newlineforarray = currentlinearray[0] + ", " + currentlinearray[1] + ", " + currentlinearray[2] + ", " + currentlinearray[3] + ", " + currentlinearray[4] + ", " + currentlinearray[5];
                newone.add(newlineforarray);
            }
            else{
                newone.add(currentline);



            }

        }
        if(matches == false){
            currentarray.add(newbikepart);
        }
        return newone;
    }
}