package top.chensmallx.android_harmony.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Arrays;
import java.sql.Date;


@Entity(tableName =  "wishes")
public class GameSummary {
    @SerializedName(value="ID")
    @PrimaryKey
    private int id;
    @SerializedName(value="NameCN")
    @ColumnInfo(name = "name_cn")
    private String nameCN;
    @SerializedName(value="NameEN")
    @ColumnInfo(name = "name_en")
    private String nameEN;
    @SerializedName(value="ImgUrl")
    @ColumnInfo(name = "img_url")
    private String imgUrl;
    @SerializedName(value="Region")
    @ColumnInfo(name = "region")
    private String region;
    @SerializedName(value="PriceCNY")
    @ColumnInfo(name = "price_cny")
    private String priceCNY;
    @SerializedName(value="SaleRate")
    @ColumnInfo(name = "sale_rate")
    private String saleRate;
    @SerializedName(value="LanguageTag")
    @Ignore
    private int[] languageTag;


    @ColumnInfo(name = "language_tag")
    private String languageTagString;


    @SerializedName(value="HasSolidEdition")
    @ColumnInfo(name = "has_solid_edition")
    private boolean hasSolidEdition;
    @SerializedName(value="HasDemo")
    @ColumnInfo(name = "has_demo")
    private boolean hasDemo;
    @SerializedName(value="IsExclusive")
    @ColumnInfo(name = "is_exclusive")
    private boolean isExclusive;



    @ColumnInfo(name = "update_at")
    private long updateAt;


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
        this.languageTagString = Arrays.toString(this.languageTag);
    }

    public GameSummary(int id, String nameCN, String nameEN, String imgUrl, String region, String priceCNY, String saleRate, String languageTagString, boolean hasSolidEdition, boolean hasDemo, boolean isExclusive) {
        this.id = id;
        this.nameCN = nameCN;
        this.nameEN = nameEN;
        this.imgUrl = imgUrl;
        this.region = region;
        this.priceCNY = priceCNY;
        this.saleRate = saleRate;
        this.hasSolidEdition = hasSolidEdition;
        this.hasDemo = hasDemo;
        this.isExclusive = isExclusive;
        this.setLanguageTagString(languageTagString);
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public void setLanguageTagString(String languageTagString) {
        this.languageTagString = languageTagString;
        Gson gson = new Gson();
        this.languageTag = gson.fromJson(this.languageTagString, int[].class);
    }

    public String getLanguageTagString() {
        return languageTagString;
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
        this.languageTagString = Arrays.toString(languageTag);
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
