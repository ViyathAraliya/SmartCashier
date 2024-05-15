package araliya.pointOfSales.dtos;

import lombok.Data;

@Data
public class Transaction_Item_Dto {

    private Long itemID;
    //transactionID will be set in the service layer
    private Long qty;
    private Long amount;

}
