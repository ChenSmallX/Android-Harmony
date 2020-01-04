package top.chensmallx.android_harmony.model;

import com.google.gson.annotations.SerializedName;

public class GameSummary {
    @SerializedName(value="ID")
    private int id;
    @SerializedName(value="NameCN")
    private String nameCN;
    @SerializedName(value="NameEN")
    private String nameEN;
    @SerializedName(value="ImgUrl")
    private String imgUrl;
    @SerializedName(value="Region")
    private String region;
    @SerializedName(value="PriceCNY")
    private String priceCNY;
    @SerializedName(value="SaleRate")
    private String saleRate;
    @SerializedName(value="LanguageTag")
    private int[] languageTag;
    @SerializedName(value="HasSolidEdition")
    private boolean hasSolidEdition;
    @SerializedName(value="HasDemo")
    private boolean hasDemo;
    @SerializedName(value="IsExclusive")
    private boolean isExclusive;

    public GameSummary(int id, String nameCN, String nameEN, String imgUrl, String region, String priceCNY, String saleRate, int[] languageTag, boolean hasSolidEdition, boolean hasDemo, boolean isExclusive) {
        this.id = id;
        this.nameCN = nameCN;
        this.nameEN = nameEN;
        this.imgUrl = imgUrl;
        this.region = region;
        this.priceCNY = priceCNY;
        this.saleRate = saleRate;
        this.languageTag = languageTag;
        this.hasSolidEdition = hasSolidEdition;
        this.hasDemo = hasDemo;
        this.isExclusive = isExclusive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPriceCNY() {
        return priceCNY;
    }

    public void setPriceCNY(String priceCNY) {
        this.priceCNY = priceCNY;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    public int[] getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(int[] languageTag) {
        this.languageTag = languageTag;
    }

    public boolean isHasSolidEdition() {
        return hasSolidEdition;
    }

    public void setHasSolidEdition(boolean hasSolidEdition) {
        this.hasSolidEdition = hasSolidEdition;
    }

    public boolean isHasDemo() {
        return hasDemo;
    }

    public void setHasDemo(boolean hasDemo) {
        this.hasDemo = hasDemo;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }
}
