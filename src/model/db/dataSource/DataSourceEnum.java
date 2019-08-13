package model.db.dataSource;

public enum DataSourceEnum {

    TXT("TXT"),
    EXCEL("Excel");

    private final String dataSource;

    DataSourceEnum(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSource() {
        return dataSource;
    }

}
