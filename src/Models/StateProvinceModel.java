package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the StateProvince model */
public class StateProvinceModel {

    /** Class variables */
    private int stateProvinceId;
    private String stateProvinceName;
    private int countryId;

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** StateProvince Model Constructor */
    public StateProvinceModel(
            int stateProvinceId,
            String stateProvinceName,
            int countryId
    ){
        this.stateProvinceId = stateProvinceId;
        this.stateProvinceName = stateProvinceName;
        this.countryId = countryId;
    }

    /** StateProvince Model Constructor */
    public StateProvinceModel(
            int stateProvinceId,
            String stateProvinceName
    ){
        this.stateProvinceId = stateProvinceId;
        this.stateProvinceName = stateProvinceName;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */

    /**
     * @return state/province Id
     * */
    public int getStateProvinceId() {
        return stateProvinceId;
    }
    /**
     * @param stateProvinceId state/province Id
     * */
    public void setStateProvinceId(int stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return state/province Name
     * */
    public String getStateProvinceName() {
        return stateProvinceName;
    }
    /**
     * @param stateProvinceName state/province name
     * */
    public void setStateProvinceName(String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return country Id
     * */
    public int getCountryId() {
        return countryId;
    }
    /**
     * @param countryId country Id
     * */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**
     * String state/province name
     * */
    @Override
    public String toString(){
        return (stateProvinceName);
    }
}
