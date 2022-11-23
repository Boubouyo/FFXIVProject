package Core;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final List<Location> locations;
    
    public Map(){
        this.locations = new ArrayList<>(); 
    }
    
    
    
    public void addLocations(Location newLoc){
        locations.add(newLoc);
    }
}
