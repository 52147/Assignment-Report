
/**
 * Student name: Shou Tzu, Han
 * Assignment  : #2
 * Date        : 2021/10/04
 */



/**
 * Report owner with first name and last name.
 */
class Owner {
	public String firstName;
	public String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = trimName(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = trimName(lastName);
    }

    /*
     * Check if the name contains only alphabet characters.
     */
    public boolean checkName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    /*
     * Trim the name to keep only alphabet characters.
     */
    private String trimName(String name) {
        return name.replaceAll("[^a-zA-Z]", "");
    }
}
