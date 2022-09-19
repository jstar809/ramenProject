package model;

public class ProductVO {
   private int num;
   private int cnt;
   private int price;
   
   private String ramen;
   private String toping;
   private String side;
   private String water;
   private String spicy;
   
   public String getRamen() {
      return ramen;
   }
   public void setRamen(String ramen) {
      this.ramen = ramen;
   }
   public String getToping() {
      return toping;
   }
   public void setToping(String toping) {
      this.toping = toping;
   }
   public String getSide() {
      return side;
   }
   public void setSide(String side) {
      this.side = side;
   }
   public String getWater() {
      return water;
   }
   public void setWater(String water) {
      this.water = water;
   }
   public String getSpicy() {
      return spicy;
   }
   public void setSpicy(String spicy) {
      this.spicy = spicy;
   }
   //getter setter
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public int getCnt() {
      return cnt;
   }
   public void setCnt(int cnt) {
      this.cnt = cnt;
   }
@Override
public String toString() {
   return "ProductVO [num=" + num + ", cnt=" + cnt + ", price=" + price + ", ramen=" + ramen + ", toping=" + toping
         + ", side=" + side + ", water=" + water + ", spicy=" + spicy + "]";
}
   
   
  
   
}