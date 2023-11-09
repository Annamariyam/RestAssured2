package activitesUtilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
    
    // DataProvider to provide data from an Excel sheet (Sheet1)
    @DataProvider(name = "data")
    public String[][] getAllData() throws IOException {
        // Specify the path to the Excel file
        String path = System.getProperty("user.dir") + "\\TestData\\excel.xlsx";
        ExcelUtility xl = new ExcelUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        int colnum = xl.getCellCount("Sheet1", 1); // Full excel sheet
        String[][] apidata = new String[rownum][colnum];
        
        // Extract data from the Excel sheet and populate the apidata array
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }
    
    // DataProvider to provide user names (ids) from an Excel sheet (Sheet1)
    @DataProvider(name = "ids")
    public String[] getUserNames() throws IOException {
        // Specify the path to the Excel file
        String path = System.getProperty("user.dir") + "\\TestData\\excel.xlsx";
        ExcelUtility xl = new ExcelUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        String[] apidata = new String[rownum]; // From that excel sheet, taking ids
        
        // Extract user names (ids) from the Excel sheet and populate the apidata array
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 0);
        }
        return apidata;
    }
    
    // DataProvider to provide update data from another Excel sheet (Sheet2)
    @DataProvider(name = "updates")
    public String[][] updateIds() throws IOException {
        // Specify the path to the Excel file
        String path = System.getProperty("user.dir") + "\\TestData\\excel.xlsx";
        ExcelUtility xl = new ExcelUtility(path);
        int rownum = xl.getRowCount("Sheet2");
        int colnum = xl.getCellCount("Sheet2", 1);
        String[][] apidata = new String[rownum][colnum];
        
        // Extract update data from the Excel sheet (Sheet2) and populate the apidata array
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet2", i, j);
            }
        }
        return apidata;
    }
}
