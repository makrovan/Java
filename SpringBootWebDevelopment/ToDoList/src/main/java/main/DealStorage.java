package main;

import response.Deal;

import java.util.*;

public class DealStorage
{
    private static int currentId = 1;
    private static Map<Integer, Deal> deals = Collections.synchronizedMap(new HashMap<>());

    public static List<Deal> getAllDeals(){
        ArrayList<Deal> dealList = new ArrayList<>(deals.values());
        return dealList;
    }

    public static int addDeals(Deal deal){
        int id = currentId++;
        deal.setId(id);
        deals.put(id, deal);
        return id;
    }

    public static Deal getDeal(int dealId){
        if (deals.containsKey(dealId)) {
            return deals.get(dealId);
        }
        return null;
    }

    public static boolean deleteDeal(int dealId){
        if (deals.containsKey(dealId)) {
            deals.remove(dealId);
            return true;
        }
        return false;
    }

    public static boolean updateDeal(int dealId, Deal deal){
        if (deals.containsKey(dealId)) {
            deals.replace(dealId, deal);
            return true;
        }
        return false;
    }

    public static void deleteAllDeals(){
        deals.clear();
    }
}
