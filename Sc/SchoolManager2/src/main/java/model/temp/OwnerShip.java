package model.temp;

public class OwnerShip
{
    public String province;
    public String city;
    public String correspondent;

    public OwnerShip(String province, String city, String correspondent)
    {
        this.province = province;
        this.city = city;
        this.correspondent = correspondent;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setCorrespondent(String correspondent)
    {
        this.correspondent = correspondent;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public String getCorrespondent()
    {
        return correspondent;
    }

    public String getProvince()
    {
        return province;
    }

    @Override
    public String toString()
    {
        return "OwnerShip{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", correspondent='" + correspondent + '\'' +
                '}';
    }
}
