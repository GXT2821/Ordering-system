package myOrder.vo;

public class mydishes {
	int dishesId;
	String dishesName;
	String dishesDiscript;
	String dishesImg;
	String dishesTxt;
	int recommend;
	float dishesPrice;
	int dstate;
	public int getDstate() {
		return dstate;
	}
	public void setDstate(int dstate) {
		this.dstate = dstate;
	}
	public int getDishesId() {
		return dishesId;
	}
	public void setDishesId(int dishesId) {
		this.dishesId = dishesId;
	}
	public String getDishesName() {
		return dishesName;
	}
	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}
	public String getDishesDiscript() {
		return dishesDiscript;
	}
	public void setDishesDiscript(String dishesDiscript) {
		this.dishesDiscript = dishesDiscript;
	}
	public String getDishesImg() {
		return dishesImg;
	}
	public void setDishesImg(String dishesImg) {
		this.dishesImg = dishesImg;
	}
	public String getDishesTxt() {
		return dishesTxt;
	}
	public void setDishesTxt(String dishesTxt) {
		this.dishesTxt = dishesTxt;
	}
	
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public float getDishesPrice() {
		return dishesPrice;
	}
	public void setDishesPrice(float dishesPrice) {
		this.dishesPrice = dishesPrice;
	}

}
