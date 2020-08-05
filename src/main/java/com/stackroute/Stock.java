package com.stackroute;


import com.stackroute.exception.EmptyFileException;
import com.stackroute.exception.NoRecordFoundException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Stock {
    private StockRecord[] stockRecordList;
    private String filePath;

    public Stock() {

    }

    /* setter method stub */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /* this method is used to find the number of records from the file.
    In case the record contains null for any date, then it should be skipped
    */
    public int countLines() {
        int lineCount = 0;
        String input;
        File file = new File(this.filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            reader.readLine(); //skip header line
            while ((input = reader.readLine()) != null) {
                if (input.split(",")[0] != null) { //checking if date not null
                    lineCount++; //count other lines
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineCount;
    }

    /* This method is responsible to read from the file and populate the array stockRecordList */
    public StockRecord[] readFile() {
        File file = new File(this.filePath);
        try {
            if (file.length() == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        List<StockRecord> listStockRecord = new ArrayList<>();
        String[] dataValues = new String[7]; //there are 7 headers
        String input;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            reader.readLine();
            while ((input = reader.readLine()) != null) {
                dataValues = input.split(",");
                StockRecord stockRecord = new StockRecord(); //create new stock record obj
                //set parameters
                try {
                    stockRecord.setDate(new SimpleDateFormat("yyyy-mm-dd").parse(dataValues[0]));
                } catch (ParseException e) {
                    e.getMessage();
                }
                stockRecord.setOpeningPrice(Double.parseDouble(dataValues[1]));
                stockRecord.setDayHighPrice(Double.parseDouble(dataValues[2]));
                stockRecord.setDayLowPrice(Double.parseDouble(dataValues[3]));
                stockRecord.setDayClosingPrice(Double.parseDouble(dataValues[4]));
                stockRecord.setAdjustedDayClosingPrice(Double.parseDouble(dataValues[5]));
                stockRecord.setVolume(Long.parseLong(dataValues[6]));
                listStockRecord.add(stockRecord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockRecordList = (StockRecord[]) listStockRecord.toArray();

    }

    /* This method is used to find absolute return between the first and last records */
    public double findAbsoluteReturn(StockRecord[] records) {
        try {
            if (records.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        double firstReturn = records[0].getDayClosingPrice();
        double lastReturn = records[records.length - 1].getDayClosingPrice();
        return (lastReturn - firstReturn) / firstReturn;
    }

    /* This method is used to find the closing stock price on a given date.
       You need to handle exceptional situations such as empty files, date not found etc.*/
    public double findClosingStockPrice(String date) throws ParseException {
        Date reqdDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
        try {
            if (Arrays.stream(this.stockRecordList).filter(stockRecord -> stockRecord.getDate().equals(reqdDate)).count() == 0) {
                throw new NoRecordFoundException();
            }
        } catch (NoRecordFoundException e) {
            e.getMessage();
        }
        try {
            if (this.stockRecordList.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        return Arrays.stream(this.stockRecordList)
                .filter(stockRecord -> stockRecord.getDate().equals(reqdDate))
                .findFirst().get().getDayClosingPrice();

    }

    /* This method is used to find absolute return between the two dates */
    private double findAbsoluteReturn(StockRecord[] records, String fromDate, String toDate) throws ParseException {
        try {
            if (records.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        Date fromDateVal = new SimpleDateFormat("yyyy-mm-dd").parse(fromDate);
        Date toDateVal = new SimpleDateFormat("yyyy-mm-dd").parse(toDate);

        try {
            if ((Arrays.stream(this.stockRecordList).filter(stockRecord -> stockRecord.getDate().equals(fromDateVal)).count() == 0)
                    || (Arrays.stream(this.stockRecordList).filter(stockRecord -> stockRecord.getDate().equals(toDateVal)).count() == 0)) {
                throw new NoRecordFoundException();
            }
        } catch (NoRecordFoundException e) {
            e.getMessage();
        }

        double fromDateClosing = Arrays.stream(records)
                .filter(stockRecord -> stockRecord.getDate().equals(fromDateVal))
                .findFirst().get().getDayClosingPrice();
        double toDateClosing = Arrays.stream(records)
                .filter(stockRecord -> stockRecord.getDate().equals(toDateVal))
                .findFirst().get().getDayClosingPrice();

        return (toDateClosing - fromDateClosing) / fromDateClosing;
    }

    /* this method is responsible for calculating the investment value as on a given date by specifying the
     * investment date */
    public double findInvestmentValue(double investmentAmount, String investmentDate, String evaluationDate) throws ParseException {
        Date investmentDateVal = new SimpleDateFormat("yyyy-mm-dd").parse(investmentDate);
        Date evaluationDateVal = new SimpleDateFormat("yyyy-mm-dd").parse(evaluationDate);

        try {
            if ((Arrays.stream(this.stockRecordList).filter(stockRecord -> stockRecord.getDate().equals(investmentDateVal)).count() == 0)
                    || (Arrays.stream(this.stockRecordList).filter(stockRecord -> stockRecord.getDate().equals(evaluationDateVal)).count() == 0)) {
                throw new NoRecordFoundException();
            }
        } catch (NoRecordFoundException e) {
            e.getMessage();
        }
        try {
            if (this.stockRecordList.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        double investmentDateClosing = Arrays.stream(this.stockRecordList)
                .filter(stockRecord -> stockRecord.getDate().equals(investmentDateVal))
                .findFirst().get().getDayClosingPrice();
        double evaluationDateClosing = Arrays.stream(this.stockRecordList)
                .filter(stockRecord -> stockRecord.getDate().equals(evaluationDateVal))
                .findFirst().get().getDayClosingPrice();

        return (investmentAmount / investmentDateClosing) * evaluationDateClosing;

    }

    /* this is used to find the highest closing price for the stock */
    public double findHighestClosingStockPrice() {
        try {
            if (this.stockRecordList.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        return Arrays.stream(this.stockRecordList)
                .max((stockRecord1, stockRecord2) -> (int) (stockRecord1.getDayClosingPrice() - stockRecord2.getDayClosingPrice()))
                .get().getDayClosingPrice();
    }

    /* this is used to find the lowest closing price for the stock */
    public double findLowestClosingStockPrice() {
        try {
            if (this.stockRecordList.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        return Arrays.stream(this.stockRecordList)
                .min((stockRecord1, stockRecord2) -> (int) (stockRecord1.getDayClosingPrice() - stockRecord2.getDayClosingPrice()))
                .get().getDayClosingPrice();
    }

    /* this is used to sort the records in ascending order by date */
    public StockRecord[] sortByDate(StockRecord[] stockRecords) {
        try {
            if (stockRecords.length == 0) {
                throw new EmptyFileException();
            }
        } catch (EmptyFileException e) {
            e.getMessage();
        }
        Comparator<StockRecord> sortByDateAsc = (stockRecord1, stockRecord2) -> stockRecord1.getDate().compareTo(stockRecord2.getDate());
        Arrays.sort(stockRecords, sortByDateAsc);
        return stockRecords;
    }

    /* this is used to find the daily avg return of the stock */
    public double findAvgDailyReturn(StockRecord[] stockRecords) {
        StockRecord[] sortedStockRecords = sortByDate(stockRecords); //first sorting by date
        Date endDate = stockRecords[sortedStockRecords.length - 1].getDate();
        Date startDate = sortedStockRecords[0].getDate();
        int numberOfDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

        double startDateClosing = Arrays.stream(sortedStockRecords)
                .filter(stockRecord -> stockRecord.getDate().equals(startDate))
                .findFirst().get().getDayClosingPrice();
        double endDateClosing = Arrays.stream(sortedStockRecords)
                .filter(stockRecord -> stockRecord.getDate().equals(endDate))
                .findFirst().get().getDayClosingPrice();

        return (endDateClosing - startDateClosing) / numberOfDays;
    }

}
