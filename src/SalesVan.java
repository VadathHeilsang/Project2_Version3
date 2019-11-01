import java.util.ArrayList;

public class SalesVan extends ArrayList{
    public ArrayList<BikePart> vanList;

    public SalesVan(ArrayList<BikePart> vanList){
        this.vanList=vanList;
    }

    public void getFromWarehouse(ArrayList<BikePart> warehouseInventoryName){
        for (int i = 0; i < warehouseInventoryName.size(); i++) {
            vanList.add(warehouseInventoryName.get(i));
            warehouseInventoryName.remove(i);
        }
    }

    public void transferTo(ArrayList<BikePart> altBikePartArrayList){
        for (int i = 0; i < vanList.size(); i++) {
            altBikePartArrayList.add(vanList.get(i));
            vanList.remove(i);
        }
    }
}
