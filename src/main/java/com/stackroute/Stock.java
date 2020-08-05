package com.stackroute;


public class Stock {
    private StockRecord[] stockRecordList;
    private String filePath;

    public Stock() {

    }

    /* setter method stub */
    public void setFilePath(String filePath) {
    }

    /* this method is used to find the number of records from the file.
    In case the record contains null for any date, then it should be skipped
    */
    public int countLines() {
        return 0;
    }

    /* This method is responsible to read from the file and populate the array stockRecordList */
    public StockRecord[] readFile() {
        return null;

    }

    /* This method is used to find absolute return between the first and last records */
    public double findAbsoluteReturn(StockRecord[] records) {
        return 0;
    }

    /* This method is used to find the closing stock price on a given date.
       You need to handle exceptional situations such as empty files, date not found etc.*/
    public double findClosingStockPrice(String date) {
        return 0;
    }

    /* This method is used to find absolute return between the two dates */
    private double findAbsoluteReturn(StockRecord[] records, String fromDate, String toDate) {
        return 0;
    }

    /* this method is responsible for calculating the investment value as on a given date by specifying the
     * investment date */
    public double findInvestmentValue(double investmentAmount, String investmentDate, String evaluationDate) {

        return 0;

    }

    /* this is used to find the highest closing price for the stock */
    public double findHighestClosingStockPrice() {
        return 0;
    }

    /* this is used to find the lowest closing price for the stock */
    public double findLowestClosingStockPrice() {
        return 0;
    }

    /* this is used to sort the records in ascending order by date */
    public StockRecord[] sortByDate(StockRecord[] stockRecords) {
        return null;
    }

    /* this is used to find the daily avg return of the stock */
    public double findAvgDailyReturn(StockRecord[] stockRecords) {
        return 0;
    }

}
