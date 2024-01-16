import java.awt.*; //For creating an object DESK to run operations for opening URLs
import java.io.File; //For File Handling
import java.io.FileNotFoundException; //For Exception Handling In File Handling
import java.io.FileWriter; //For Writing into File
import java.io.IOException; //For Exception Handling In File Handling
import java.net.URI; //For Opening URLs
import java.net.URISyntaxException;  //For removing Exceptions in opening URLs
import java.nio.file.Files; //For working inside the files
import java.nio.file.Paths; //For working inside the files to manipulate content
import java.util.InputMismatchException;  //For Exception Handling
import java.util.Scanner; //For Scanner class


public class ParitialFunctional {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Desktop desk = Desktop.getDesktop();
        input = new Scanner(System.in);
        int option;
        do {
            System.out.print(">>>>>>>    Choose your preference    <<<<<<<<\n" +
                    "1. Open Admin Panel\n" +
                    "2. Open Passenger Panel\n" +
                    "3. Exit\n" +
                    ">>>>  ");
            option = input.nextInt();
            switch (option)
            {
                case 1:
                    admin();
                case 2:
                    passenger();
                case 3:
                    System.out.println("<<<<<<<<     EXIT    >>>>>>>>>");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not valid input");
            }
        }
        while (option != 0);
    }

    public static void admin() throws IOException {
        input = new Scanner(System.in);
        System.out.println("\n <<<<<<<<<< Welcome To Admin Panel >>>>>>>>>>>>>>");
        System.out.println("\n <<<<<<<<< Please Enter Your Credentials >>>>>>>>>>\n");

        Scanner input = new Scanner(System.in);
        int index = 0;
        Files.readAllLines(Paths.get("Admins Passwords.txt")).get(index).equalsIgnoreCase("ADMINS");
        Files.readAllLines(Paths.get("Admins User Names.txt")).get(index).equalsIgnoreCase("ADMINS");

        System.out.print("Enter Your User_Name >>>> ");
        String user_name = input.nextLine();

        System.out.print("Enter Your Password >>>> ");
        String password = input.nextLine();
        boolean pas_match = false;
        try {
            while (index < 5) {
                if (Files.readAllLines(Paths.get("Admins User Names.txt")).get(index).equals(user_name) && Files.readAllLines(Paths.get("Admins Passwords.txt")).get(index).equals(password)) {
                    pas_match = true;
                    menuForAdmin();
                    break;
                } else {
                    //System.out.println("False");
                    index++;
                }
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println(" >>>>>>>>>>>>>> Invalid Input <<<<<<<<<<<<< ");
            e.printStackTrace();
        }
        System.out.println(" >>>>>>>>>>>>>> Invalid Credentials >>>>>>>>>>>>>> \n" +
                " >>>>>>>>>>>>>> Admin Not Found >>>>>>>>>>>>>> \n" +
                " >>>>>>>>>>>>>> PLEASE ENTER AGAIN >>>>>>>>>>>>>> \n");

    }

    public static void menuForAdmin() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<\n" +
                ">>> Welcome Admin To Train Management System <<<\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<\n");
        int option;
        do {
            System.out.println(">>>>>>>>  Choice your Preference  <<<<<<<<<<< \n" +
                    "1. Train Menu\n" +
                    "2. Journey Menu\n" +
                    "3. EXIT\n" +
                    "0. Back\n" +
                    ">>>>");
            option = input.nextInt();
            switch (option)
            {
                case 1:
                    menuForTrain();
                case 2:
                    menuForJourney();
                case 3:
                    System.out.println(">>>>>>>>>>>>> EXIT <<<<<<<<<<<<<<<<<<<<");
                    System.exit(option);
                case 0:
                    try {
                        admin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
            }
        }
        while (option != 0) ;
    }

    public static void menuForTrain() {
        int option;
        do {
            System.out.println(">>>>>>> Choose your preference <<<<<<<<\n" +
                    "1. Add Train\n" +
                    "2. View Train\n" +
                    "3. Update Train\n" +
                    "4. Delete Train\n" +
                    "5. EXIT\n" +
                    "0. Back\n" +
                    ">>>>");
            option = input.nextInt();
            if (option == 1) {
                addTrain();
            } else if (option == 2) {
                viewTrain();
            }
            else if (option == 3) {
                updateTrain();
            } else if (option == 4) {
                deleteJourney();
            }
            else if (option == 5) {
                System.out.println(">>>>>>>>>>>>> EXIT <<<<<<<<<<<<<<<<<<<<");
                System.exit(option);
            } else if (option == 0) {
                menuForAdmin();
            }
        }
        while (option != 0) ;
        System.out.println(" >>>>>>>>>>>>>> Invalid Input <<<<<<<<<<<<< ");
    }

    // >>>>>>>>>>>>>>> Methods For Train <<<<<<<<<<<<<<<<<<<<<<<<<//
    public static void addTrain() {
        input = new Scanner(System.in);

        System.out.print("Engine Num >>> ");
        String engineNumber = input.next();

        System.out.print("Number of Bogies >>> ");
        String trainSize = input.next();

        System.out.print("Driver Name >>> ");
        String driverName = input.next();


        //Creating new File
        try {
            File adminData = new File(engineNumber + ".txt");
            adminData.createNewFile();
        } catch (IOException e) {
            System.out.println(">>>>>>>>>>>>>> Unable To create a file <<<<<<<<<<<<");
            e.printStackTrace();
        }

        //Writing into the File
        try {
            FileWriter adminWriter = new FileWriter(engineNumber + ".txt");
            adminWriter.write("Engine Number >>> " + engineNumber + "\n");
            adminWriter.write("Train Size(Buggies) >>> " + trainSize + "\n");
            adminWriter.write("Driver Name >>> " + driverName + "\n");
            adminWriter.close();
        } catch (IOException e) {
            System.out.println(">>>>> Unable To Write in a File: ");
            e.printStackTrace();
        }
        menuForTrain();
    }

    public static void viewTrain() {

        Scanner str = new Scanner(System.in);
        System.out.println("Enter Engine Number to Fetch Data: ");
        String engine_number = str.nextLine();

        File Reader = new File(engine_number + ".txt");
        Scanner input = null;
        try {
            input = new Scanner(Reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
        menuForTrain();
    }

    public static void updateTrain() {
        // >>>  First, Deleting previous File Than Add New file in place of respective file
        input = new Scanner(System.in);
        System.out.print("Enter the Engine Number to Update >>>> ");
        String engine_number = input.nextLine();

        try {
            File fileDelete = new File(engine_number + ".txt");
            if (fileDelete.delete()) {
                System.out.print("");
            }
        } catch (Exception e) {
            System.out.println(">>>>> Unable to Delete the File <<<<<");
            e.printStackTrace();
        }
        System.out.println(" >>>>> Now Add Updated Info for this Train <<<<<<");
        addTrain();

    }

    public static void deleteTrain() {
        input = new Scanner(System.in);
        System.out.println("Enter the Engine Number to Delete: ");
        String engine_numbe = input.nextLine();

        try {
            File fileDelete = new File(engine_numbe + ".txt");
            if (fileDelete.delete()) {
                System.out.println(fileDelete.getName() + ">>>>> File deleted successfully >>>>>");
            }
        } catch (Exception e) {
            System.out.println(">>>>> Unable to Delete the File: ");
            e.printStackTrace();
        }

        menuForTrain();
    }

    // >>>>>>>>>>>>>>>>>> Journey Menu <<<<<<<<<<<<<<<<<<<<<<<<//

    public static void menuForJourney() {
        int option;
        do {
            System.out.println(">>>>>>> Choose your preference <<<<<<<<\n" +
                    "1. Add Journey\n" +
                    "2. View Journey\n" +
                    "3. Update Journey\n" +
                    "4. Delete Journey\n" +
                    "5. View Schedule\n" +
                    "6. EXIT\n" +
                    "0. Back\n" +
                    ">>>>");
            option = input.nextInt();
            if (option == 1) {
                addJourney();
            } else if (option == 2) {
                viewJourney();
            }
            else if (option == 3) {
                updateJourney();
            } else if (option == 4) {
                updateJourney();
            }
            else if (option == 5) {
                ticketsDetails();
            } else if (option == 6) {
                System.out.println(">>>>>>>>>>>>> EXIT <<<<<<<<<<<<<<<<<<<<");
                System.exit(option);
            } else if (option == 0) {
                menuForAdmin();
            }else if (option > 6) {
                System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
            }
        }
        while (option != 0) ;
    }

    // >>>>>>>>>>>>>>> Methods For Journey <<<<<<<<<<<<<<<<<<<<<<<<<//
    public static void addJourney() {
        Scanner str = new Scanner(System.in);

        input = new Scanner(System.in);
        System.out.print("Train Name >>> ");
        String trainName = input.nextLine();

        System.out.print("Route/Departure >>> ");
        String route = str.nextLine();

        System.out.print("Distance(km) >>> ");
        String distance = input.nextLine();

        System.out.print("Timing >>> ");
        String time = input.nextLine();

        System.out.print("Date of Departure >>> ");
        String date = input.nextLine();

        //Creating new File
        File adminData = new File(route + ".txt");
        try {
            adminData.createNewFile();
        } catch (IOException e) {
            System.out.println(">>>>>>>>>>>>>> Unable To create a file <<<<<<<<<<<<");
            e.printStackTrace();
        }
        //Writing into the File
        try {
            FileWriter adminWriter = new FileWriter(route + ".txt");
            adminWriter.write("Train Name >>> " + trainName + "\n");
            adminWriter.write("Route/Departure >>> " + route + "\n");
            adminWriter.write("Distance(km) >>> " + distance + "\n");
            adminWriter.write("Timing >>> " + time + "\n");
            adminWriter.write("Date of Departure >>> " + date + "\n");
            adminWriter.close();
        } catch (IOException e) {
            System.out.println(">>>>> Unable To Write in a File: ");
            e.printStackTrace();
        }
        menuForJourney();
    }
    // Check Data For Passengers
    public static void ticketsDetails(){
        Scanner str = new Scanner(System.in);
        System.out.print("Enter Route Number: ");
        String details = str.nextLine();
        File Reader = new File(details + ".txt");
        Scanner input = null;
        try {
            input = new Scanner(Reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
    }


    public static void viewJourney() {
        Scanner str = new Scanner(System.in);
        System.out.print("Enter Route Number: ");
        String route = str.nextLine();
        File Reader = new File(route + ".txt");
        Scanner input = null;
        try {
            input = new Scanner(Reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
        menuForJourney();
    }

    public static void updateJourney() {
        // First, delete the previous file then add new file
        input = new Scanner(System.in);
        System.out.println("Enter the Engine Number to Delete: ");
        String num = input.nextLine();

        try {
            File fileDelete = new File(num + ".txt");
            if (fileDelete.delete()) {
                System.out.println(fileDelete.getName() + ">>>>> File deleted successfully >>>>>");
            }
        } catch (Exception e) {
            System.out.println(">>>>> Unable to Delete the File: ");
            e.printStackTrace();
        }
        System.out.println(" >>>>>>> Now Add Updated Journey Info <<<<<<<<<<<<");
        addJourney();
    }

    public static void deleteJourney() {
        input = new Scanner(System.in);
        System.out.println("Enter the Engine Number to Delete: ");
        String num = input.nextLine();

        try {
            File fileDelete = new File(num + ".txt");
            if (fileDelete.delete()) {
                System.out.println(fileDelete.getName() + ">>>>> File deleted successfully >>>>>");
            }
        } catch (Exception e) {
            System.out.println(">>>>> Unable to Delete the File: ");
            e.printStackTrace();
        }
        menuForJourney();
    }


          //>>>>>>>>>>>>>>>>>>>> PASSENGER MODULE >>>>>>>>>>>>>>>>>>>>>>>>

    public static void passenger() throws IOException {
        input = new Scanner(System.in);

        int index = 0;
        Files.readAllLines(Paths.get("Passengers Passwords.txt")).get(index).equalsIgnoreCase("PASSENGERS");
        Files.readAllLines(Paths.get("Passengers User Names.txt")).get(index).equalsIgnoreCase("PASSENGERS");
        System.out.println("\n<====>  WELCOME TO THE PASSENGER PANEL  <====>\n");
        System.out.print("Do you have an Account?Yes/No: ");
        String passenger_answer = input.nextLine();

        try {
            switch (passenger_answer) {
                case "Yes":
                    System.out.println("\n Please Enter Your Credentials!\n");

                    //Login info of Passenger
                    System.out.print("Enter Your User_Name: ");
                    String passenger_user_name = input.nextLine();

                    System.out.print("Enter Your Password: ");
                    String passenger_password = input.nextLine();

                    boolean pas_match = false;
                    try {
                        while (index < 10) {
                            if (Files.readAllLines(Paths.get("Passengers User Names.txt")).get(index).equals(passenger_user_name) && Files.readAllLines(Paths.get("Passengers Passwords.txt")).get(index).equals(passenger_password)) {
                                pas_match = true;
                                menuForPassenger();
                                break;
                            } else {
                                //System.out.println("False");
                                index++;
                            }
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println(" >>>>>>>>>>>>>> Invalid Input <<<<<<<<<<<<< ");
                        e.printStackTrace();
                    }
                    System.out.println("\n >>>>>>>>>>>>>> Invalid Credentials >>>>>>>>>>>>>> \n" +
                            " >>>>>>>>>>>>>> PASSENGER Not Found >>>>>>>>>>>>>> \n" +
                            " >>>>>>>>>>>>>> PLEASE ENTER AGAIN >>>>>>>>>>>>>> \n");
                    passenger();

                case "No": {
                    passenger_signUp();
                }
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input! Enter Only In String Format");
        }
    }

    public static void passenger_signUp() {
        input = new Scanner(System.in);
        System.out.println("TO CREATE NEW ACCOUNT ANSWER THE BELOW QUESTION FREQUENTLY \n");

        System.out.print("Enter Your Name: ");
        String passenger_name = input.nextLine();

        System.out.print("Create a Password: ");
        String passenger_password = input.nextLine();

        System.out.print("Enter Your CNIC Number: ");
        String passenger_cnic = input.nextLine();

        System.out.print("Enter Your Age: ");
        String passenger_age = input.nextLine();

        System.out.print("Enter Your Phone Number: ");
        String passenger_phone_number = input.nextLine();

        System.out.println("Account Created Successfully");

        try {
            FileWriter passengerWriter = new FileWriter("Passenger SignUp Data.txt");
            FileWriter passengerSignUpUserName = new FileWriter("Passengers User Names.txt");
            FileWriter passengerSignUpPassword = new FileWriter("Passengers Passwords.txt");
            passengerWriter.write("Passenger Name >>> " + passenger_name + "\n");
            passengerSignUpUserName.write(passenger_name + "\n");
            passengerSignUpPassword.write(passenger_password + "\n");
            passengerWriter.write("Passenger CNIC >>> " + passenger_cnic + "\n");
            passengerWriter.write("Passenger Age >>> " + passenger_age + "\n");
            passengerWriter.write("Passenger Phone >>> " + passenger_phone_number + "\n");
            passengerWriter.close();
        } catch (IOException e) {
            System.out.println(">>>>> Unable To Write in a File: ");
            e.printStackTrace();
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>> Menu For Passenger <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void menuForPassenger() {
        int option;
        do {
            System.out.println(">>>>>>> Choose your preference <<<<<<<<");
            System.out.println("1. View Schedule");
            System.out.println("2. Booking");
            System.out.println("3. Exit");
            System.out.print(">>>> ");
            option = input.nextInt();
            if (option == 1) {
                viewSchedule();
            }else if (option == 2) {
                booking();
            } else if (option == 3) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>   EXIT  <<<<<<<<<<<<<<<<");
                System.exit(option);
            }else if (option > 3) {
                System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
            }
        }
        while (option != 0);
    }

    public static void viewSchedule() {
        Scanner str = new Scanner(System.in);
        System.out.print("Enter Route(from-to) >>> ");
        String route = str.nextLine();
        File Reader = new File(route + ".txt");
        if (Reader.exists()){
            Scanner input = null;
            try {
                input = new Scanner(Reader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
        }else{
            System.out.println(">>>>>>>>>>> Sorry! No Train is Available for this Route <<<<<<<<<<<<<<");
        }

        menuForPassenger();
    }


    // >>>>>>>>>>>>>>>>     BOOKING    MODULE     <<<<<<<<<<<<<<<<<<<<


    public static void booking() {
        int option;
        do {
            System.out.println(">>>>>>> Choose your preference <<<<<<<<\n" +
                    "1. Book Ticket\n" +
                    "2. Cancel Ticket\n" +
                    "3. Exit\n" +
                    ">>>>");
            option = input.nextInt();
            if (option == 1) {
                bookTicket();
            } else if (option == 2) {
                cancelTicket();
            } else if (option == 3) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>   EXIT  <<<<<<<<<<<<<<<<");
                System.exit(option);
            }else if (option > 3) {
                System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
            }
        }
        while (option != 0);
    }

    public static void bookTicket() {
        Desktop desk = Desktop.getDesktop();
        input = new Scanner(System.in);
        System.out.print("Your Name >>> ");
        String name = input.nextLine();
        System.out.print("CNIC Number >>> ");
        String cnic = input.nextLine();
        System.out.print("Phone Number >>> ");
        String phoneNum = input.nextLine();
        System.out.print("Destination-to-from >>> ");
        String destination = input.nextLine();
        System.out.print("Date >>> ");
        String date = input.nextLine();

        //Creating New file
        try {
            File passengerFile = new File(cnic + ".txt");
            passengerFile.createNewFile();
        } catch (IOException e) {
            System.out.println(">>>>> Unable To Book a Ticket, Please Try Again Later <<<<<<");
            e.printStackTrace();
        }
        // Writing data into File
        try {
            FileWriter passengerWriter = new FileWriter(cnic + ".txt");
            passengerWriter.write("Your Name >>> " + name + "\n");
            passengerWriter.write("CNIC Number >>> " + cnic + "\n");
            passengerWriter.write("Phone Number >>> " + phoneNum + "\n");
            passengerWriter.write("Destination-to-from >>> " + destination + "\n");
            passengerWriter.write("Date >>> " + date + "\n");
            passengerWriter.close();
            System.out.println("\n Your information has been saved. Now select your payment method for further procedure. \n");
        } catch (IOException e) {
            System.out.println(">>>>> Unable to Save Info! Please Try Again <<<<<<<<<");
            e.printStackTrace();
            bookTicket();
        }

        //Creating New file For Admin
        try {
            File passengerFile = new File( "DataChecker.txt");
            passengerFile.createNewFile();
        } catch (IOException e) {
            System.out.println(">>>>> Unable To Book a Ticket, Please Try Again Later <<<<<<");
            e.printStackTrace();
        }
        //Writing File For Admin
        try {
            FileWriter passengerWriter = new FileWriter( "DataChecker.txt", true);
            passengerWriter.write("Your Name >>> " + name + "\n");
            passengerWriter.write("CNIC Number >>> " + cnic + "\n");
            passengerWriter.write("Phone Number >>> " + phoneNum + "\n");
            passengerWriter.write("Destination-to-from >>> " + destination + "\n");
            passengerWriter.write("Date >>> " + date + "\n");
            passengerWriter.write("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n ");
            passengerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Payment
        int option;
        do {
            System.out.println("\\n >>>>>>> Choose your preference <<<<<<<< \\n\n" +
                    "1. Jazz Cash\n" +
                    "2. Easypaisa\n" +
                    "3. Bank\n" +
                    "4. Exit\n" +
                    ">>>>");
            option = input.nextInt();
            if (option == 1) {
                try {
                    desk.browse(new URI("https://www.jazzcash.com.pk"));
                    System.out.println(">>>>> THANK YOU FOR USING TRAIN MANAGEMENT SYSTEM <<<<<");
                    receipt();
                    break;
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            } else if (option == 2) {
                try {
                    desk.browse(new URI("https://easypaisa.com.pk"));
                    System.out.println(">>>>> THANK YOU FOR USING TRAIN MANAGEMENT SYSTEM <<<<<");
                    receipt();
                    break;
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }

            } else if (option == 3) {
                int optionInside;
                do {
                    System.out.println("\\n >>>>>>> Choose your preference <<<<<<<< \\n\n" +
                            "1. National Bank Of Pakistan\n" +
                            "2. Habib Bank Of Pakistan \n" +
                            "3. Allied Bank of Pakistan\n" +
                            "4. Exit\n" +
                            ">>>>");
                    optionInside = input.nextInt();
                    if (optionInside == 1){
                        try {
                            desk.browse(new URI("https://ibanking.nbp.com.pk/login"));
                            receipt();
                            break;
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }else if (optionInside == 2 ){
                        try {
                            desk.browse(new URI("https://www.hblibank.com.pk/Registeration/Register"));
                            System.out.println(">>>>> THANK YOU FOR USING TRAIN MANAGEMENT SYSTEM <<<<<");
                            receipt();
                            break;
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    } else if (optionInside == 3){
                        try {
                            desk.browse(new URI("https://login.abl.com"));
                            System.out.println(">>>>> THANK YOU FOR USING TRAIN MANAGEMENT SYSTEM <<<<<");
                            receipt();
                            break;
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    } else if (optionInside == 4){
                        System.out.println(" >>>>>>>>>>>>>>>>>>>>   EXIT  <<<<<<<<<<<<<<<<");
                        System.exit(optionInside);
                    }else if (optionInside > 4) {
                        System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
                    }
                } while (option != 0);


            } else if (option == 4) {
                System.out.println(" >>>>>>>>>>>>>>>>>>>>   EXIT  <<<<<<<<<<<<<<<<");
                System.exit(option);
            } else if (option > 4) {
                System.out.println(">>>>>>>>> Invalid Option <<<<<<<<<<<<<");
            }
        } while (option != 0);

    }
    public static void cancelTicket ()
    {
        input = new Scanner(System.in);
        System.out.println("Enter your CNIC to cancel your Ticket >>> ");
        String cnic = input.nextLine();

        try {
            File fileDelete = new File(cnic + ".txt");
            if (fileDelete.delete()) {
                System.out.println(fileDelete.getName() + ">>>>> File deleted successfully >>>>>");
            }
        } catch (Exception e) {
            System.out.println(">>>>> Unable to find your CNIC To cancel your Ticket! Please Try Again ");
            e.printStackTrace();
            System.out.println(" >>>> Now again add info To cancel your ticket <<<<<");
            cancelTicket();
        }
    }
    public static void receipt() {
        Scanner str = new Scanner(System.in);
        System.out.print("Re-Confirm your CNIC to get receipt >>> ");
        String cnic = str.nextLine();
        File Reader = new File(cnic + ".txt");
        Scanner input = null;
        try {
            input = new Scanner(Reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println(line);
        }
        int num = Integer.parseInt(cnic);
        System.exit(num);
    }
}
//Admin close

