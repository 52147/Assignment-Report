import java.lang.Double;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Check {

    public static void main(String[] args) {

        // a report with default 10 records, each record has 7 numbers
        int sizeOfReport = 10;
        int sizeOfRecord = 7;
         Report report = new Report(sizeOfReport, sizeOfRecord);

        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.print("Please enter your frist name: ");
            input = scanner.nextLine();

            if (input.trim().length() == 0) {
                continue;
            }

            if (!report.getOwner().checkName(input)) {
                System.out.println("Warning: The name should contain only alphabet characters.");
                continue;
            }

            report.getOwner().setFirstName(input);
            break;
        }

        while (true) {
            System.out.println();
            System.out.print("Please enter your last name : ");
            input = scanner.nextLine();

            if (input.trim().length() == 0) {
                continue;
            }

            if (!report.getOwner().checkName(input)) {
                System.out.println("Warning: The name should contain only alphabet characters.");
                continue;
            }

            report.getOwner().setLastName(input);
            break;
        }

        while (true) {
            System.out.println();
            System.out.print("Please enter the report name: ");
            input = scanner.nextLine();

            if (input.trim().length() == 0) {
                continue;
            }

            report.setName(input);
            break;
        }

        String[] values;
        BigDecimal[] numbers = new BigDecimal[sizeOfRecord];
        int index;
        int count = 0;
        boolean hasDouble;
        boolean hasInteger;

        while (true) {
            System.out.println();
            System.out.print("Please enter " + sizeOfRecord + " numbers separated by space (-1 to exit): ");
            input = scanner.nextLine();

            if (input.trim().equals("-1")) {
                break;
            }

            values = input.split(" ");

            if (values.length != sizeOfRecord) {
                System.out.println("Warning: Please enter " + sizeOfRecord + " numbers.");
                continue;
            }

            Arrays.fill(numbers, new BigDecimal(0));

            index = 0;
            hasDouble = false;
            hasInteger = false;
            for (; index < values.length; index++) {
                String value = values[index];

                try {
                    // check if the value is a number
                    Double.parseDouble(value);

                    numbers[index] = new BigDecimal(value);

                    if (value.contains(".")
                            && Integer.parseInt(value.split("\\.")[1]) > 0) {
                        hasDouble = true;
                    } else {
                        hasInteger = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Warning: \"" + value + "\" is not a number.");
                    break;
                }
            }

            if (index < values.length) {
                // do not get 7 numbers
                continue;
            }

            if (!hasDouble) {
                System.out.println("Warning: Enter at least one double.");
                continue;
            }

            if (!hasInteger) {
                System.out.println("Warning: Enter at least one integer.");
                continue;
            }

            Record record = new Record(sizeOfRecord);
            record.addNumbers(numbers);
            report.addRecord(record);
            count++;

            System.out.println("Highest Number: " + record.getHighest());
            System.out.println("Lowest  Number: " + record.getLowest());
            System.out.println("         Total: " + record.getTotal());
            System.out.println("Average Number: " + record.getAverage());

            switch (count) {
            case 1:
                System.out.println("There is " + count + " record in the report.");
                break;
            default:
                System.out.println("There are " + count + " records in the report.");
            }
            System.out.println("");
        }

        System.out.println();
        // print final report
        report.printReport();

        scanner.close();
    }
}
