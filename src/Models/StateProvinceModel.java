package Models;

public class StateProvinceModel {
    private int stateProvinceId;
    private String stateProvinceName;
    private int countryId;

    public StateProvinceModel(
            int stateProvinceId,
            String stateProvinceName,
            int countryId
    ){
        this.stateProvinceId = stateProvinceId;
        this.stateProvinceName = stateProvinceName;
        this.countryId = countryId;
    }

    public StateProvinceModel(
            int stateProvinceId,
            String stateProvinceName
    ){
        this.stateProvinceId = stateProvinceId;
        this.stateProvinceName = stateProvinceName;
    }

    public int getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(int stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public String getStateProvinceName() {
        return stateProvinceName;
    }

    public void setStateProvinceName(String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString(){
        return (stateProvinceName);
    }
}
