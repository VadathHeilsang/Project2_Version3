import java.util.ArrayList;

public class SalesVan extends ArrayList{
    public ArrayList<BikePart> vanList;

    public SalesVan(ArrayList<BikePart> vanList){
        this.vanList=vanList;
    }

    public void deliverToWarehouse(String warehouseInventoryName){

    }

    public void transferToSalesVan(SalesVan altSalesVan){
        for (int i = 0; i < vanList.size(); i++) {
            altSalesVan.add(vanList.get(i));
        }
    }
}
