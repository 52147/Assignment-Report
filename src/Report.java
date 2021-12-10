
import java.lang.Double;
import java.math.BigDecimal;



/*
 * Report with the input numbers.
 */
public class Report {

	public String name;
	public Owner owner;
	public Record[] records;
	public int size;
	public int sizeOfRecord;
	public int indexOfRecord;
	public BigDecimal highest;
	public BigDecimal lowest;
	public BigDecimal total;
	public BigDecimal average;

    public Report(int size, int sizeOfRecord) {

        owner = new Owner();

        this.size = size;
        this.sizeOfRecord = sizeOfRecord;
        indexOfRecord = -1;
        records = new Record[size];

        highest = new BigDecimal(Double.MIN_VALUE);
        lowest = new BigDecimal(Double.MAX_VALUE);
        total = new BigDecimal(0);
        average = new BigDecimal(0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    /*
     * Extend the array size of the report.
     */
    private void extendSize() {
        Record[] temp = new Record[records.length + size];
        System.arraycopy(records, 0, temp, 0, records.length);
        records = temp;
    }

    /*
     * Add a record to the report.
     */
    public void addRecord(Record record) {
        indexOfRecord += 1;
        if (indexOfRecord >= records.length) {
            extendSize();
        }

        records[indexOfRecord] = record;

        if (highest.compareTo(record.getHighest()) == -1) {
            highest = record.getHighest();
        }

        if (lowest.compareTo(record.getLowest()) == 1) {
            lowest = record.getLowest();
        }

        total = total.add(record.getTotal());

        average = total.divide(
                new BigDecimal((indexOfRecord + 1) * sizeOfRecord), 2,
                java.math.RoundingMode.HALF_UP);
    }

    /*
     * Print the report.
     */
    public void printReport() {

        System.out.println("========================================================================================================================");
        System.out.println("Owner's Name: " + owner.getFirstName() + " " + owner.getLastName());
        System.out.println("Report  Name: " + name);
        System.out.println("-----------------------------------------------------------+----------------+---------------+-----------+---------------");

        System.out.println("                       Input Numbers                       | Highest Number | Lowest Number |   Total   | Average Number");
        System.out.println("-----------------------------------------------------------+----------------+---------------+-----------+---------------");

        int numberStringLength = 0;
        for (int i = 0; i <= indexOfRecord; i++) {

            numberStringLength = 0;
            for (int j = 0; j < sizeOfRecord; j++) {

                System.out.print(records[i].getNumbers()[j]);
                numberStringLength += records[i].getNumbers()[j].toString().length();

                if (j < sizeOfRecord - 1) {
                    System.out.print(", ");
                    numberStringLength += 2;
                } else {
                    System.out.print(" ");
                    numberStringLength += 1;
                }
            }

            for (int k = 0; k < 59 - numberStringLength; k++) {
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(records[i].getHighest());
            
            for (int k = 0; k < 15 - records[i].getHighest().toString().length(); k++) {
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(records[i].getLowest());
            
            for (int k = 0; k < 14 - records[i].getLowest().toString().length(); k++) {
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(records[i].getTotal());
            
            for (int k = 0; k < 10 - records[i].getTotal().toString().length(); k++) {
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.println(records[i].getAverage());
        }

        System.out.println("-----------------------------------------------------------+----------------+---------------+-----------+---------------");
        System.out.println("Grand    Total: " + total);
        System.out.println("Highest Number: " + (indexOfRecord < 0 ? 0 : highest));
        System.out.println("Lowest  Number: " + (indexOfRecord < 0 ? 0 : lowest));
        System.out.println("Average Number: " + average);
        System.out.println("=========================================================================================================================");
    }
}
