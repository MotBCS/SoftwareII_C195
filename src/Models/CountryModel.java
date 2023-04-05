package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the country model */
public class CountryModel {

    /** Class Variables */
    private int countryId;
    private String countryName;

    /** Country Model Constructor */
    public CountryModel(
            int countryId,
            String countryName
    ){
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */

    /**
     * @return Country Id
     * */
    public int getCountryId() {
        return countryId;
    }
    /**
     * @param countryId Country Id
     * */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Country Name
     * */
    public String getCountryName() {
        return countryName;
    }
    /**
     * @param countryName Country Name
     * */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */
    /**
     * @return (String) Country Name
     * */
    @Override
    public String toString(){
        return (countryName);
    }
}
