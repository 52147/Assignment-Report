
import java.lang.Double;
import java.math.BigDecimal;
import java.util.Arrays;



/**
 * Record for a line of numeric input.
 */
public class Record {

	public int size;
	public BigDecimal[] numbers;
	public BigDecimal highest;
	public BigDecimal lowest;
	public BigDecimal total;
	public BigDecimal average;

    /*
     * Constructor with the number of elements in a record.
     */
    public Record(int size) {
        this.size = size;
        numbers = new BigDecimal[size];
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getAverage() {
        return average;
    }

    /*
     * Add an array of numbers to the record.
     */
    public void addNumbers(BigDecimal[] values) {

        // default values
        Arrays.fill(numbers, new BigDecimal(0));
        highest = new BigDecimal(Double.MIN_VALUE);
        lowest = new BigDecimal(Double.MAX_VALUE);
        total = new BigDecimal(0);
        average = new BigDecimal(0);

        for (int i = 0; i < values.length && i < size; i++) {

            BigDecimal value = values[i];

            numbers[i] = value;

            if (highest.compareTo(value) == -1) {
                highest = value;
            }

            if (lowest.compareTo(value) == 1) {
                lowest = value;
            }

            total = total.add(value);
        }

        average = total.divide(new BigDecimal(size), 2,
                java.math.RoundingMode.HALF_UP);
    }

    /*
     * Get the numbers of the record.
     */
    public BigDecimal[] getNumbers() {
        return numbers;
    }
}
