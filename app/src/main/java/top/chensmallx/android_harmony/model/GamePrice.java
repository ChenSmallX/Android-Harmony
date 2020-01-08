package top.chensmallx.android_harmony.model;

import com.google.gson.annotations.SerializedName;

public class GamePrice {
    @SerializedName(value="Region")
    private String region;

    @SerializedName(value="HasChinese")
    private boolean hasChinese;

    @SerializedName(value="IsLowestPrice")
    private boolean isLowestPrice;

    @SerializedName(value="IsOnSale")
    private boolean isOnSale;

    @SerializedName(value="SaleRate")
    private double saleRate;

    @SerializedName(value="EndDate")
    private int endDate;

    @SerializedName(value="PriceCNY")
    private double priceCNY;

    @SerializedName(value="PriceLocal")
    private double priceLocal;

    @SerializedName(value="GameLanguage")
    private String[] gameLanguage;

    @SerializedName(value="ReleaseDate")
    private String releaseDate;

    @SerializedName(value="RegionComment")
    private String regionComment;

    @SerializedName(value="HistoryLowestPrice")
    private double historyLowestPrice;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isHasChinese() {
        return hasChinese;
    }

    public void setHasChinese(boolean hasChinese) {
        this.hasChinese = hasChinese;
    }

    public boolean isLowestPrice() {
        return isLowestPrice;
    }

    public void setLowestPrice(boolean lowestPrice) {
        isLowestPrice = lowestPrice;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public double getPriceCNY() {
        return priceCNY;
    }

    public void setPriceCNY(double priceCNY) {
        this.priceCNY = priceCNY;
    }

    public double getPriceLocal() {
        return priceLocal;
    }

    public void setPriceLocal(double priceLocal) {
        this.priceLocal = priceLocal;
    }

    public String[] getGameLanguage() {
        return gameLanguage;
    }

    public void setGameLanguage(String[] gameLanguage) {
        this.gameLanguage = gameLanguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRegionComment() {
        return regionComment;
    }

    public void setRegionComment(String regionComment) {
        this.regionComment = regionComment;
    }

    public double getHistoryLowestPrice() {
        return historyLowestPrice;
    }

    public void setHistoryLowestPrice(double historyLowestPrice) {
        this.historyLowestPrice = historyLowestPrice;
    }


    public GamePrice(String region, boolean hasChinese, boolean isLowestPrice, boolean isOnSale, double saleRate, int endDate, double priceCNY, double priceLocal, String[] gameLanguage, String releaseDate, String regionComment, double historyLowestPrice) {
        this.region = region;
        this.hasChinese = hasChinese;
        this.isLowestPrice = isLowestPrice;
        this.isOnSale = isOnSale;
        this.saleRate = saleRate;
        this.endDate = endDate;
        this.priceCNY = priceCNY;
        this.priceLocal = priceLocal;
        this.gameLanguage = gameLanguage;
        this.releaseDate = releaseDate;
        this.regionComment = regionComment;
        this.historyLowestPrice = historyLowestPrice;
    }
}
