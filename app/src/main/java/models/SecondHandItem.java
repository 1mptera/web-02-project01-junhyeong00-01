package models;

public class SecondHandItem {
    public static final String[] CATEGORY = {"-카테고리 선택-", "디지털기기", "생활가전", "가구", "주방","식품", "미용", "의류", "게임", "도서", "스포츠", "기타"};
    private long price;
    private String category;

    public SecondHandItem(long price, String category) {
        this.price = price;
        this.category = category;
    }

    public long price() {
        return price;
    }

    public String category() {
        return category;
    }
}
