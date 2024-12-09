import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class  TextUi {
        private Scanner scan = new Scanner(System.in);
        private Login login;
         static TextUi ui;

        TextUi(Login login){
           this.login = login;

        }


        public String displayMsg(String msg){
            System.out.println(msg);
            return msg;
        }

        public boolean promptBinary(String msg, String responseForTrue, String responseForFalse){
            String input = promptText(msg);
            if(input.equalsIgnoreCase(responseForTrue)){
                Login login = new Login();
                login.createUser();
                return true;


            }
            else if(input.equalsIgnoreCase(responseForFalse)){

                return false;
            }
            return promptBinary(msg, responseForTrue, responseForFalse);
        }
        /*public boolean promptBinary2(String msg){
            String input = promptText(msg);
            if(input.equalsIgnoreCase("Movies")){
                return true;
            }
            else if(input.equalsIgnoreCase("Series")){
                return false;
            }
            return promptBinary(msg);
        }
        public boolean promptBinary3(String msg){
            String input = promptText(msg);
            if(input.equalsIgnoreCase("Genre")){
                return true;
            }
            else if(input.equalsIgnoreCase("Title")){
                return false;
            }
            return promptBinary(msg);
        }
    */
        public int promptNumeric(String msg) {
            System.out.println(msg);              // Stille brugeren et spørgsmål
            String input = scan.nextLine();
            int number;
            // Give brugere et sted at placere sit svar og vente på svaret
            try {
                number = Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                displayMsg("Please type a number");
                number = promptNumeric(msg);
            }
            return number;
        }

        public String promptText(String msg){
            System.out.println(msg);//Stille brugeren et spørgsmål
            String input = scan.nextLine();
            return input;
        }

        public ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg){
            ArrayList<String> choices = new ArrayList<String>();  //Lave en beholder til at gemme brugerens valg
            int count = 1;
            while(choices.size() < limit){             //tjekke om brugeren skal vælge flere drinks
                String choice = promptText(count+":");
                choices.add(choice);
                count++;
            }
            return choices;
        }

        public void displayList(ArrayList<String> options, String msg){
            System.out.println("*******");
            System.out.println(msg);
            System.out.println("*******");

            int i = 1;

            for (String option : options) {
                System.out.println(i+": "+option);
                i++;
            }
        }
    }

