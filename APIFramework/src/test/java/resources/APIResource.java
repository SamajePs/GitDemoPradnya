package resources;
//collection of constant methods
public enum APIResource {
    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    updatePlaceAPI("/maps/api/place/update/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String path;

    APIResource(String path) {
        this.path=path;
    }
    public String getResource()
    {
        return path;
    }
}
