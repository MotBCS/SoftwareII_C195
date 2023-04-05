package Models;

/** --------------------------------------------------------------------------------------------------------------------------- */

/**This class is for constructing the customer model */
public class ContactModel {

    /** Class Variables */
    private int contactId;
    private String contactName;
    private String contactEmail;

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /** Contact Model Constructor*/
    public ContactModel(
            int contactId,
            String contactName,
            String contactEmail
    ){
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /** Contact Model Constructor*/
    public ContactModel(
            int contactId,
            String contactName
    ){
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */
    /** Getter and Setter */

    /**
     * @return Contact Id
     * */
    public int getContactId() {
        return contactId;
    }
    /**
     * @param contactId Contact Id
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Contact Name
     * */
    public String getContactName() {
        return contactName;
    }
    /**
     * @param contactName Contact Name
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Contact Email Address
     * */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * @param contactEmail Contact Email Address
     * */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */
    /** Integer (ContactID) + String (Contact Name)*/
    /**
     * @return Contact ID and Contact Name Concatenated
     * */
    @Override
    public String toString(){
        return (contactName);
    }
}
