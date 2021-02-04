package bt.pb.cart;

import java.text.NumberFormat;

public class ItemInCart
{
    private String itemName;
    private double cprice;
    private double sprice;
    private int qty;

    // -------------------------------------------------------
    //  Create a new item with the given attributes.
    // -------------------------------------------------------
    public ItemInCart (String itemName, double cPrice, double sPrice, int qty)
    {
      this.itemName = itemName;
      this.cprice = cPrice;
      this.sprice = sPrice;
      this.qty = qty;
    }

    // -------------------------------------------------------
    //   Return a string with the information about the itemss
    // -------------------------------------------------------
    public String toString ()
    {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      return (itemName + "\t" + fmt.format(sprice) + "\t" + qty + "\t"
            + fmt.format(sprice*qty));
    }

    // -------------------------------------------------
    //   Returns the unit price of the item
    // -------------------------------------------------
    public double getCostPrice()
    {
      return cprice;
    }
    // -------------------------------------------------
    //   Returns the unit price of the item
    // -
    public double getSellingPrice()
    {
      return sprice;
    }

    // -------------------------------------------------
    //   Returns the name of the item
    // -------------------------------------------------
    public String getItemName()
    {
      return itemName;
    }

    // -------------------------------------------------
    //   Returns the quantity of the item
    // -------------------------------------------------
    public int getQuantity()
    {
      return qty;
    }
} 